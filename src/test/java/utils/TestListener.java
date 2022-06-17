package utils;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import driver.SingletonDriver;
import exceptions.TestFailureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import service.AssertService;

public class TestListener extends ReportPortalTestNGListener {

    private Logger log = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart(iTestResult);
        log.info("Test " + iTestResult.getName() + " started successfully");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try{
            AssertService.assertAll();
            log.info("Test " + iTestResult.getName() + " passed successfully");
            super.onTestSuccess(iTestResult);
        } catch (TestFailureException exception){
            iTestResult.setStatus(ITestResult.FAILURE);
            iTestResult.setThrowable(exception);
            onTestFailure(iTestResult);
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("RP_MESSAGE#BASE64#{}#{}",
                ((TakesScreenshot) SingletonDriver
                        .getDriver())
                        .getScreenshotAs(OutputType.BASE64),
                "Test " + iTestResult.getName() + " failed");
        try{
            AssertService.assertAll();
        } catch (TestFailureException ignored){

        }
        finally {
            super.onTestFailure(iTestResult);
        }
    }
}