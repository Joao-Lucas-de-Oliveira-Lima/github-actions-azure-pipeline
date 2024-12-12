package edu.jl.githubactionsazureapi.swagger;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class SwaggerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should return status 200 for Swagger UI endpoint")
    void shouldReturn200ForSwaggerUI() {
        given()
                .when()
                .get("/swagger-ui/index.html")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Should return status 200 for OpenAPI JSON documentation endpoint")
    void shouldReturn200ForOpenApiDocs() {
        given()
                .when()
                .get("/v3/api-docs")
                .then()
                .statusCode(200);
    }
}
