package org.semanticscience.d2s.api.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#apimodel
@ApiModel(
	value = "Reasoner API Query Graph",
	description = "A query graph containing nodes and edges."
)
public class QueryGraph {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// @NotNull(message = "Message cannot be null")
	@ApiModelProperty(value = "Nodes to query",
		example= "example node", required= false, position= 0)
	private String nodes;
		public String getNodes() {
			return nodes;
		}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@ApiModelProperty(value = "Edges to query.", 
		example = "50", required = false, position= 1)
	private int edges;
	public int getEdges() {
		return edges;
	}

}