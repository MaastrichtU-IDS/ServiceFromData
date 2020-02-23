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

}