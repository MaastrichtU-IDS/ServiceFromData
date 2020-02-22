package org.semanticscience.d2s.api.service;

public class ReasonerQueryBuilder extends AbstractQueryBuilder {

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
