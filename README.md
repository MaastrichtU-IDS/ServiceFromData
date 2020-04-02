## About
This project aims to generate useful web services described following the [OpenAPI 3.0](http://spec.openapis.org/oas/v3.0.1) specifications using [SpringDoc](https://springdoc.github.io/springdoc-openapi-demos/). The generated services enable the user to query a [BioLink-compliant](https://biolink.github.io/biolink-model/) RDF knowledge graph using HTTP request following the [Reasoner API Specifications](https://github.com/NCATS-Tangerine/NCATS-ReasonerStdAPI/tree/master/API). 

This services has been developed for the [NCATS Translator project](https://ncats.nih.gov/translator).

## Pull or build
You can pull the image from [DockerHub](https://hub.docker.com/repository/docker/umids/d2s-api)

```bash
docker pull umids/d2s-api
```

Or build from the git repository. Builds after modifying pom.xml might take some time, since the Dockerfile will resolve all dependencies from scratch. Once cached, builds should be significantly faster.

```bash
docker build -t umids/d2s-api .
```

## Run
```bash
docker run -it --rm -p 80:8080 umids/d2s-api
```

Use `-e ENDPOINT=<biolink_sparql_endpoint_url>` environment switch to override default endpoint.

Using a specific endpoint and the [DockerHub image](https://hub.docker.com/repository/docker/umids/d2s-api):

```bash
docker run -it --rm -p 80:8080 --net d2s-cwl-workflows_network -e ENDPOINT="http://graphdb:7200/repositories/test" umids/d2s-api
```

> Access at http://localhost/

> Access the Swagger Apidoc at http://localhost/v3/api-docs

## Development

Change the default endpoint in [src/main/resources/application.yml](https://github.com/MaastrichtU-IDS/d2s-api/blob/master/src/main/resources/application.yml#L2)

Use the [convenience script](https://github.com/MaastrichtU-IDS/d2s-api/blob/master/restart_local.sh):

```bash
./restart_local.sh
```

> Access at http://localhost:8080

Or manually:

```bash
# Build
mvn package spring-boot:repackage
# Run
java -jar target/d2s-api-0.9.0.jar
```

## Acknowledgments

Alexander Malic for his concept and contribution with [serviceFromData](https://github.com/amalic/ServiceFromData)

Thanks Desson Ariawan for its detailed [tutorial on documenting Spring Boot REST API with SpringDoc + OpenAPI 3](https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/).

And Baeldung for their [tutorial about documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation).