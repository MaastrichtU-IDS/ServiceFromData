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
	info = @Info(title = "TReK Reasoner API", 
		description = "Reasoner API for the Translator REd Knowledge graph, a BioLink-compliant RDF knowledge graph about Clinical Data.\n\n"
				+ "This endpoint implements the [Reasoner API specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API) to query clinical data from the Columbia Open Health Data (COHD) integrated in a [BioLink-complying RDF](https://biolink.github.io/biolink-model/) triplestore, through a SPARQL endpoint. \n \n "
				+ "COHD was developed at the [Columbia University Department of Biomedical Informatics](https://www.dbmi.columbia.edu/) "
				+ "as a collaboration between the [Weng Lab](http://people.dbmi.columbia.edu/~chw7007/), [Tatonetti Lab](http://tatonettilab.org/), and the [NCATS Biomedical Data Translator program](https://ncats.nih.gov/translator) (Red Team).\n\n"
				+ "This work was supported in part by grants: NCATS OT3TR002027, NLM R01LM009886-08A1, and NIGMS R01GM107145.\n\n"
				+ "This API is registered as a Smart API at [http://trek.smart-api.info/](http://trek.smart-api.info/)\n\n" +
				"The following external resources may be useful:\n" + 
				"* [Reasoner API Specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API)\n" +
				"* [BioLink model documentation](https://biolink.github.io/biolink-model/)\n" +
				"* [Resolve COHD OMOP identifiers](http://api.ohdsi.org/WebAPI/vocabulary/concept/922802)\n" +
				"* [OHDSI](https://www.ohdsi.org/)\n" + 
				"* [OMOP Common Data Model](https://github.com/OHDSI/CommonDataModel/wiki)\n" + 
				"* [Athena](http://athena.ohdsi.org/) (OMOP vocabularies, search, concept relationships, concept hierarchy)\n" + 
				"* [Atlas](http://www.ohdsi.org/web/atlas/) (OMOP vocabularies, search, concept relationships, concept hierarchy, concept sets)",
		termsOfService = "https://opensource.org/licenses/MIT",
		version = "1.0",
		license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT"),
		contact = @Contact(name = "Institute of Data Science at Maastricht University",
				email = "vincent.emonet@maastrichtuniversity.nl",
				url = "https://d2s.semanticscience.org"))
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