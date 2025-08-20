package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeBrowser implements Browser{

    private WebDriver driver;
    private ChromeOptions chromeOptions;

    @Override
    public void openBrowser() {

        configBrowser();
        driver = new ChromeDriver(chromeOptions);

        driver.get("https://demoqa.com/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        LoggerUtility.startTest(this.getClass().getSimpleName());

    }

    @Override
    public void configBrowser() {

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");

    }

    public WebDriver getDriver() {
        return driver;
    }
}
