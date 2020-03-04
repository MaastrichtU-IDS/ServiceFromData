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
	
	@Schema(description = "List of all returned potential answers for the query posed")
	private Result[] results;
	public Result[] getResults() {
		return results;
	}
	
	// TODO: this should be able to be either a KnowledgeGraph OR a RemoteKnowledgeGraph
	@Schema(description = "KnowledgeGraph object that contains all the nodes and edges referenced" + 
			" in any of the possible answers to the query OR connection information" + 
			" for a remote knowledge graph",
			required = false)
	private KnowledgeGraph knowledge_graph;
	public KnowledgeGraph getKnowledge_graph() {
		return knowledge_graph;
	}
	
	@Schema(description = "RemoteKnowledgeGraph object that contains  connection information" + 
			" for a remote knowledge graph connection (not used if knowledge_graph defined)",
			required = false)
	private RemoteKnowledgeGraph remote_knowledge_graph;
	public RemoteKnowledgeGraph getRemote_knowledge_graph() {
		return remote_knowledge_graph;
	}

}