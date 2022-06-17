package appium.appiumTests;

import appium.appiumPages.MobileMainYoutubePage;
import appiumDriver.DriverManager;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Wait;

import java.util.List;
import java.util.Locale;

public class SearchTest extends BaseAppiumTest {

    @Test
    public void searchJava() {

        MobileMainYoutubePage mobileMainYoutubePage = new MobileMainYoutubePage();
        mobileMainYoutubePage.getBtnSearch().click();
        mobileMainYoutubePage.getSearchTextEdit().sendKeys("Java");
        mobileMainYoutubePage.getSearchTextEdit().sendKeys(Keys.ENTER);
        Wait.forMillis(10000);

        List<MobileElement> foundVideos = DriverManager.getAppiumDriver()
                .findElements(By.xpath("//h4[@class='compact-media-item-headline']"));
        SoftAssert softAssert = new SoftAssert();

        foundVideos.forEach(x -> {
            softAssert.assertTrue(x.getText().toLowerCase(Locale.ROOT).contains("java"));
        });
        softAssert.assertAll();
    }
}
