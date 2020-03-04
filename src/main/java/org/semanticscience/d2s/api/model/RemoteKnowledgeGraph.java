package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A thought graph associated with this result that is not repeated here, "
		+ "but stored elsewhere in a way that can be remotely accessed by the reader of this Message")
public class RemoteKnowledgeGraph {
	
	@Schema(description = "URL that provides programmatic access to the remote knowledge graph", 
		required = true)
	private String url;
	public String getUrl() {
		return url;
	}
	
	@Schema(description = "Credentials needed for programmatic access to the remote knowledge graph",
		required= false)
	private Credentials credentials;
	public Credentials getCredentials() {
		return credentials;
	}
	
	@Schema(description = "Knowledge Graph protocol, e.g. rdf, neo4j",
		example = "rdf",
		required= false)
	private String protocol;
	public String getProtocol() {
		return protocol;
	}

}