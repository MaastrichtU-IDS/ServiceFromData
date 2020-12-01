package org.semanticscience.d2s.api.repository;

import java.io.OutputStream;
import java.util.Arrays;

import org.eclipse.rdf4j.query.resultio.TupleQueryResultWriter;
import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.eclipse.rdf4j.query.resultio.text.csv.SPARQLResultsCSVWriter;
import org.eclipse.rdf4j.query.resultio.text.tsv.SPARQLResultsTSVWriter;

public enum ResultAs {
	XML
	, JSON
	, JSON_SPARQL
	, CSV
	, TSV;
	
	public final static String CONTENT_TYPE_XML = "application/xml";
	public final static String CONTENT_TYPE_JSON = "application/json";
	public final static String CONTENT_TYPE_JSON_SPARQL = "application/sparql-results+json";
	public final static String CONTENT_TYPE_CSV = "text/csv";
	public final static String CONTENT_TYPE_TSV = "text/tab-separated-values";
	
	private final static String[] CONTENT_TYPES = new String[]{
			CONTENT_TYPE_XML, CONTENT_TYPE_JSON, CONTENT_TYPE_CSV, CONTENT_TYPE_TSV, CONTENT_TYPE_JSON_SPARQL};
	
	public TupleQueryResultWriter getWriter(OutputStream out) {
		switch(this) {
		case XML: return new SPARQLResultsXMLWriter(out); 
		case JSON: return new SPARQLResultsJSONWriter(out);
		case JSON_SPARQL: return new SPARQLResultsJSONWriter(out);
		case CSV: return new SPARQLResultsCSVWriter(out);
		case TSV: return new SPARQLResultsTSVWriter(out);
		default: throw new IllegalStateException();
		}
	}

	public static ResultAs fromContentType(String accept) {
		switch(accept) {
		case CONTENT_TYPE_XML: return XML;
		case CONTENT_TYPE_JSON: return JSON;
		case CONTENT_TYPE_JSON_SPARQL: return JSON_SPARQL;
		case CONTENT_TYPE_CSV: return CSV;
		case CONTENT_TYPE_TSV: return TSV;
		default: throw new IllegalStateException("Unknown accept header. Try one of these " + Arrays.toString(CONTENT_TYPES));
		}
	}
	
	public String getContentType() {
		switch(this) {
		case XML: return CONTENT_TYPE_XML;
		case JSON: return CONTENT_TYPE_JSON;
		case JSON_SPARQL: return CONTENT_TYPE_JSON_SPARQL;
		case CSV: return CONTENT_TYPE_CSV;
		case TSV: return CONTENT_TYPE_TSV;
		default: throw new IllegalStateException();
		}
	}

}
