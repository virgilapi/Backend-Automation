package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import sharedData.SharedData;

public class DeleteUserTest extends SharedData {

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


        System.out.println("=== Step 3: Get User ===");
        requestSpecification.header("Authorization", "Bearer "+token);
        response = requestSpecification.get("/Account/v1/User/"+userId);

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.getStatusLine().contains("OK"));


        System.out.println("=== Step 4: Delete User ===");
//        requestSpecification.header("Authorization", "Bearer "+token);
        response = requestSpecification.delete("/Account/v1/User/"+userId);

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),204);
        Assert.assertTrue(response.getStatusLine().contains("No Content"));

        System.out.println("=== Step 5: Get User ===");
//        requestSpecification.header("Authorization", "Bearer "+token);
        response = requestSpecification.get("/Account/v1/User/"+userId);

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(),401);
        Assert.assertTrue(response.getStatusLine().contains("Unauthorized"));

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginMethod(requestBody.get("userName").toString(),requestBody.get("password").toString());
        loginPage.validetLoginMethod();

//
//        System.out.println("=== Step 3: Delete User ===");
//        Response deleteResponse = RestAssured.given()
//                .baseUri("https://demoqa.com")
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + token)
//                .delete("/Account/v1/User/" + userId);
//
//        System.out.println(deleteResponse.getStatusLine());
//        deleteResponse.getBody().prettyPrint();
//
//        Assert.assertEquals(deleteResponse.getStatusCode(), 204);
//        Assert.assertTrue(deleteResponse.getStatusLine().contains("No Content"));


    }
}
