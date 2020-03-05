package org.semanticscience.d2s.api.repository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.repository.util.Repositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RdfRepository {
	private static final Logger logger = Logger.getLogger(RdfRepository.class.getName());

	@Value("${default.endpoint}")
	private String defaultEndpoint;

	private SPARQLRepository repo;

	public void handleApiCall(String query, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultAs resultAs = ResultAs.fromContentType(request.getHeader("accept"));
		response.setContentType(resultAs.getContentType());
		executeSparql(query, response.getOutputStream(), resultAs);
	}

	public void executeSparql(String query, final OutputStream outputStream, ResultAs resultAs) {
		logger.fine(query.trim().replaceAll("\\s+", " "));
		Repository repo = getRepo();
		try {
			Repositories.tupleQueryNoTransaction(repo, query, resultAs.getWriter(outputStream));
		} finally {
			repo.getConnection().close();
		}
	}
	
	public TupleQueryResult executeSparqlSelect(String queryString) {
		logger.fine(queryString.trim().replaceAll("\\s+", " "));
		Repository repo = getRepo();
		TupleQueryResult selectResults = null;
		try {
			RepositoryConnection conn = repo.getConnection();

			TupleQuery tupleQuery = conn.prepareTupleQuery(queryString);
			selectResults = tupleQuery.evaluate();
//			Repositories.tupleQueryNoTransaction(repo, query, resultAs.getWriter(outputStream));
		} finally {
			repo.getConnection().close();
		}
		return selectResults;
	}

	public List<BindingSet> executeSparql(String query) {
		Repository repo = getRepo();
		try {
			return Repositories.tupleQueryNoTransaction(repo, query, iter -> QueryResults.asList(iter));
		} finally {
			repo.getConnection().close();
		}
	}

	// Get ENDPOINT environment variable here
	public SPARQLRepository getRepo() {
		if (repo == null) {
			String endpoint = System.getenv("ENDPOINT");
			if (endpoint == null || endpoint.length() == 0)
				endpoint = defaultEndpoint;
			logger.fine("ENDPOINT: " + endpoint);
			repo = new SPARQLRepository(endpoint);
		}
		if (!repo.isInitialized()) {
			repo.initialize();
			logger.info("Repository initialized");
		}
		return repo;
	}

}
