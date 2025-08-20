package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeBrowser implements Browser {

    private WebDriver driver;
    private EdgeOptions edgeOptions;


    @Override
    public void openBrowser() {
        configBrowser();
        driver = new EdgeDriver(edgeOptions);

        driver.get("https://demoqa.com/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Override
    public void configBrowser() {

        edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("window-size=1920,1080");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-extensions");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
