package pages;

import helpers.ElementHelper;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy ( id = "userName")
    private WebElement userNameElement;
    @FindBy ( id = "password")
    private WebElement passwordElement;
    @FindBy ( id = "login")
    private WebElement loginButton;



    public void loginMethod(String usernameValue,String passwordValue){
        userNameElement.sendKeys(usernameValue);
        LoggerUtility.infoLog("The user enters the user name: "+usernameValue);
        passwordElement.sendKeys(passwordValue);
        LoggerUtility.infoLog("The user enters the password: "+passwordValue);
        loginButton.click();
        LoggerUtility.infoLog("The user click on the login button");
    }



}
