package blocks.baseElements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JSActions;
import utils.Wait;

@Getter
public class RadioButton extends BaseElementBlock{
    public RadioButton(WebElement self) {
        super(self);
    }
    public WebElement getRadioButton(){
        return getSelf().findElement(By.id("radio"));
    }
    public WebElement getTitle(){
        return getSelf().findElement(By.id("label"));
    }
    public boolean isActive(){
        return this.getRadioButton().getAttribute("aria-checked").equals("true");
    }

    public RadioButton setActive(){
        if(!isActive() && getRadioButton().isDisplayed()) JSActions.scrollIntoView(getRadioButton(), false).click();
        Wait.forMillis(300);
        return this;
    }
}
