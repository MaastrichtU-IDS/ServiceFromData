package org.semanticscience.d2s.api.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// @ApiModel (description = "Reasoner API query model.")
// @Api
@Entity
public class ReasonerQuery {
	
	// @ApiModelProperty(notes = "Query message", 
	// 	example = "node9", required = true, position = 0)
	// @ApiModelProperty(example = "node9", required = true)
	@NotNull(message = "Message cannot be null")
	private String message;
	
	// @ApiModelProperty(notes = "Maximum number of results returned by the query.", 
	// 	example = "50", required = false, position = 1)
	// @ApiModelProperty(example = "50", required = false)
    private int max_results;

    // … Constructor, getters, setters, ...
}