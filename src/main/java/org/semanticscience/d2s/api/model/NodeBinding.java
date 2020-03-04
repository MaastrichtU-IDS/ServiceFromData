package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A Node Binding in the Results"
)
public class NodeBinding {
	
	@Schema(description = "One or more knowledge-graph node ids, i.e. the `id` of a KNode",
//		example= "???", 
		required= true)
	private String[] kg_id;
	public String[] getKg_id() {
		return kg_id;
	}
	
	@Schema(description = "Query-graph node id, i.e. the `node_id` of a QNode", 
//		example = "???", 
		required = true)
	private String qg_id;
	public String getQg_id() {
		return qg_id;
	}

}