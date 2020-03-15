package org.semanticscience.d2s.api.model;

import java.util.HashMap;
import java.util.Map;

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
		example = "related_to", 
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
//		example = "false",
		required = false)
	private Boolean negated;
	public Boolean getNegated() {
		return negated;
	}
	
	// Build the SPARQL query based on the object attributes
	public String buildSparqlQuery(HashMap<String, String> queryOptions) {
		String edgeVar = "?" + this.id;
		String sparqlQuery = edgeVar + " " + edgeVar + "p " + edgeVar + "o . \n";
		sparqlQuery = sparqlQuery + edgeVar + " a " + edgeVar + "type . \n";
		if (this.type != null && !this.type.isEmpty()) {
			// If type provided
			sparqlQuery = sparqlQuery + edgeVar + " a bl:" + this.type + ". \n";
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
		// Add filters for has_count/has_quotient
		if (queryOptions != null && !queryOptions.isEmpty()) {
			int queryOptionCount = 1;
	    	for (Map.Entry<String, String> queryOption : queryOptions.entrySet()) {
		        System.out.println(queryOption.getKey() + " = " + queryOption.getValue());
		        sparqlQuery += edgeVar + " bl:has_evidence [ bl:has_attribute [ bl:has_attribute_type <"
		        		+ queryOption.getKey() + "> ; bl:has_quantitative_value [ bl:has_numeric_value " 
	        			+ edgeVar + queryOptionCount + " ] ] ].\n";
		        sparqlQuery += "FILTER(xsd:double(" + edgeVar + queryOptionCount +") >= xsd:double(" 
	        			+ queryOption.getValue() + ")) \n";
		        queryOptionCount++;
		        // Adding to the SPARQL query:
		        // ?e00 bl:has_evidence [ bl:has_attribute [ 
				// bl:has_attribute_type <https://w3id.org/biolink/cohd/attribute/ttest> ; 
				// bl:has_quantitative_value [bl:has_numeric_value ?e001 ] ] ] .
				// FILTER( ?e001 >= 40)
		    }
		    // Also: with has_count on evidence:
		    // ?e00 bl:has_evidence [ bl:has_count ?e00has_count ] .
			// FILTER( ?e00has_count >= 40)
		}
	    
		return sparqlQuery;
	}
}