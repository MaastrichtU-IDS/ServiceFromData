package org.semanticscience.d2s.api;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(title = "Knowledge Collaboratory Reasoner API", 
		description = "Reasoner API for the Translator Personal Scientific Knowledge Graph and Knowledge Collaboratory.\n\n"
				+ "This endpoint implements the [Reasoner API specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API) to query Nanopublications integrated in a [BioLink-complying RDF](https://biolink.github.io/biolink-model/) triplestore, through a SPARQL endpoint. \n \n "
				+ "This work was supported in part by grants: NCATS OT3TR002027, NLM R01LM009886-08A1, and NIGMS R01GM107145.\n\n"
				"The following external resources may be useful:\n" + 
				"* [Reasoner API Specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API)\n" +
				"* [BioLink model documentation](https://biolink.github.io/biolink-model/)\n" +,
		termsOfService = "https://opensource.org/licenses/MIT",
		version = "1.0",
		license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT"),
		contact = @Contact(name = "Institute of Data Science at Maastricht University",
				email = "vincent.emonet@maastrichtuniversity.nl",
				url = "https://github.com/vemonet/"))
)
@SpringBootApplication
@RestController
public class Application {
	private static final Logger logger = Logger.getLogger(Application.class.getName());
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @RequestMapping("/")
    public void getDefault(HttpServletResponse response) {
    	try {
    		response.sendRedirect("/swagger-ui.html");
    	} catch (Exception e) {
    		logger.severe("Error: " + ExceptionUtils.getStackTrace(e));
    	}
    } 
}