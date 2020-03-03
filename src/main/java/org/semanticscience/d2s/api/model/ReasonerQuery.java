package org.semanticscience.d2s.api.model;

import org.semanticscience.d2s.api.model.Message;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#apimodel
@ApiModel(
	value = "Reasoner API Query",
	description = "Reasoner API specifications query model."
)
public class ReasonerQuery {
	// public ReasonerQuery(String message, int max_results) {
	// 	this.message = message;
	// 	this.max_results = max_results;
	// }
	
	// @NotNull(message = "Message cannot be null")
	@ApiModelProperty(value = "Reasoner API query message",
		example= "example message", required= true, position= 0)
	// message in http://transltr.io:7071/apidocs/#/default/post_validate_query
	// but "query_message" in https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API#top-level-query-class
	private Message message;
	public Message getMessage() {
		return message;
	}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@ApiModelProperty(name = "max_results", value = "Maximum number of results returned by the query.", 
		example = "50", required = false, position= 1)
	private int max_results;
	public int getMax_results() {
		return max_results;
	}

	@ApiModelProperty(name = "page_size", value = "Split the results into pages with this number of results each.", 
		example = "20", required = false, position= 1)
	private int page_size;
	public int getPage_size() {
		return page_size;
	}

	@ApiModelProperty(name = "page_number", value = "Page number of results when the number of results exceeds the page_size.", 
		example = "20", required = false, position= 1)
	private int page_number;
	public int getPage_number() {
		return page_number;
	}

	@ApiModelProperty(name = "bypass_cache", value = "Set to true in order to bypass any possible cached message and try to answer the query over again.", 
		example = "true", required = false, position= 1)
	private Boolean bypass_cache;
	public Boolean getBypass_cache() {
		return bypass_cache;
	}

	@ApiModelProperty(name = "asynchronous", value = "Set to true in order to receive an incomplete message_id if the query will take a while. Client can then periodically request that message_id for a status update and eventual complete message.", 
		example = "false", required = false, position= 1)
	private Boolean asynchronous;
	public Boolean getAsynchronous() {
		return asynchronous;
	}

	@ApiModelProperty(name = "reasoner_ids", value = "List of reasoners to consult for the query (e.g.: [ trek, RTX, Robokop ])", 
		example = "['trek']", required = false, position= 1)
	private int reasoner_ids;
	public int getReasoner_ids() {
		return reasoner_ids;
	}

	@ApiModelProperty(name = "previous_message_processing_plan", value = "Container for one or more Message objects or identifiers for one or more Messages along with a processing plan for how those messages should be processed and returned.", 
		example = "1", required = false, position= 1)
	private int previous_message_processing_plan;
	public int getPrevious_message_processing_plan() {
		return previous_message_processing_plan;
	}

}