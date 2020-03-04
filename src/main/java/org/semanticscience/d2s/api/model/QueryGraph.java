package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "QueryGraph object that contains a serialization of a query in the form of a graph"
)
public class QueryGraph {
	
	@Schema(description = "Nodes to query",
		required= false)
	private Node nodes;
	public Node getNodes() {
		return nodes;
	}
	
	@Schema(description = "Edges to query.", 
		required = false)
	private Edge edges;
	public Edge getEdges() {
		return edges;
	}

}