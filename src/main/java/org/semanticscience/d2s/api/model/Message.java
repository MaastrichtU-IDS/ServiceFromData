package org.semanticscience.d2s.api.model;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.rdf4j.query.BindingSet;
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
	private ArrayList<Result> results;
	public ArrayList<Result> getResults() {
		return results;
	}
	
	// TODO: this should be able to be either a KnowledgeGraph OR a RemoteKnowledgeGraph
	@Schema(description = "KnowledgeGraph object that contains all the nodes and edges referenced" + 
			" in any of the possible answers to the query",
			required = false)
	private KnowledgeGraph knowledge_graph;
	public KnowledgeGraph getKnowledge_graph() {
		return knowledge_graph;
	}
	
	public void createResultKnowledgeGraph() {
		this.knowledge_graph = new KnowledgeGraph();
		this.results = new ArrayList<Result>();
	}
	
	public void createResultBindings() {
		this.results.add(new Result());
	}
	
	// TODO: process results here!
	public void addQnodeResult(String id, BindingSet resultRow) {
//		System.out.println("addQnode");
//		System.out.println(id);
//		System.out.println(resultRow.getValue(id).stringValue());
//		System.out.println(resultRow.getValue(id + "type").stringValue());
//		System.out.println(resultRow.getValue(id + "name").stringValue());
		this.knowledge_graph.addNode(resultRow.getValue(id).stringValue(), 
				resultRow.getValue(id + "type").stringValue(),
				resultRow.getValue(id + "name").stringValue());
		// Add NodeBinding to the latest result added to the list
		this.results.get(results.size() - 1).addNodeBinding(id, resultRow.getValue(id).stringValue());
	}
	public void addQedgeResult(String id, String sourceId, String targetId, BindingSet resultRow) {
		this.knowledge_graph.addEdge(resultRow.getValue(id).stringValue(),
				resultRow.getValue(id + "type").stringValue(),
				resultRow.getValue(sourceId).stringValue(),
				resultRow.getValue(targetId).stringValue()
				);
		// Add EdgeBinding to the latest result added to the list
		this.results.get(results.size() - 1).addEdgeBinding(id, resultRow.getValue(id).stringValue());
	}
	
	@Schema(description = "RemoteKnowledgeGraph object that contains  connection information" + 
			" for a remote knowledge graph connection (not used if knowledge_graph defined)",
			required = false)
	private RemoteKnowledgeGraph remote_knowledge_graph;
	public RemoteKnowledgeGraph getRemote_knowledge_graph() {
		return remote_knowledge_graph;
	}
	
	@Schema(description = "JSON-LD context URI (e.g.: https://rtx.ncats.io/ns/translator.jsonld)",
			//example = "https://rtx.ncats.io/ns/translator.jsonld",
			required = false)
	private String context;
	public String getContext() {
		return context;
	}
	
	@Schema(description = "Entity type of this message (e.g.: translator_reasoner_message)",
			//example = "translator_reasoner_message",
			required = false)
	private String type;
	public String getType() {
		return type;
	}
	
	@Schema(description = "URI for this message (e.g.: https://rtx.ncats.io/api/rtx/v1/message/123)",
			//example = "https://rtx.ncats.io/api/rtx/v1/message/123",
			required = false)
	private String id;
	public String getId() {
		return id;
	}
	
	@Schema(description = "Identifier string of the reasoner that provided this message (one of trek, RTX, Robokop, Indigo, Integrator, etc.)",
			//example = "trek",
			required = false)
	private String reasoner_id;
	public String getReasoner_id() {
		return reasoner_id;
	}
	
	@Schema(description = "Version label of the tool that generated this message (e.g.: RTX 0.5.0)",
			//example = "RTX 0.5.0",
			required = false)
	private String tool_version;
	public String getTool_version() {
		return tool_version;
	}
	
	@Schema(description = "Version label of this JSON-LD schema (e.g.: 0.9.0)",
			//example = "0.9.0",
			required = false)
	private String schema_version;
	public String getSchema_version() {
		return reasoner_id;
	}
	
	@Schema(description = "Datetime string for the time that this message was generated (e.g.: 2018-01-09 12:34:45)",
			//example = "2018-01-09 12:34:45",
			required = false)
	private String datetime;
	public String getDatetime() {
		return datetime;
	}
	
	@Schema(description = "Total number of results from the query (which may be less than what is returned if limits were placed on the number of results to return) (e.g.: 42)",
			//example = "42",
			required = false)
	private String n_results;
	public String getN_results() {
		return n_results;
	}
	
	@Schema(description = "Set to OK for success, or some other short string to indicate and error (e.g., KGUnavailable, TermNotFound, etc.) (e.g.: OK)",
			required = false)
	private String message_code;
	public String getMessage_code() {
		return message_code;
	}
	
	@Schema(description = "Extended description denoting the success or mode of failure in the generation of the message (e.g.: 9 results found)",
			required = false)
	private String code_description;
	public String getCode_description() {
		return code_description;
	}
	
	@Schema(description = "List of column names that corresponds to the row_data for each result (e.g.: [chemical_substance.name, chemical_substance.id])",
			required = false)
	private String[] table_column_names;
	public String[] getTable_column_names() {
		return table_column_names;
	}
	
	@Schema(description = "The original question text typed in by the user (e.g.: what proteins are affected by sickle cell anemia)",
			required = false)
	private String original_question;
	public String getOriginal_question() {
		return original_question;
	}
	
	@Schema(description = "A precise restatement of the question, as understood by the Translator, for which the answer applies. The user should verify that the restated question matches the intent of their original question (it might not). (e.g.: Which proteins are affected by sickle cell anemia?)",
			required = false)
	private String restated_question;
	public String getRestated_question() {
		return restated_question;
	}
	
	@Schema(description = "The query type id if one is known for the query/message (as defined in [Google docs](https://docs.google.com/spreadsheets/d/18zW81wteUfOn3rFRVG0z8mW-ecNhdsfD_6s73ETJnUw/edit#gid=1742835901) ) (e.g.: Q2)",
			required = false)
	private String query_type_id;
	public String getQuery_type_id() {
		return query_type_id;
	}
	
	@Schema(description = " Dict of options that can be sent with the query. Options are tool specific and not stipulated here (e.g.: {coalesce=True,threshold=0.9})",
			required = false)
	private Map<String, String> query_options;
	public Map<String, String> getQuery_options() {
		return query_options;
	}

}