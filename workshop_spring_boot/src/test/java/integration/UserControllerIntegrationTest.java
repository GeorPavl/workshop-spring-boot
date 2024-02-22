package integration;

import gr.infoteam.workshop_spring_boot.WorkshopSpringBootApplication;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserResponseDto;
import gr.infoteam.workshop_spring_boot.utils.exceptions.ErrorResponse;
import integration.container.PostgresqlContainer;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Tag("Integration_test")
@DisplayName("User Controller Integration Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = WorkshopSpringBootApplication.class)
@Slf4j
public class UserControllerIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgres =
            PostgresqlContainer.getInstance();

    private final String BASE_URL = "http://localhost:8080/api/v1";
    private final String USER_URL = BASE_URL + "/users";

    @Test
    void shouldReturnUserByEmailSuccessfully() {
        var userEmail = "mary@mail.com";
        var result = given()
                .contentType(ContentType.JSON)
                .when()
                .get(USER_URL + "/email/" + userEmail)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(new TypeRef<UserResponseDto>() {});

        assertNotNull(result);
        assertEquals(userEmail, result.email());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenInvalidEmail() {
        var userEmail = "jeremy@mail.com";
        var url = USER_URL + "/email/" + userEmail;

        var result = given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(new TypeRef<ErrorResponse>() {});

        result.getErrors().forEach(
                (errorDetails -> log.error(errorDetails.message()))
        );

        assertEquals(1, result.getErrors().size());
    }

}
