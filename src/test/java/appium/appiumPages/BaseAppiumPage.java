package appium.appiumPages;

import appiumDriver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseAppiumPage {

    public BaseAppiumPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getAppiumDriver()), this);
    }
}
