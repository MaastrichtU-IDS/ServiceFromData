package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "An Edge Binding in the Results"
)
public class EdgeBinding {
	
	@Schema(description = "One or more knowledge-graph node ids, i.e. the `id` of a KNode",
//		example= "???", 
		required= true)
	private String[] kg_id;
	public String[] getKg_id() {
		return kg_id;
	}
	
	@Schema(description = "Query-graph edge id, i.e. the `edge_id` of a QEdge", 
//		example = "???", 
		required = true)
	private String qg_id;
	public String getQg_id() {
		return qg_id;
	}

}