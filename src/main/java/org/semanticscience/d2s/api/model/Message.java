package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.QueryGraph;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A message containing the query to the Reasoner API."
)
public class Message {
	
	// See http://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Parameter.html
	@Schema(description = "Reasoner API QueryGraph object that contains a serialization of a query in the form of a graph",
			required = true)
	private QueryGraph query_graph;
	public QueryGraph getQuery_graph() {
		return query_graph;
	}
	
	@Schema(description = "KnowledgeGraph object that contains all the nodes and edges referenced" + 
			" in any of the possible answers to the query OR connection information" + 
			" for a remote knowledge graph")
	private String knowledge_graph;
	public String getKnowledge_graph() {
		return knowledge_graph;
	}
	
	@Schema(description = "List of all returned potential answers for the query posed")
	private String results;
	public String getResults() {
		return results;
	}

}