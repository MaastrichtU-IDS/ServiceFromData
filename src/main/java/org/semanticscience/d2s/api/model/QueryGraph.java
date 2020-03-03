package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse( 
	description = "Reasoner API Query Graph, containing nodes and edges"
)
public class QueryGraph {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// @NotNull(message = "Message cannot be null")
	@Parameter(name = "Nodes to query",
		example= "example node", required= false)
	private String nodes;
		public String getNodes() {
			return nodes;
		}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@Parameter(name = "Edges to query.", 
		example = "50", required = false)
	private int edges;
	public int getEdges() {
		return edges;
	}

}