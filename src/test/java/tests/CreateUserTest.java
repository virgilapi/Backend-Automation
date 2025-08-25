package tests;

import backEnd.services.AccountService;
import org.testng.annotations.Test;
import frontEnd.pages.LoginPage;
import frontEnd.sharedData.SharedData;

public class CreateUserTest extends SharedData {

    @Test
    public void testMethod(){

        AccountService accountService = new AccountService("https://demoqa.com");

        String username = "VirgilTesting" + System.currentTimeMillis();
        String password = "Parola123!";

        String userId = accountService.createUser(username, password);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginMethod(username,password);
    }
}
