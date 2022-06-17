package utils;

import driver.SingletonDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.CheckReturnValue;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@ParametersAreNonnullByDefault
public class Wait{
    private final long startTimeNano;
    private final long timeoutNano;
    private static final int EXPLICIT_WAIT_TIMEOUT = 10;

    public Wait(long timeoutMs){
        startTimeNano = nanoTime();
        timeoutNano = MILLISECONDS.toNanos(timeoutMs);
    }

    @CheckReturnValue
    public boolean isTimeoutReached(){
        return nanoTime() - startTimeNano > timeoutNano;
    }

    public void sleep(long milliseconds){
        if(isTimeoutReached()) return;
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    /**
     * Sleep at least given number of milliseconds.
     * Default {@link Thread#sleep(long)} doesn't guarantee the sleep duration, it can awake earlier.
     *
     * @param milliseconds the number of milliseconds to sleep
     */
    public static void forMillis(long milliseconds){
        Wait stopwatch = new Wait(milliseconds);
        do{
            stopwatch.sleep(milliseconds);
        }while(!stopwatch.isTimeoutReached());
    }

    public static WebElement waitForVisibilityOfElement(WebElement webElement){
        return new WebDriverWait(SingletonDriver.getDriver(), EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void forAjax(){
        for(int i = 0; i < 100; i++){
            boolean ajaxIsComplete = (boolean) ((JavascriptExecutor) SingletonDriver.getDriver()).executeScript("return document.readyState == 'complete'");
            if(ajaxIsComplete) break;
            forMillis(500);
        }
    }

    public static List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElements){
        return new WebDriverWait(SingletonDriver.getDriver(), EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public static WebElement waitForElementToBeClickable(WebElement webElement){
        return new WebDriverWait(SingletonDriver.getDriver(), EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
