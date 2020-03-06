package org.semanticscience.d2s.api.model;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A thought graph associated with this result. "
		+ "This will commonly be a linear path subgraph from one concept to another, but related items aside of the path may be included.")
public class KnowledgeGraph {
	
	@Schema(description = "List of nodes in the KnowledgeGraph",
		required= false)
	private ArrayList<Node> nodes = new ArrayList<Node>();
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	@Schema(description = "List of edges in the KnowledgeGraph", 
		required = false)
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public void addNode(String id, String type, String name) {
		this.nodes.add(new Node(id, type, name));
	}

	public void addEdge(String id, String type, String source_id, String target_id) {
		this.edges.add(new Edge(id, type, source_id, target_id));
	}
}