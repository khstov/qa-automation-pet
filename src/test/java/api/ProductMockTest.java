package api;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@WireMockTest
class ProductMockTest {

    @Test
    void shouldGetProductFromMock(WireMockRuntimeInfo wireMock) {
        stubFor(get(urlEqualTo("/products/1"))
                .willReturn(okJson("{\"id\":1,\"title\":\"Mock product\"}")));

        given()
                .baseUri("http://localhost:" + wireMock.getHttpPort())
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Mock product"));

                verify(1, getRequestedFor(urlEqualTo("/products/1")));
    }
}