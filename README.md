# Apitemplate

- Java 21
- Spring Boot 3.4.1
- Spring Security
- Postgresql
- Flyway
- Jwt
- Mapstruct
- Openapi

## Instalação do serviço de banco de dados via Docker

```bash
docker-compose -f Docker/docker-compose.yml up
```

## Instalação de dependências

```bash
mvn clean install -DskipTests
```

## Iniciar servidor

```bash
mvn spring-boot:run
```

## Link do swagger

[Swagger](http://localhost:8080/swagger-ui/index.html)