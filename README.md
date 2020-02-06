## About
This project aims to generate useful webservices for the the BioLink Api.
For now it serves only Swagger V2 services, since it depends on Springfox.

## Build
First build and builds after modifying pom.xml might take some time, since the Dockerfile will resolve all dependencies from scratch. Once cached, builds should be significantly faster.
```bash
docker build -t d2s-api:latest .
```

## Run
Use `-e ENDPOINT=<biolink-kg endpoint url>` environment switch to override default endpoint.
```bash
docker run -it --rm -p 80:8080 d2s-api
```

### Try it out: 
http://localhost/

### Swagger-Apidoc:
http://localhost/v2/api-docs

## Development

```bash
mvn clean install
```

