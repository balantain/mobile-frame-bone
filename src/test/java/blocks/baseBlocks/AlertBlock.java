package blocks.baseBlocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class AlertBlock {

    @FindBy(id = "alert-banner")
    private WebElement self;

    @FindBy (xpath = "//div[@id='alert-banner']//yt-formatted-string[@id='alert-message']")
    private WebElement alertMessage;

    @FindBy (xpath = "//div[@id='alert-banner']//div[@id='dismiss-button']")
    private WebElement btnDismiss;

    @FindBy (xpath = "//div[@id='alert-banner']//div[@id='action-buttons']")
    private WebElement btnAction;

}
