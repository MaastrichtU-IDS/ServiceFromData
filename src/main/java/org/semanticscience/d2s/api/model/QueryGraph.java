package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A graph intended to be the thought path to be followed by a reasoner to" + 
	" answer the question. This graph is a representation of a question.")
public class QueryGraph {
	
	@Schema(description = "List of nodes in the QueryGraph",
		required= false)
	private QNode[] nodes;
	public QNode[] getNodes() {
		return nodes;
	}
	
	@Schema(description = "List of edges in the QueryGraph", 
		required = false)
	private QEdge[] edges;
	public QEdge[] getEdges() {
		return edges;
	}

}