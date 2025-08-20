package sharedData;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SharedData {

    private WebDriver driver;
    private String browser;

    @BeforeMethod(alwaysRun = true)
    public void prepareEnv(){
        LoggerUtility.startTest(this.getClass().getSimpleName());

        browser = System.getProperty("browser");
        switch (browser){
            case "Chrome":
                ChromeBrowser chromeBrowser = new ChromeBrowser();
                chromeBrowser.openBrowser();
                driver = chromeBrowser.getDriver();
                break;
            case "Edge":
                EdgeBrowser edgeBrowser = new EdgeBrowser();
                edgeBrowser.openBrowser();
                driver = edgeBrowser.getDriver();
                break;
        }

        LoggerUtility.infoLog("The browser "+browser+" was opened with success");
    }

    @AfterMethod(alwaysRun = true)
    public void closeEnv(ITestResult result){
        if (result.getStatus() ==ITestResult.FAILURE){
            LoggerUtility.errorLog(result.getThrowable().getMessage());
        }

//        driver.quit();


        LoggerUtility.infoLog("The browser "+browser+" was closed with success");

        LoggerUtility.finishTest(this.getClass().getSimpleName());

    }

    public WebDriver getDriver() {
        return driver;
    }
}
