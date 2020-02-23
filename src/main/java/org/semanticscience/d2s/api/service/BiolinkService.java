package org.semanticscience.d2s.api.service;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticscience.d2s.api.repository.RdfRepository;
import org.semanticscience.d2s.api.repository.ResultAs;
import org.semanticscience.d2s.api.model.ReasonerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/biolink/v1")
@Api(description = "API to query the TReK BioLink dataset.")
public class BiolinkService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BiolinkService.class.getName());
    
    @Autowired
	private RdfRepository repository;
	
	@RequestMapping(value = "/prefixes"
    	, method = RequestMethod.GET
		, produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV})
    @ApiOperation(value="Returns all prefixes and their namespace URI used by the API.")
    public void prefixes(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	repository.handleApiCall(BiolinkQueryBuilder.datasets(), request, response);
    }

    @RequestMapping(value = "/datasets"
    	, method = RequestMethod.GET
		, produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV})
    @ApiOperation(value="This call returns all datasets, which can be used as input for other services. Note that the first line in csv is the header.")
    public void datasets(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	repository.handleApiCall(BiolinkQueryBuilder.datasets(), request, response);
    }
    
    @RequestMapping(value = "/get/{dataset}"
	    , method = RequestMethod.GET
	    , produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV})
    @ApiOperation(value="This all classes for this particular data-set with instances having an id.")
    public void classes(HttpServletRequest request, HttpServletResponse response
    		, @ApiParam("Id of the dataset to query.") @PathVariable String dataset
    		) throws IOException {
    	repository.handleApiCall(BiolinkQueryBuilder.classes(dataset), request, response);
    }
    
    @RequestMapping(value = "/get/{dataset}/{class}"
    	, method = RequestMethod.GET
    	, produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV})
    @ApiOperation(value="Returns all instances of a class. Default and maximum limit is 1000 instances per page. Use page parameter to load more.")
    public void datasetClass(HttpServletRequest request, HttpServletResponse response
    		, @ApiParam("Id of the dataset to query.") @PathVariable String dataset
    		, @ApiParam("Class to retrieve informations about.") @PathVariable("class") String className
    		, @RequestParam(required=false) Long page
    		, @RequestParam(required=false) Long limit
     		) throws IOException {
    	repository.handleApiCall(BiolinkQueryBuilder.datasetClass(dataset, className, page, limit), request, response);
    }
	
	@RequestMapping(value = "/get/{dataset}/{class}/{id}"
    	, method = RequestMethod.GET
    	, produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV})
    @ApiOperation(value="Loads all properties of a specific instance.")
    public void datasetClassId(HttpServletRequest request, HttpServletResponse response
    		, @ApiParam("Id of the dataset to query.") @PathVariable String dataset
    		, @ApiParam("Class of the concept to retrieve.") @PathVariable("class") String className
    		, @ApiParam("Id of the concept to retrieve.") @PathVariable String id
    		) throws IOException {
    	repository.handleApiCall(BiolinkQueryBuilder.datasetClassId(dataset, className, id), request, response);
	}
	
	@RequestMapping(value = "/reasoner/query"
    	, method = RequestMethod.POST
    	, produces = {ResultAs.CONTENT_TYPE_JSON})
	@ApiOperation(value="Execute a Reasoner API query on the BioLink-compliant triplestore.",
		notes="See the [Reasoner API specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API#top-level-message-class)")
    public ReasonerQuery reasonerQueryCall(
			// HttpServletRequest request, HttpServletResponse response,
			// @ApiParam(value = "Reasoner API query to execute.",
			// 	required = true,
			// 	example = "{ 'message' : 'tessst'")
			@RequestBody @Valid ReasonerQuery reasonerQuery
    		) throws IOException {
		return reasonerQuery;
    	// repository.handleApiCall(ReasonerQueryBuilder.processQuery(query), request, response);
	}
    
}
