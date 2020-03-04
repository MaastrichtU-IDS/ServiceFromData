package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "An edge in the query_graph linking two nodes"
)
public class QEdge {
	
	@Schema(description = "QueryGraph internal identifier for this QEdge. Recommended form: e00, e01, e02, etc.",
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
	
	@Schema(description = "Lower-level relationship type of this edge (e.g.: upregulates)", 
		example = "upregulates", 
		required = false)
	private String relation;
	public String getRelation() {
		return relation;
	}
	
	@Schema(description = "Corresponds to the id of the source node of this edge", 
		example = "n00",
		required = false)
	private String source_id;
	public String getSource_id() {
		return source_id;
	}
	
	@Schema(description = "Corresponds to the id of the target node of this edge", 
		example = "n01",
		required = false)
	private String target_id;
	public String getTarget_id() {
		return target_id;
	}
	
	@Schema(description = "Boolean that if set to true, indicates the edge statement is negated i.e. is not true", 
		example = "false",
		required = false)
	private Boolean negated;
	public Boolean getNegated() {
		return negated;
	}
}