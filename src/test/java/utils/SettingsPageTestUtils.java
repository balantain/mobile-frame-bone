package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Wait.waitForVisibilityOfElement;

public class SettingsPageTestUtils {
    private static Logger log = LogManager.getRootLogger();

    public static void turnOnToggles(List<WebElement> toggles){
        for (WebElement element : toggles){
            log.info("Toggle " + element.getAttribute("aria-label") + " pressed??? == " + element.getAttribute("aria-pressed"));
            if (element.isDisplayed() && "false".equals(element.getAttribute("aria-pressed"))){
                waitForVisibilityOfElement(element).click();
                log.info("Toggle " + element.getAttribute("aria-label") + " clicked");
            }
        }
    }

    public static void turnOffToggles(List<WebElement> toggles){
        for (WebElement element : toggles){
            log.info("Toggle " + element.getAttribute("aria-label") + " pressed??? == " + element.getAttribute("aria-pressed"));
            if (element.isDisplayed() && "true".equals(element.getAttribute("aria-pressed"))){
                waitForVisibilityOfElement(element).click();
                log.info("Toggle " + element.getAttribute("aria-label") + " clicked");
            }
        }
    }
}
