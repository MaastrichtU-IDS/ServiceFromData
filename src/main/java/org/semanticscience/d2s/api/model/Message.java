package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.QueryGraph;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

// https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#apimodel
@ApiResponse( 
	description = "A message containing the query to the Reasoner API."
)
public class Message {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// See http://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Parameter.html
	@Schema(description = "Reasoner API query graph",
			required = true)
	private QueryGraph query_graph;
	public QueryGraph getQuery_graph() {
		return query_graph;
	}
	
	@Schema(description = "KnowledgeGraph object that contains all the nodes and edges referenced" + 
			" in any of the possible answers to the query OR connection information" + 
			" for a remote knowledge graph")
	private String knowledge_graph;
	public String getKnowledge_graph() {
		return knowledge_graph;
	}

}