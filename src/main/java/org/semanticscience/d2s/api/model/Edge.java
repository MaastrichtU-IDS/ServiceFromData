package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "An edge in the query_graph linking two nodes"
)
public class Edge {
	
	@Schema(description = "Local URI or identifier for this edge, which is unique within this" + 
			" KnowledgeGraph, and perhaps within the source reasoner's knowledge" + 
			" graph, e.g. https://w3id.org/biolink/cohd/association/1_8516_941473",
		example= "e00", required= true)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "BioLink type of the edge", 
		example = "Association", 
		required = false)
	private String type;
	public String getType() {
		return type;
	}
	
	// Required in YAML
	@Schema(description = "Corresponds to the id of the source node of this edge", 
		example = "n00",
		required = false)
	private String source_id;
	public String getSource_id() {
		return source_id;
	}
	
	// Required in YAML
	@Schema(description = "Corresponds to the id of the target node of this edge", 
		example = "n01",
		required = false)
	private String target_id;
	public String getTarget_id() {
		return target_id;
	}
}