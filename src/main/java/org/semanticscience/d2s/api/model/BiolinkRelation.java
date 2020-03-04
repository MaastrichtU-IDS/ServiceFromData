package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A relation, i.e. child of [related_to](https://biolink.github.io/biolink-model/docs/related_to.html)",
	example= "affects", 
	required= false
)
public enum BiolinkRelation {
	
	affects, contributes_to, has_gene_product, produces, same_as

}