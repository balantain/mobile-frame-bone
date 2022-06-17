package blocks.baseElements;

import driver.SingletonDriver;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public abstract class BaseElementBlock{
    @FindBy(id = "")
    WebElement self;

    public BaseElementBlock(WebElement self) {
        PageFactory.initElements(SingletonDriver.getDriver(), this);
        this.self = self;
    }
}
