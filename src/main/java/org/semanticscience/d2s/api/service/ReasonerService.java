package org.semanticscience.d2s.api.service;

import java.io.IOException;
import java.util.logging.Logger;

import org.semanticscience.d2s.api.repository.RdfRepository;
import org.semanticscience.d2s.api.repository.ResultAs;
import org.semanticscience.d2s.api.model.ReasonerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;


@RestController
@RequestMapping("/reasoner/v1")
@Tag(name = "Reasoner API", description = "Query TReK BioLink-compliant datasets using the Reasoner API.")
public class ReasonerService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ReasonerService.class.getName());
    
    @Autowired
	private RdfRepository repository;
	
	@RequestMapping(value = "/query"
    	, method = RequestMethod.POST
    	, consumes = {ResultAs.CONTENT_TYPE_JSON}
    	, produces = {ResultAs.CONTENT_TYPE_JSON})
	@Operation(summary="Execute a Reasoner API query on the BioLink-compliant triplestore.",
		description="See the [Reasoner API specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API#top-level-message-class)")
    public ReasonerQuery reasonerQueryCall(
			// HttpServletRequest request, HttpServletResponse response,
			@Parameter(description = "Reasoner API query to execute.",
				name = "Reasoner API query",
				required = true)
			@RequestBody @Valid ReasonerQuery reasonerQuery
    		) throws IOException {
		return reasonerQuery;
    	// repository.handleApiCall(ReasonerQueryBuilder.processQuery(query), request, response);
	}
    
}
