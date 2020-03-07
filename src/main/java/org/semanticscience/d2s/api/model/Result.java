package org.semanticscience.d2s.api.model;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "One of potentially several results or answers for a query")
public class Result {
	
	public Result() {
		this.node_bindings = new ArrayList<NodeBinding>();
		this.edge_bindings = new ArrayList<EdgeBinding>();
	}
	
	@Schema(description = "List of QNode-KNode bindings.",
		required= false)
	private ArrayList<NodeBinding> node_bindings;
	public ArrayList<NodeBinding> getNode_bindings() {
		return node_bindings;
	}
	
	@Schema(description = "List of QEdge-KEdge bindings.", 
		required = false)
	private ArrayList<EdgeBinding> edge_bindings;
	public ArrayList<EdgeBinding> getEdge_bindings() {
		return edge_bindings;
	}
	
	// Add new Node and Edge Bindings to their list
	public void addNodeBinding(String qg_id, String kg_id) {
		this.node_bindings.add(new NodeBinding(qg_id, kg_id));
	}
	public void addEdgeBinding(String qg_id, String kg_id) {
		this.edge_bindings.add(new EdgeBinding(qg_id, kg_id));
	}

	@Schema(description = "Any type of score associated with this result (e.g.: 163.233)", 
		required = false)
	private float score;
	public float getScore() {
		return score;
	}
	
	@Schema(description = "Name for the score (e.g.: Jaccard distance)", 
		required = false)
	private String score_name;
	public String getScore_name() {
		return score_name;
	}
	
	@Schema(description = "Sorting indicator for the score: one of higher_is_better or lower_is_better (e.g.: lower_is_better)", 
		required = false)
	private String score_direction;
	public String getScore_direction() {
		return score_direction;
	}
	
	@Schema(description = "Any type of score associated with this result (e.g.: 163.233)", 
		required = false)
	private float confidence;
	public float getConfidence() {
		return confidence;
	}
	
	
	@Schema(description = "URI for this message (e.g.: https://rtx.ncats.io/api/rtx/v1/result/234)", 
		required = false)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "A free text description of this result answer from the reasoner (e.g.: The genetic condition sickle cell anemia may provide protection)", 
		required = false)
	private String description;
	public String getDescription() {
		return description;
	}
	
	@Schema(description = "A single string that is the terse essence of the result (useful for simple answers) (e.g.: ibuprofen)", 
		required = false)
	private String essence;
	public String getEssence() {
		return essence;
	}
	
	@Schema(description = "A Translator bioentity type of the essence (e.g.: drug)", 
		required = false)
	private String essence_type;
	public String getEssence_type() {
		return essence;
	}
	
	@Schema(description = "An arbitrary list of values that captures the essence of the result that can be turned into a tabular result across all answers (each result is a row) for a user that wants tabular output (e.g.: [ ibuprofen, CHEMBL:CHEMBL521 ])", 
		required = false)
	private String[] row_data;
	public String[] getRow_data() {
		return row_data;
	}
	
	@Schema(description = "One of several possible result types: 'individual query answer', 'neighborhood graph', 'type summary graph'", 
		required = false)
	private String result_type;
	public String getResult_type() {
		return result_type;
	}
	
	@Schema(description = "An integer group number for results for use in cases where several results should be grouped together. Also useful to control sorting ascending. (e.g.: 1)", 
		required = false)
	private int result_group;
	public int getResult_group() {
		return result_group;
	}
	
	@Schema(description = "A score that denotes the similarity of this result to other members of the result_group (e.g.: 0.95)", 
		required = false)
	private float result_group_similarity_score;
	public float getResult_group_similarity_score() {
		return result_group_similarity_score;
	}
	
	// TODO: no other reference thatn GitHub ReasonerAPI readme
//	@Schema(description = "A graph that describes the thought pattern of this result (i.e. answer to the query)", 
//		required = false)
//	private String result_graph;
//	public String getResult_graph() {
//		return result_graph;
//	}
	
	@Schema(description = "Identifier string of the reasoner that provided this message (one of trek, RTX, Robokop, Indigo, Integrator, etc.)",
			//example = "trek",
			required = false)
	private String reasoner_id;
	public String getReasoner_id() {
		return reasoner_id;
	}
}