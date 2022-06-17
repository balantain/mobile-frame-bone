package tests;

import driver.SingletonDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import pages.MainPage;
import service.LoginService;
import service.PropertyDataReader;
import spring.Spring;
import spring.SpringConfig;
import utils.TestListener;
import utils.Wait;

@Listeners(TestListener.class)
@ContextConfiguration(classes = {SpringConfig.class, Spring.class})
public class BaseTest extends AbstractTestNGSpringContextTests implements PropertyDataReader{
    protected final String MAIN_URL = getUrl(MAIN);

    @Autowired
    MainPage page;

    @Autowired
    LoginService loginService;

    protected final Logger log = LogManager.getRootLogger();

    protected void openUrl(String url){
        SingletonDriver.getDriver().get(url);
        Wait.forAjax();
        loginService.logInWithTestCredentials();
        Wait.forAjax();
    }

    @BeforeMethod(alwaysRun = true)
    public void configurePage() {
        SingletonDriver.getDriver().get(MAIN_URL);
    }

    @AfterTest(alwaysRun = true)
    public void configureSuite() {
        SingletonDriver.deleteCookies();
    }

    @AfterSuite(alwaysRun = true)
    public void deleteSuite() {
        SingletonDriver.closeDriver();
    }

}
