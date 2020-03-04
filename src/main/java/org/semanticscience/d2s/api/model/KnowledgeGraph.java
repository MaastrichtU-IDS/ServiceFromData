package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A thought graph associated with this result. "
		+ "This will commonly be a linear path subgraph from one concept to another, but related items aside of the path may be included.")
public class KnowledgeGraph {
	
	@Schema(description = "List of nodes in the KnowledgeGraph",
		required= false)
	private Node[] nodes;
	public Node[] getNodes() {
		return nodes;
	}
	
	@Schema(description = "List of edges in the KnowledgeGraph", 
		required = false)
	private Edge[] edges;
	public Edge[] getEdges() {
		return edges;
	}

}