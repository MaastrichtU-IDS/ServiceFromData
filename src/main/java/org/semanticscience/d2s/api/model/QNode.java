package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A node in the query_graph"
)
public class QNode {
	
	@Schema(description = "QueryGraph internal identifier for this QNode. Recommended form: n00, n01, n02, etc.",
		example= "n00", required= true)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "URI or CURIE identifier for this node", 
		example = "http://api.ohdsi.org/WebAPI/vocabulary/concept/941473", 
		required = false)
	private String curie;
	public String getCurie() {
		return curie;
	}
	
	@Schema(description = "BioLink type of the entity", 
		example = "bl:Drug", 
		required = false)
	private String type;
	public String getType() {
		return type;
	}
	
	@Schema(description = "Additional properties for the entity", 
//		example = "score>0.9", 
		required = false)
	private String additionalProperties;
	public String getAdditionalProperties() {
		return additionalProperties;
	}

}