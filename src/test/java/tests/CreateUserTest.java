package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import sharedData.SharedData;

public class CreateUserTest extends SharedData {

    @Test
    public void testMethod(){

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

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginMethod(requestBody.get("userName").toString(),requestBody.get("password").toString());
    }
}
