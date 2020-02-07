package org.semanticscience.d2s.api.service;

public class BiolinkQueryBuilder extends AbstractQueryBuilder {
	
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

	public static String datasets() {
		return PREFIXES 
				+ "SELECT ?dataset\n" 
				+ "WHERE {\n" 
				+ "  ?ds a dctypes:Dataset ;\n" 
				+ "  idot:preferredPrefix ?dataset .\n" 
				+ "  ?version dct:isVersionOf ?ds ;\n" 
				+ "  dcat:distribution ?graph .\n"
				+ "  ?graph a void:Dataset . \n"
	    		+ "}\n";
	}
	
	private static final String GRAPH_FROM_DATASET_PART = "?ds a dctypes:Dataset ; idot:preferredPrefix ?dataset .\n" 
					+ "?version dct:isVersionOf ?ds ; dcat:distribution ?graph .\n"
					+ "?graph a void:Dataset .\n" 
					+ "FILTER(?dataset = \"%s\")\n";
	
	private static String getGraphFromDatasetPart(String dataset) {
		return String.format(GRAPH_FROM_DATASET_PART, dataset);
	}
	
	// TODO: fix the class URI strafter using hardcoded URI?
	public static String classes(String dataset) {
		return PREFIXES 
				+ "SELECT ?dataset ?class ?count\n" 
				+ "WHERE {\n" 
				+ "  {\n" 
				+ "    SELECT ?dataset ?classUri (count(?classUri) as ?count)  \n" 
				+ "    WHERE {\n"
				+ getGraphFromDatasetPart(dataset)
				+ "      graph ?graph {\n" 
				+ "        [] a ?classUri ;\n" 
				+ "        bl:id ?id .\n" 
				+ "      }\n" 
				+ "    }\n" 
				+ "    group by ?dataset ?classUri\n" 
				+ "    order by desc(?count)\n" 
				+ "  }\n" 
				+ "  BIND(strafter(str(?classUri),\"https://w3id.org/biolink/vocab/\") as ?class)\n" 
				+ "  FILTER(strlen(?class) > 0)\n" 
				+ "}";
	}
	
	public static String datasetClass(String dataset, String className, Long page, Long limit) {
		return String.format(PREFIXES 
				+ "SELECT ?dataset ?class ?id\n" 
				+ "WHERE {\n" 
				+ getGraphFromDatasetPart(dataset) 
				+ "  GRAPH ?graph {\n" 
				+ "    ?entityUri a ?class .\n" 
				+ "    ?entityUri a bl:%s .\n" 
				+ "    ?entityUri bl:id ?id\n" 
				+ "}}" 
				, className) + paginate(page, limit);
	}
	
	public static String datasetClassId(String dataset, String className, String id) {
		return String.format(PREFIXES 
				+ "SELECT ?dataset ?class ?id ?property ?value\n" 
				+ "WHERE {\n" 
				+ getGraphFromDatasetPart(dataset)
				+ "  GRAPH ?graph {\n" 
				+ "    ?entityUri a bl:%s .\n" 
				+ "    ?entityUri a ?classUri .\n" 
				+ "    ?entityUri bl:id ?id .\n" 
				+ "    ?entityUri ?property ?value .\n" 
				+ "    FILTER(?id = \"%s\")\n" 
				+ "  }\n" 
				+ "  BIND(strafter(str(?classUri),\"https://w3id.org/biolink/vocab/\") as ?class)\n" 
				+ "}"
				, className, id);
	}
	
}
