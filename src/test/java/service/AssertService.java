package service;

import driver.SingletonDriver;
import exceptions.TestFailureException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.LogUtils;
import utils.Wait;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AssertService {
    private static Logger log = LogManager.getRootLogger();
    private static List<Throwable> totalFailures = new ArrayList<>();

    public static <T> void softAssertThat(String description, Supplier<T> supplierForGettingActualValue, Matcher<? super T> matcher){
        Throwable testFailure = null;
        for (int i = 0; i < 5; i++){;
            testFailure = null;
            Wait.forMillis(1000);
            try {
                MatcherAssert.assertThat(description, supplierForGettingActualValue.get(), matcher);
                break;
            }
            catch (Exception | AssertionError ex){
                testFailure = ex;
            }
        }
        if (testFailure != null){
            totalFailures.add(testFailure);
            log.info("RP_MESSAGE#BASE64#{}#{}#{}",
                    ((TakesScreenshot) SingletonDriver
                            .getDriver())
                            .getScreenshotAs(OutputType.BASE64),
                    "Soft assert " + description + " failed!\n",
                    LogUtils.getShortLog(testFailure, 7));
        }
    }


    public static <T> void assertThat(String description, Supplier <T> actualValue, Matcher <? super T> matcher){
        try{
            MatcherAssert.assertThat(description, actualValue.get(), matcher);
        }
        catch (Exception | AssertionError ex){
            totalFailures.add(ex);
        }
    }

    /**
     do not use this method because it is automatically called in TestListener
     **/
    public static void assertAll() {
        if(!totalFailures.isEmpty()){
            totalFailures.clear();
            throw new TestFailureException("Test failed!");
        }
    }
}
