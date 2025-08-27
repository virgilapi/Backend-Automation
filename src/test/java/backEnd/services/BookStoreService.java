package backEnd.services;

import backEnd.ResponseCodes;
import backEnd.client.RestClient;
import backEnd.endPoints.Endpoints;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class BookStoreService {

    private final RestClient restClient;

    public BookStoreService(String baseUri) {
        this.restClient = new RestClient(baseUri);
    }

    private void validateResponse(Response response, int expectedStatus, String expectedStatusLine) {
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
        Assert.assertTrue(response.getStatusLine().contains(expectedStatusLine));
    }

    public void getAllBooks() {

        Response response = restClient.getRequestSpec().get(Endpoints.GET_ALL_BOOKS);
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        validateResponse(response, ResponseCodes.OK, ResponseCodes.OK_LINE);
    }
}
