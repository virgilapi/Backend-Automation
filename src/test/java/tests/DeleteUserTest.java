package tests;

import backEnd.services.AccountService;
import org.testng.annotations.Test;
import frontEnd.pages.LoginPage;
import frontEnd.sharedData.SharedData;

public class DeleteUserTest extends SharedData {

    @Test
    public void testMethod(){
        AccountService accountService = new AccountService("https://demoqa.com");

        var username = "VirgilTesting" + System.currentTimeMillis();
        var password = "Parola123!";

        var userId = accountService.createUser(username, password);
        var token = accountService.generateToken(username, password);

        accountService.getUser(userId, token);
        accountService.deleteUser(userId, token);
        accountService.getUserUnauthorized(userId);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginMethod(username,password);
        loginPage.validetLoginMethod();

    }
}
