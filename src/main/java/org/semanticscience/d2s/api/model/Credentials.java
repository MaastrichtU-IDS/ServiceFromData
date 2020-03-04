package org.semanticscience.d2s.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credentials needed for programmatic access to the remote knowledge graph")
public class Credentials {
	
	@Schema(description = "Username needed for programmatic access to the remote knowledge graph",
		example = "myusername",
		required= true)
	private String username;
	public String getUsername() {
		return username;
	}
	
	@Schema(description = "Password needed for programmatic access to the remote knowledge graph",
		example = "mypassword",
		required= true)
	private String password;
	public String getPassword() {
		return password;
	}

}