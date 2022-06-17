package utils;

import driver.SingletonDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSActions{
    public static WebElement scrollIntoView(WebElement webElement, boolean b){
        ((JavascriptExecutor) SingletonDriver.getDriver()).executeScript(String.format("arguments[0].scrollIntoView(%s);", b), webElement);
        Wait.forMillis(100);
        return webElement;
    }
}
