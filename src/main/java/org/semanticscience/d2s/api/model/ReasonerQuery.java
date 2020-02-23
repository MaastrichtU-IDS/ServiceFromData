package org.semanticscience.d2s.api.model;

// import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

//import javax.persistence.Entity;
// import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// @ApiModel (description = "Reasoner API query model.")
// @Entity
@ApiModel(
	value = "ReasonerQuery",
	description = "Reasoner API specifications query model."
)
public class ReasonerQuery {
	
	// http://springfox.github.io/springfox/docs/current/#q27
	// @NotNull(message = "Message cannot be null")
	@ApiModelProperty(value = "Reasoner API query message",
		example= "example message", required= true, position= 0)
	private String message;
	public String getMessage() {
		return message;
	}
	// public void setMessage(String message) {
	// 	this.message = message;
	// }
	
	@ApiModelProperty(name = "max_results", value = "Maximum number of results returned by the query.", 
		example = "50", required = false, position= 1)
	private int max_results;
	public int getmax_results() {
		return max_results;
	}

    // … Constructor, getters, setters, ...
}