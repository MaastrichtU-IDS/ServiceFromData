package org.semanticscience.d2s.api.model;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A Node Binding in the Results"
)
public class NodeBinding {
	
	public NodeBinding(String qg_id, String kg_id) {
		this.qg_id = qg_id;
		this.kg_id = new ArrayList<String>();
		this.kg_id.add(kg_id);
	}
	
	@Schema(description = "One or more knowledge-graph node ids, i.e. the `id` of a KNode",
//		example= "???", 
		required= true)
	private ArrayList<String> kg_id;
	public ArrayList<String> getKg_id() {
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