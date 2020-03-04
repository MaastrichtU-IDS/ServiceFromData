package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "One of potentially several results or answers for a query")
public class Result {
	
	@Schema(description = "List of QNode-KNode bindings.",
		required= false)
	private NodeBinding[] node_bindings;
	public NodeBinding[] getNode_bindings() {
		return node_bindings;
	}
	
	@Schema(description = "List of QEdge-KEdge bindings.", 
		required = false)
	private EdgeBinding[] edge_bindings;
	public EdgeBinding[] getEdge_bindings() {
		return edge_bindings;
	}

}