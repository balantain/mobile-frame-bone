package blocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class AdvertisementBlock {

    @FindBy (xpath = "//div[@id='masthead-ad']/ytd-banner-promo-renderer[@class='style-scope ytd-rich-grid-renderer']")
    WebElement self;

    @FindBy(xpath = "//ytd-button-renderer[@id='dismiss-button']")
    private WebElement btnDismissAdvertisement;

    @FindBy (xpath = "//div[@id='masthead-ad']//tp-yt-paper-button[@id='button']")
    private WebElement btnSubscribePremium;
}
