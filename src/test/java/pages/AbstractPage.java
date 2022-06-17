package pages;

import driver.SingletonDriver;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public abstract class AbstractPage{

    protected boolean isElementExists(WebElement element){
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            System.out.printf("The element %s is not exists", element);
            return false;
        }
    }
}
