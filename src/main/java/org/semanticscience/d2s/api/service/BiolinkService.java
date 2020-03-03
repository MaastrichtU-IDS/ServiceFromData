package org.semanticscience.d2s.api.service;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticscience.d2s.api.repository.RdfRepository;
import org.semanticscience.d2s.api.repository.ResultAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/biolink/v1")
@Tag(name = "Explore datasets", description = "Services to explore the TReK BioLink datasets classes and concepts.")
public class BiolinkService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BiolinkService.class.getName());

	@Autowired
	private RdfRepository repository;

	// https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
	// http://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Operation.html
	@RequestMapping(value = "/prefixes", 
		method = RequestMethod.GET 
		//, produces = {ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV}
	)
	@Operation(summary = "Returns all prefixes and their namespace URI used by the API.", 
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = {
					@Content(mediaType = ResultAs.CONTENT_TYPE_TSV), @Content(mediaType = ResultAs.CONTENT_TYPE_CSV),
					@Content(mediaType = ResultAs.CONTENT_TYPE_JSON),
					@Content(mediaType = ResultAs.CONTENT_TYPE_XML) }) })
	public void prefixes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		repository.handleApiCall(BiolinkQueryBuilder.datasets(), request, response);
	}

	@RequestMapping(value = "/datasets", 
			method = RequestMethod.GET )
	@Operation(summary = "This call returns all datasets, which can be used as input for other services. Note that the first line in csv is the header.", 
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = {
					@Content(mediaType = ResultAs.CONTENT_TYPE_TSV), @Content(mediaType = ResultAs.CONTENT_TYPE_CSV),
					@Content(mediaType = ResultAs.CONTENT_TYPE_JSON),
					@Content(mediaType = ResultAs.CONTENT_TYPE_XML) }) })
	public void datasets(HttpServletRequest request, HttpServletResponse response) throws IOException {
		repository.handleApiCall(BiolinkQueryBuilder.datasets(), request, response);
	}

	@RequestMapping(value = "/get/{dataset}", 
			method = RequestMethod.GET)
	@Operation(summary = "This all classes for this particular data-set with instances having an id.", 
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = {
					@Content(mediaType = ResultAs.CONTENT_TYPE_TSV), @Content(mediaType = ResultAs.CONTENT_TYPE_CSV),
					@Content(mediaType = ResultAs.CONTENT_TYPE_JSON),
					@Content(mediaType = ResultAs.CONTENT_TYPE_XML) }) })
	public void classes(HttpServletRequest request, HttpServletResponse response, @PathVariable String dataset)
			throws IOException {
		repository.handleApiCall(BiolinkQueryBuilder.classes(dataset), request, response);
	}

	@RequestMapping(value = "/get/{dataset}/{class}", 
			method = RequestMethod.GET )
	@Operation(summary = "Returns all instances of a class. Default and maximum limit is 1000 instances per page. Use page parameter to load more.", 
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = {
				@Content(mediaType = ResultAs.CONTENT_TYPE_TSV), @Content(mediaType = ResultAs.CONTENT_TYPE_CSV),
				@Content(mediaType = ResultAs.CONTENT_TYPE_JSON),
				@Content(mediaType = ResultAs.CONTENT_TYPE_XML)}) })
	public void datasetClass(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String dataset,
			@PathVariable("class") String className,
			@RequestParam(required = false) Long page, @RequestParam(required = false) Long limit) throws IOException {
		repository.handleApiCall(BiolinkQueryBuilder.datasetClass(dataset, className, page, limit), request, response);
	}

	@RequestMapping(value = "/get/{dataset}/{class}/{id}", method = RequestMethod.GET, consumes = {
			ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON, ResultAs.CONTENT_TYPE_CSV,
			ResultAs.CONTENT_TYPE_TSV }, produces = { ResultAs.CONTENT_TYPE_XML, ResultAs.CONTENT_TYPE_JSON,
					ResultAs.CONTENT_TYPE_CSV, ResultAs.CONTENT_TYPE_TSV })
	@Operation(summary = "Loads all properties of a specific instance.", 
			responses = {
					@ApiResponse(description = "Successful Operation", responseCode = "200", content = {
						@Content(mediaType = ResultAs.CONTENT_TYPE_TSV), @Content(mediaType = ResultAs.CONTENT_TYPE_CSV),
						@Content(mediaType = ResultAs.CONTENT_TYPE_JSON),
						@Content(mediaType = ResultAs.CONTENT_TYPE_XML)}) })
	public void datasetClassId(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String dataset,
			@PathVariable("class") String className,
			@PathVariable String id) throws IOException {
		repository.handleApiCall(BiolinkQueryBuilder.datasetClassId(dataset, className, id), request, response);
	}

}
