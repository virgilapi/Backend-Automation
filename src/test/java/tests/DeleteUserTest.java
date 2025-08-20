package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTest {

    @Test
    public void testMethod(){
        System.out.println("=== Step 1: Create User ===");
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName","VirgilTesting" + System.currentTimeMillis());
        requestBody.put("password","Parola123!");
        requestSpecification.body(requestBody.toString());

        Response response = requestSpecification.post("/Account/v1/User");
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertTrue(response.getStatusLine().contains("Created"));

        String userId = response.path("userID");

        System.out.println("=== Step 2: Generate Token ===");
        response = requestSpecification.post("/Account/v1/GenerateToken");

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.getStatusLine().contains("OK"));

        String token = response.path("token");

//        System.out.println("=== Step 3: Get User ===");
//        requestSpecification.header("Authorization", "Bearer "+token);
//        response = requestSpecification.get("/Account/v1/User/"+userId);
//
//        System.out.println(response.getStatusLine());
//        response.getBody().prettyPrint();
//
//        Assert.assertEquals(response.getStatusCode(),200);
//        Assert.assertTrue(response.getStatusLine().contains("OK"));


        System.out.println("=== Step 4: Delete User ===");
        requestSpecification.header("Authorization", "Bearer "+token);
        response = requestSpecification.delete("/Account/v1/User/"+userId);

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),204);
        Assert.assertTrue(response.getStatusLine().contains("No Content"));
    }
}
