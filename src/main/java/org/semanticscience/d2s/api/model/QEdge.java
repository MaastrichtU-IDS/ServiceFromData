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
	
	// Build the SPARQL query based on the object attributes
	public String buildSparqlQuery() {
		String edgeVar = "?" + this.id;
		String sparqlQuery = edgeVar + " ?p ?o . \n";
		if (this.type != null && !this.type.isEmpty()) {
			// If type provided
			sparqlQuery = sparqlQuery + edgeVar + " a bl:" + this.type + " . \n";
		}
		if (this.source_id != null && !this.source_id.isEmpty()) {
			sparqlQuery = sparqlQuery + edgeVar + " bl:subject ?" + this.source_id + " . \n";
		}
		if (this.target_id != null && !this.type.isEmpty()) {
			sparqlQuery = sparqlQuery + edgeVar + " bl:object ?" + this.target_id + " . \n";
		}
		if (this.relation != null && !this.relation.isEmpty()) {
			// TODO: written to work with URI atm, make it work with CURIE too
			sparqlQuery = sparqlQuery + edgeVar + " bl:relation bl:" + this.relation + " .";
		}
		return sparqlQuery;
	}
}