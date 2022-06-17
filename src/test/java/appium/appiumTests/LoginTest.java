package appium.appiumTests;

import appium.appiumPages.AccountPage;
import appium.appiumPages.MobileMainYoutubePage;
import appiumDriver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.PropertyDataReader;

public class LoginTest extends BaseAppiumTest implements PropertyDataReader {

    @Test
    public void logInTest() {
        MobileMainYoutubePage mobileMainYoutubePage = new MobileMainYoutubePage();
        mobileMainYoutubePage.getBtnAccount().click();
        AccountPage accountPage = new AccountPage();
        accountPage.getLogIn().click();
        new WebDriverWait(DriverManager.getAppiumDriver(), 30)
                .until(ExpectedConditions.visibilityOf(accountPage.getInputEmail())).sendKeys(getUserCredentials("email"));
        accountPage.getNextButton().click();
        new WebDriverWait(DriverManager.getAppiumDriver(), 30)
                .until(ExpectedConditions.visibilityOf(accountPage.getInputPassword())).sendKeys(getUserCredentials("password"));
        accountPage.getNextButton().click();
        Assert.assertTrue(new WebDriverWait(DriverManager.getAppiumDriver(), 30)
                .until(ExpectedConditions.visibilityOf(mobileMainYoutubePage.getAccountIcon())).isDisplayed());
    }
}
