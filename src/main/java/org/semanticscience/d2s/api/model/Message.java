package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.QueryGraph;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#apimodel
@ApiModel(
	value = "Reasoner API Message",
	description = "A message containing the query to the Reasoner API."
)
public class Message {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// @NotNull(message = "Message cannot be null")
	@ApiModelProperty(value = "Reasoner API query graph",
		example= "example query graph", required= true, position= 0)
	private QueryGraph query_graph;
	public QueryGraph getQuery_graph() {
		return query_graph;
	}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@ApiModelProperty(name = "max_results", value = "Maximum number of results returned by the query.", 
		example = "50", required = false, position= 1)
	private int max_resultse;
	public int getMax_resultse() {
		return max_resultse;
	}

}