package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SingletonDriver{
    private static final int WAITING_TIME = 10;
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null){
            switch(System.getProperty("browser", "chrome")){
                case "firefox":{
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "explorer":{
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
                default:{
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(WAITING_TIME, TimeUnit.SECONDS).pageLoadTimeout(WAITING_TIME, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }

    public static void deleteCookies(){
        driver.manage().deleteAllCookies();
    }
}
