package blocks.baseElements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Wait;

@Getter
public class Checkbox extends BaseElementBlock{
    public Checkbox(WebElement self) {
        super(self);
    }

    public WebElement getCheckbox(){
        return getSelf().findElement(By.id("checkbox"));
    }
    public WebElement getTitle(){
        return getSelf().findElement(By.id("label"));
    }

    public boolean isActive(){
        return this.getCheckbox().getAttribute("aria-checked").equals("true");
    }

    public Checkbox set(boolean value){
        if(isActive() ^ value) getCheckbox().click();
        Wait.forMillis(500);
        return this;
    }
}
