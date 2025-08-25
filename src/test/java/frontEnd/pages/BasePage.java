package frontEnd.pages;

import helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;
    public ElementHelper elementHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }
}
