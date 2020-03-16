package org.semanticscience.d2s.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.semanticscience.d2s.api.model.Message;
import org.semanticscience.d2s.api.model.QEdge;
import org.semanticscience.d2s.api.model.QNode;
import org.semanticscience.d2s.api.model.Query;
import org.semanticscience.d2s.api.model.QueryGraph;
import org.semanticscience.d2s.api.repository.RdfRepository;
import org.semanticscience.d2s.api.repository.ResultAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/reasoner/v1")
@Tag(name = "Reasoner API", 
	description = "Query BioLink-compliant datasets using the Reasoner API")
public class ReasonerService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ReasonerService.class.getName());
    
    @Autowired
	private RdfRepository repository;
	
	@RequestMapping(value = "/query"
    	, method = RequestMethod.POST
    	, consumes = {ResultAs.CONTENT_TYPE_JSON}
    	, produces = {ResultAs.CONTENT_TYPE_JSON})
	@Operation(summary="Execute a Reasoner API query on the BioLink-compliant triplestore.",
		description="Query the BioLink-compliant knowledge graph using the [Reasoner API query specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API#top-level-message-class).\n\n"
				+ "Use this example query for COHD:\n"
				+ "```json \n"
				+ "{\n" + 
				"  \"max_results\": 50,\n" + 
				"  \"message\": {\n" + 
				"    \"query_graph\": {\n" + 
				"      \"nodes\": [\n" + 
				"        { \"id\": \"n00\", \"type\": \"Procedure\" },\n" + 
				"        { \"id\": \"n01\", \"type\": \"Drug\" }\n" + 
				"      ],\n" + 
				"      \"edges\": [\n" + 
				"        { \"id\": \"e00\", \"type\": \"Association\",\n" + 
				"          \"source_id\": \"n00\", \"target_id\": \"n01\" }\n" + 
				"      ]\n" + 
				"    },\n" +
				"    \"query_options\": {\n" +
				"	   \"https://w3id.org/biolink/cohd/attribute/ttest\": \"1.5e+02\",\n" +
				"	   \"https://w3id.org/biolink/cohd/attribute/pvalue\": \"1.338936e-87\"\n" +
				"    }\n" + 
				"  }\n" + 
				"}\n" +
				"```")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", 
    				description = "successful operation", 
                    content = @Content(array = 
                    	@ArraySchema(schema = @Schema(implementation = Message.class)))) })	
	public Message reasonerQueryCall(
		 HttpServletRequest request, HttpServletResponse response,
		@Parameter(description = "Reasoner API query to execute", 
			schema=@Schema(implementation = Query.class),
			required = true)
		@RequestBody @Valid Query reasonerQuery
	) throws IOException {
		Message queryMessage = reasonerQuery.getMessage();
		QueryGraph queryGraph = reasonerQuery.getMessage().getQuery_graph();
		ArrayList<String> variablesArray = new ArrayList<String>();
		String sparqlQuery = " WHERE { \n";
		// Build SPARQL query for each QNode and QEdge
		for (QNode qNode : queryGraph.getNodes()) {
			variablesArray.add(qNode.getId());
			variablesArray.add(qNode.getId() + "type");
			variablesArray.add(qNode.getId() + "name");
			sparqlQuery += qNode.buildSparqlQuery();
		}
		for (QEdge qEdge : queryGraph.getEdges()) {
			variablesArray.add(qEdge.getId());
			variablesArray.add(qEdge.getId() + "type");
			sparqlQuery += qEdge.buildSparqlQuery(queryMessage.getQuery_options());
		}
		// Add SPARQL select part
		String selectVariables = BiolinkQueryBuilder.PREFIXES + "SELECT DISTINCT ?" 
				+ String.join(" ?", variablesArray);
		
		// Close SPARQL query
		if (reasonerQuery.getMax_results() != 0) {
			sparqlQuery += "} LIMIT " + reasonerQuery.getMax_results();
		} else {
			sparqlQuery += "}";
		}
		
		// For results details see http://cohd.smart-api.info/#/Translator/query
//    	repository.handleApiCall(selectVariables + sparqlQuery, request, response);
		
		queryMessage.createResultKnowledgeGraph();
		System.out.println(selectVariables + sparqlQuery);
    	TupleQueryResult reasonerQueryResults = repository.executeSparqlSelect(selectVariables + sparqlQuery);
    	while (reasonerQueryResults.hasNext()) {
			BindingSet resultRow = reasonerQueryResults.next();
			queryMessage.createResultBindings();
			// Generate results for each QNode and QEdge
			for (QNode qNode : queryGraph.getNodes()) {
				queryMessage.addQnodeResult(qNode.getId(), resultRow);
//				System.out.println(qNode.getId() + " qNode ID:");
//				System.out.println(resultRow.getValue(qNode.getId()).stringValue());
//				System.out.println(resultRow.getValue(qNode.getId() + "type").stringValue());
			}
			for (QEdge qEdge : queryGraph.getEdges()) {
				queryMessage.addQedgeResult(qEdge.getId(), qEdge.getSource_id(), qEdge.getTarget_id(), resultRow);
//				System.out.println(qEdge.getId() + " qEdge ID:");
//				System.out.println(resultRow.getValue(qEdge.getId()).stringValue());
//				System.out.println(resultRow.getValue(qEdge.getId() + "type").stringValue());
			}
			// Iterate over the SPARQL variables array
//			for (String variable : variablesArray) {
//				System.out.println(variable + " variable:");
//				System.out.println(resultRow.getValue(variable).stringValue());
//			}
//			IRI subjectIri = f.createIRI(resultRow.getValue("s").stringValue());
//			IRI predicateIri = f.createIRI(resultRow.getValue("p").stringValue());
//			String stringToSplit = resultRow.getValue("toSplit").stringValue();
    	}
		return queryMessage;
	}  
}
