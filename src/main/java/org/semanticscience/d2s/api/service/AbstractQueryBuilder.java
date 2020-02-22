package org.semanticscience.d2s.api.service;

public class AbstractQueryBuilder {
	public static final Long LIMIT = 1000L;
	
	// TODO: allow to change prefixes (and see)
	public static final String PREFIXES = 
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
		+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
		+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
		+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
		+ "PREFIX bl: <https://w3id.org/biolink/vocab/>\n"
		+ "PREFIX d2s: <https://w3id.org/d2s/>\n"
		+ "PREFIX bio2rdf: <http://bio2rdf.org/>\n"
		+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
		+ "PREFIX dct: <http://purl.org/dc/terms/>\n"
		+ "PREFIX dctypes: <http://purl.org/dc/dcmitype/>\n"
		+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
		+ "PREFIX idot: <http://identifiers.org/idot/>\n"
		+ "PREFIX dcat: <http://www.w3.org/ns/dcat#>\n"
		+ "PREFIX void: <http://rdfs.org/ns/void#>\n"
		+ "PREFIX void-ext: <http://ldf.fi/void-ext#>\n"
		+ "PREFIX obo: <http://purl.obolibrary.org/obo/>\n"
		+ "PREFIX ncit: <http://ncicb.nci.nih.gov/xml/owl/EVS/Thesaurus.owl#>\n"
		+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
		+ "PREFIX schema: <http://schema.org/>\n";

	protected static String paginate(Long page, Long limit) {
		if (limit == null || limit > LIMIT || limit<1L)
			limit = LIMIT;
		return (page!=null && page > 1L ? " OFFSET " + ((page - 1L) * limit) : "")
				+ " LIMIT " + limit;
	}

}