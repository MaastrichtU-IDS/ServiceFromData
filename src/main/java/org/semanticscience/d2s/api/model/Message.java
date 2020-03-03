package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.QueryGraph;

import io.swagger.v3.oas.annotations.Parameter;
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
	
	// @NotNull(message = "Message cannot be null")
	//@ApiModelProperty(value = "Reasoner API query graph",
	//	example= "example query graph", required= true, position= 0)
	
	// See http://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Parameter.html
	@Parameter(name = "Reasoner API query graph", 
			description = "A message containing the query to the Reasoner API.",
			required = true)
	private QueryGraph query_graph;
	public QueryGraph getQuery_graph() {
		return query_graph;
	}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@Parameter(name = "max_results", 
			description = "Maximum number of results returned by the query.", 
			example = "50", required = false)
	private int max_resultse;
	public int getMax_resultse() {
		return max_resultse;
	}

}