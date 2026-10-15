package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApiTest {

    protected RequestSpecification requestSpec;

    @BeforeEach
    void configureApi() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://fakestoreapi.com")
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }
}