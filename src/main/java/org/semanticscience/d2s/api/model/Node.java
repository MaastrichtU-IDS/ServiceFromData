package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A node in the query_graph"
)
public class Node {
	
	@Schema(description = "URI or CURIE identifier for this node, e.g. http://api.ohdsi.org/WebAPI/vocabulary/concept/941473",
		example= "n00", required= true)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "Formal name of the entity", 
		example = "Betamethasone", 
		required = false)
	private String name;
	public String getName() {
		return name;
	}
	
	@Schema(description = "BioLink type of the entity", 
		example = "Drug", 
		required = false)
	private BiolinkEntity type;
	public BiolinkEntity getType() {
		return type;
	}
	
//	@Schema(description = "Additional properties for the entity", 
////		example = "score>0.9", 
//		required = false)
//	private String additionalProperties;
//	public String getAdditionalProperties() {
//		return additionalProperties;
//	}

}