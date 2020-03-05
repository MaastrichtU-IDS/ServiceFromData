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
	
	// TODO: Make BiolinkEntity?
	@Schema(description = "BioLink type of the entity", 
		example = "Drug", 
		required = false)
	private String type;
	public String getType() {
		return type;
	}
	
//	public String buildSparqlSelectVar() {
//		return "?" + this.id + " ";
//	}
	
	// Build the SPARQL query based on the object attributes
	public String buildSparqlQuery() {
		String nodeVar = "?" + this.id;
		String sparqlQuery = nodeVar + " ?p ?o . \n";
		if (this.type != null && !this.type.isEmpty()) {
			// If type provided
			sparqlQuery = sparqlQuery + nodeVar + " a bl:" + this.type + " . \n";
		}
		if (this.curie != null && !this.curie.isEmpty()) {
			// TODO: written to work with URI atm, make it work with CURIE too
			sparqlQuery = sparqlQuery + nodeVar + " bl:id <" + this.curie + ">";
		}
		return sparqlQuery;
	}
}