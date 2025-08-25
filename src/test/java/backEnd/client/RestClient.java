package backEnd.client;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestClient {

    private final RequestSpecification requestSpec;

    public RestClient(String baseUri) {
        this.requestSpec = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json");
    }

    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }
}
