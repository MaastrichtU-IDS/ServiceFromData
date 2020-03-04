package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.QueryGraph;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A message containing the query to the Reasoner API."
)
public class Message {
	// TODO: use SecurityScheme? https://springdoc.github.io/springdoc-openapi-demos/#additonnal-settings
	
	// See http://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Parameter.html
	@Schema(description = "Reasoner API QueryGraph object that contains a serialization of a query in the form of a graph",
			required = true)
	private QueryGraph query_graph;
	public QueryGraph getQuery_graph() {
		return query_graph;
	}
	
	// TODO: create Result, NodeBinding, EdgeBinding and make it an array
	// http://cohd.smart-api.info/#/Translator/query
	// https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/blob/master/API/TranslatorReasonersAPI.yaml#L112
	@Schema(description = "List of all returned potential answers for the query posed")
	private Result[] results;
	public Result[] getResults() {
		return results;
	}
	
	@Schema(description = "KnowledgeGraph object that contains all the nodes and edges referenced" + 
			" in any of the possible answers to the query OR connection information" + 
			" for a remote knowledge graph")
	private KnowledgeGraph knowledge_graph;
	public KnowledgeGraph getKnowledge_graph() {
		return knowledge_graph;
	}

}