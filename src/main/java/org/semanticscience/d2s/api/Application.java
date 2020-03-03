package org.semanticscience.d2s.api;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(title = "Translator Red Knowledge API", 
			description = "Reasoner API for RDF triplestore.",
			termsOfService = "https://opensource.org/licenses/MIT",
			version = "1.0")
	)
@SpringBootApplication
//@EnableSwagger2WebMvc
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
    		logger.severe("Error: " + ExceptionUtils.getFullStackTrace(e));
    	}
    }
    
}