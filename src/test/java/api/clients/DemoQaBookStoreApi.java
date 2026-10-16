package api.clients;

import api.models.demoqa.AddBooksRequest;
import api.models.demoqa.BooksResponse;
import api.models.demoqa.CreateUserRequest;
import api.models.demoqa.CreateUserResponse;
import api.models.demoqa.TokenResponse;
import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DemoQaBookStoreApi {

    private final RequestSpecification requestSpecification;

    public DemoQaBookStoreApi() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(TestConfig.DEMOQA_BASE_URL)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    public TokenResponse generateToken(CreateUserRequest request) {
        return given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(TokenResponse.class);
    }

    public void deleteUser(String token, String userId) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .when()
                .delete("/Account/v1/User/{userId}")
                .then()
                .log().ifValidationFails()
                .statusCode(204);
    }

    public BooksResponse getBooks() {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);
    }

    public void addBooks(String token, AddBooksRequest request) {
        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().ifValidationFails()
                .statusCode(201);
    }
}
