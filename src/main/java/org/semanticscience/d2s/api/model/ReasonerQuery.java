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
	description = "Reasoner API query model."
)
public class ReasonerQuery {
	
	// http://springfox.github.io/springfox/docs/current/#q27
	// @NotNull(message = "Message cannot be null")
	// ApiModelProperty don't work. The ReasonerQuery object is well recognized
	// But not a single care is given to ApiModelProperty, litteraly useless.
	// Only a misleading description can be passed to he ApiModel.
	@ApiModelProperty(value = "node9", notes = "Query message", required = true)
	private String message;
	
	// @ApiModelProperty(notes = "Maximum number of results returned by the query.", 
	// 	example = "50", required = false, position = 1)
	// @ApiModelProperty(example = "50", required = false)
    private int max_results;

    // … Constructor, getters, setters, ...
}