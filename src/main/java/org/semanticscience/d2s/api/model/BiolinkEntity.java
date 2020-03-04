package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( 
	description = "A subclass of category [bl:NamedThing](https://biolink.github.io/biolink-model/docs/NamedThing.html)",
	example= "Drug", 
	required= false
)
public enum BiolinkEntity {
	
	Drug, Disease, Gene

}