package api;

import api.models.PostProduct;
import api.models.Product;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Execution(ExecutionMode.CONCURRENT)
@Epic("FakeStore API")
@Feature("Products")
@Owner("Iaroslav")
class ProductApiTest extends BaseApiTest {

    @Story("Get product by ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Return an existing product by ID")
    @Tag("smoke")
    @Tag("regression")
    @Test
    void shouldReturnProductById() {
        int productId = 1;

        Product product = given()
                .spec(requestSpec)
                .pathParam("productId", productId)
        .when()
                .get("/products/{productId}")
        .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(Product.class);

        assertNotNull(product.rating());

        assertAll(
                () -> assertEquals(productId, product.id()),
                () -> assertTrue(
                        product.title() != null && !product.title().isBlank()
                ),
                () -> assertTrue(product.price() > 0.0),
                () -> assertTrue(product.rating().rate() >= 0.0),
                () -> assertTrue(product.rating().rate() <= 5.0)
        );
    }

    @Story("Missing product")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Return 404 for an unknown product")
    @Tag("regression")
    @Disabled("FKS-001: API returns 200 instead of 404")
    @Test
    void shouldReturnNotFoundForUnknownProduct() {
        int productId = 999999;

        given()
                .spec(requestSpec)
                .pathParam("productId", productId)
                .when()
                .get("/products/{productId}")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }

    @Tag("smoke")
    @Tag("regression")
    @DisplayName("Create a new product")
    @Test
    void shouldCreateProduct() {
        PostProduct product = new PostProduct(
                "Title",
                100,
                "desc",
                "cat",
                "image"
        );

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(product)
        .when()
                .post("/products")
        .then()
                .log().ifValidationFails()
                .statusCode(201)
                .body(
                        "title", equalTo(product.title()),
                        "id", greaterThan(0)
                );
    }

    @DisplayName("Return product list")
    @Tag("regression")
    @Test
    void shouldReturnProductList() {
        given()
                .spec(requestSpec)
        .when()
                .get("/products")
        .then()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}