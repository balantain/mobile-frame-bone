package appium.appiumTests;

import appiumDriver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class BaseAppiumTest {

    @BeforeTest
    public void createSession() {
        DriverManager.getAppiumDriver().get("https://www.youtube.com");
    }

    @AfterMethod(alwaysRun = true)
    public void closeSession() {
        DriverManager.closeAppiumDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
