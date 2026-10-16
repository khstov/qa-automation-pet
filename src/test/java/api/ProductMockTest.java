package api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@WireMockTest
@Epic("FakeStore API")
@Feature("Products")
@Owner("khstov")
class ProductMockTest {

    @Story("Get product from mock service")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Return a product from the mock service")
    @Tag("regression")
    @Test
    void shouldReturnProductFromMock(WireMockRuntimeInfo wireMock) {
        stubFor(get(urlEqualTo("/products/1"))
                .willReturn(okJson("{\"id\":1,\"title\":\"Mock product\"}")));

        given()
                .baseUri("http://localhost:" + wireMock.getHttpPort())
                .filter(new AllureRestAssured())
                .when()
                .get("/products/1")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("title", equalTo("Mock product"));

        verify(1, getRequestedFor(urlEqualTo("/products/1")));
    }
}
