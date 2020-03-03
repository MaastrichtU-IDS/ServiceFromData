package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Top level Reasoner API Query, contains a message with a query_graph.")
public class ReasonerQuery {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// @NotNull(message = "Message cannot be null")
	@Schema(description = "Query message for the Reasoner API, containing a query_graph.",
			required= true)
	// message in http://transltr.io:7071/apidocs/#/default/post_validate_query
	// but "query_message" in https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API#top-level-query-class
	private Message message;
	public Message getMessage() {
		return message;
	}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@Schema(name = "max_results", 
			description  = "Maximum number of results returned by the query.", 
			example = "50", required = false)
	private int max_results;
	public int getMax_results() {
		return max_results;
	}

	@Schema(name = "page_size", 
			description = "Split the results into pages with this number of results each.", 
			example = "20", required = false)
	private int page_size;
	public int getPage_size() {
		return page_size;
	}

	@Schema(name = "page_number", 
			description = "Page number of results when the number of results exceeds the page_size.", 
			example = "20", required = false)
	private int page_number;
	public int getPage_number() {
		return page_number;
	}

	@Schema(name = "bypass_cache", 
			description = "Set to true in order to bypass any possible cached message and try to answer the query over again.", 
			example = "true", required = false)
	private Boolean bypass_cache;
	public Boolean getBypass_cache() {
		return bypass_cache;
	}

	@Schema(name = "asynchronous",
			description = "Set to true in order to receive an incomplete message_id if the query will take a while. Client can then periodically request that message_id for a status update and eventual complete message.", 
			example = "false", required = false)
	private Boolean asynchronous;
	public Boolean getAsynchronous() {
		return asynchronous;
	}

	@Schema(name = "reasoner_ids",
			description = "List of reasoners to consult for the query (e.g.: [ trek, RTX, Robokop ])", 
			example = "['trek']", required = false)
	private int reasoner_ids;
	public int getReasoner_ids() {
		return reasoner_ids;
	}

	@Schema(name = "previous_message_processing_plan", 
			description = "Container for one or more Message objects or identifiers for one or more Messages along with a processing plan for how those messages should be processed and returned.", 
			example = "1", required = false)
	private int previous_message_processing_plan;
	public int getPrevious_message_processing_plan() {
		return previous_message_processing_plan;
	}

}