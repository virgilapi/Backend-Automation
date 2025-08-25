package frontEnd.pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
    @FindBy ( id = "name")
    private WebElement textElement;



    public void loginMethod(String usernameValue,String passwordValue){
        userNameElement.sendKeys(usernameValue);
        LoggerUtility.infoLog("The user enters the user name: "+usernameValue);
        passwordElement.sendKeys(passwordValue);
        LoggerUtility.infoLog("The user enters the password: "+passwordValue);
        elementHelper.ultraJSElement(loginButton);
        LoggerUtility.infoLog("The user click on the login button");
    }

    public void validetLoginMethod(){
        Assert.assertEquals(textElement.getText(),"Invalid username or password!");
    }



}
