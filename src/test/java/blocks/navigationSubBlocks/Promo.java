package blocks.navigationSubBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
public class Promo{

    @FindBy(xpath = "//ytd-guide-signin-promo-renderer[@class='style-scope ytd-guide-renderer']")
    private WebElement self;

    @FindBy(xpath = "//ytd-guide-signin-promo-renderer[@class='style-scope ytd-guide-renderer']/yt-formatted-string")
    private WebElement textOfPromo;

    @FindBy(id = "sign-in-button")
    private WebElement btnSignIn;
}
