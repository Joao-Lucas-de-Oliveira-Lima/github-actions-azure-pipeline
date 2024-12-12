package edu.jl.githubactionsazureapi.infrastructure.controller;

import edu.jl.githubactionsazureapi.infrastructure.dto.user.UserResponseDto;
import edu.jl.githubactionsazureapi.mock.UserMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerIT extends UserMock {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should return all users successfully")
    void shouldReturnAllUsers() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("$.size()", is(totalDatabaseUsers))
                .extract().response();

        List<UserResponseDto> users = response.jsonPath().getList("$", UserResponseDto.class);
        users.forEach(user -> {
            assertThat(user.getId()).isGreaterThan(0);
            assertThat(user.getFullName()).isNotBlank();
            assertThat(user.getUsername()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Should return user by valid ID")
    void shouldReturnUserByValidId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(200)
                .body("username", equalTo(validUserResponseDto.getUsername()))
                .body("fullName", equalTo(validUserResponseDto.getFullName()))
                .body("id", is(validUserId.intValue()));
    }

    @Test
    @DisplayName("Should return 404 for invalid user ID")
    void shouldReturnNotFoundForInvalidId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users/{id}", invalidUserId)
                .then()
                .statusCode(404)
                .body("timestamp", notNullValue())
                .body("message", notNullValue())
                .body("details", notNullValue());
    }

    @Test
    @DisplayName("Should save user successfully")
    void shouldSaveUserSuccessfully() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(validUserRequestDto)
                .post("/api/v1/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("username", equalTo(validUserRequestDto.getUsername()))
                .body("fullName", equalTo(validUserRequestDto.getFullName()));
    }

    @Test
    @DisplayName("Should return 400 when updating user with null username")
    void shouldReturnBadRequestWhenUpdatingUserWithNullUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithNullUsername)
                .put("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when updating user with empty username")
    void shouldReturnBadRequestWhenUpdatingUserWithEmptyUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithEmptyUsername)
                .put("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when updating user with blank username")
    void shouldReturnBadRequestWhenUpdatingUserWithBlankUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithBlankUsername)
                .put("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should delete user successfully")
    void shouldDeleteUserSuccessfully() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(204);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("$.size()", is(totalDatabaseUsers - 1));
    }

    @Test
    @DisplayName("Should update user successfully with valid ID")
    void shouldUpdateUserSuccessfully() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(validUserRequestDto)
                .put("/api/v1/users/{id}", validUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(validUserId.intValue()))
                .body("username", equalTo(validUserRequestDto.getUsername()))
                .body("fullName", equalTo(validUserRequestDto.getFullName()));
    }

    @Test
    @DisplayName("Should return 404 when updating user with invalid ID")
    void shouldReturnNotFoundWhenUpdatingUserWithInvalidId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(validUserRequestDto)
                .put("/api/v1/users/{id}", invalidUserId)
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Should return 400 when saving user with null username")
    void shouldReturnBadRequestWhenSavingUserWithNullUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithNullUsername)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when saving user with empty username")
    void shouldReturnBadRequestWhenSavingUserWithEmptyUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithEmptyUsername)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when saving user with blank username")
    void shouldReturnBadRequestWhenSavingUserWithBlankUsername() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithBlankUsername)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when saving user with null full name")
    void shouldReturnBadRequestWhenSavingUserWithNullFullName() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithNullFullName)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when saving user with empty full name")
    void shouldReturnBadRequestWhenSavingUserWithEmptyFullName() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithEmptyFullName)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when saving user with blank full name")
    void shouldReturnBadRequestWhenSavingUserWithBlankFullName() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(invalidUserRequestDtoWithBlankFullName)
                .post("/api/v1/users")
                .then()
                .statusCode(400);
    }
}
