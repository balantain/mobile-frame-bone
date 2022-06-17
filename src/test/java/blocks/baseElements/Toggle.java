package blocks.baseElements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JSActions;
import utils.Wait;


@Getter
public class Toggle extends BaseElementBlock{
    public Toggle(WebElement self) {
        super(self);
    }

    public WebElement getToggle(){
        return getSelf().findElement(By.id("toggle"));
    }
    public WebElement getTitle(){
        return getSelf().findElement(By.id("title"));
    }
    public WebElement getSubtitle(){
        return getSelf().findElement(By.id("subTitle"));
    }

    public boolean isActive(){
        return getToggle().getAttribute("aria-pressed").equals("true");
    }

    public Toggle set(boolean value){
        if(isActive() ^ value && getToggle().isDisplayed()) JSActions.scrollIntoView(getToggle(), false).click();
        Wait.forMillis(100);
        return this;
    }
}