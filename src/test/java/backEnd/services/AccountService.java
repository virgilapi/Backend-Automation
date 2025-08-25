package backEnd.services;

import backEnd.ResponseCodes;
import backEnd.client.RestClient;
import backEnd.endPoints.Endpoints;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class AccountService {

    private final RestClient restClient;

    public AccountService(String baseUri) {
        this.restClient = new RestClient(baseUri);
    }

    private void validateResponse(Response response, int expectedStatus, String expectedStatusLine) {
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
        Assert.assertTrue(response.getStatusLine().contains(expectedStatusLine));
    }

    public String createUser(String username, String password) {
        System.out.println("=== Step 1: Create User ===");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        Response response = given()
                .spec(restClient.getRequestSpec())
                .body(requestBody.toString())
                .post(Endpoints.CREATE_USER);

        validateResponse(response, ResponseCodes.CREATED, ResponseCodes.CREATED_LINE);

        return response.path("userID");
    }

    public String generateToken(String username, String password) {
        System.out.println("=== Step 2: Generate Token ===");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        Response response = given()
                .spec(restClient.getRequestSpec())
                .body(requestBody.toString())
                .post(Endpoints.GENERATE_TOKEN);

        validateResponse(response, ResponseCodes.OK, ResponseCodes.OK_LINE);

        return response.path("token");
    }

    public void getUser(String userId, String token) {
        System.out.println("=== Step 3: Get User ===");

        Response response = given()
                .spec(restClient.getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .get(Endpoints.GET_USER.replace("{userId}", userId));

        validateResponse(response, ResponseCodes.OK, ResponseCodes.OK_LINE);
    }

    public void deleteUser(String userId, String token) {
        System.out.println("=== Step 4: Delete User ===");

        Response response = given()
                .spec(restClient.getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .delete(Endpoints.DELETE_USER.replace("{userId}", userId));

        validateResponse(response, ResponseCodes.NO_CONTENT, ResponseCodes.NO_CONTENT_LINE);
    }

    public void getUserUnauthorized(String userId) {
        System.out.println("=== Step 5: Get User (after deletion) ===");

        Response response = given()
                .spec(restClient.getRequestSpec())
                .get(Endpoints.GET_USER.replace("{userId}", userId));

        validateResponse(response, ResponseCodes.UNAUTHORIZED, ResponseCodes.UNAUTHORIZED_LINE);
    }
}
