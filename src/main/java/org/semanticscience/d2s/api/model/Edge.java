package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "An edge in the query_graph linking two nodes"
)
public class Edge {
	
	public Edge(String id, String type, String source_id, String target_id) {
		this.id = id;
		this.type = type;
		this.source_id = source_id;
		this.target_id = target_id;
	}
	
	@Schema(description = "Local URI or identifier for this edge, which is unique within this" + 
			" KnowledgeGraph, and perhaps within the source reasoner's knowledge" + 
			" graph, e.g. https://w3id.org/biolink/cohd/association/1_8516_941473",
		//example= "https://w3id.org/biolink/cohd/association/1_8507_950641",
		required= true)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "BioLink type of the edge", 
		example = "Association", 
		required = false)
	//private BiolinkRelation type;
	private String type;
	public String getType() {
		return type;
	}
	
	@Schema(description = "Corresponds to the id of the source node of this edge", 
		example = "http://api.ohdsi.org/WebAPI/vocabulary/concept/8507",
		required = false)
	private String source_id;
	public String getSource_id() {
		return source_id;
	}
	
	@Schema(description = "Corresponds to the id of the target node of this edge", 
		example = "http://api.ohdsi.org/WebAPI/vocabulary/concept/950641",
		required = false)
	private String target_id;
	public String getTarget_id() {
		return target_id;
	}
}