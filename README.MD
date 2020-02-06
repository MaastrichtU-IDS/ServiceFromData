## About
This project aims to generate useful webservices for the the BioLink Api.
For now it serves only Swagger V2 services, since it depends on Springfox.

## Build
First build and builds after modifying pom.xml might take some time, since the Dockerfile will resolve all dependencies from scratch. Once cached, builds should be significantly faster.
```bash
docker build -t servicefromdata .
```

## Run
Use `-e ENDPOINT=<biolink_sparql_endpoint_url>` environment switch to override default endpoint.
```bash
docker run -it --rm -p 80:8080 servicefromdata
```

On local GraphDB:

```bash
docker run -it --rm -p 80:8080 --net d2s-cwl-workflows_network -e ENDPOINT="http://graphdb:7200" servicefromdata
```

### Try it out: 

http://localhost/

### Swagger-Apidoc:
http://localhost/v2/api-docs

## Development

Build

```bash
mvn package spring-boot:repackage
```

> Build should be done with `mvn clean install` but Java, Maven and Spring are too instable, and most of the time they fail to properly package spring application even if all required configuration is passed, ending in `no main manifest attribute`

Run

```bash
java -jar target/ServiceFromData-0.9.0.jar org.amalic.servicefromdata.Application
```

> Access on http://localhost:8080