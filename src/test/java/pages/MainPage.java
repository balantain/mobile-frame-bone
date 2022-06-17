package pages;

import blocks.*;
import blocks.baseBlocks.*;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import spring.annotations.PageObject;

@PageObject
@Getter
public class MainPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='masthead-container']/..")
    private WebElement self;

    @Autowired
    HeaderBlock headerBlock;
    @Autowired
    NavigationBlock navigationBlock;
    @Autowired
    MiniNavigationBlock miniNavigationBlock;
    @Autowired
    MoviesBlock moviesBlock;
    @Autowired
    FilterBlock filterBlock;
    @Autowired
    FooterBlock footerBlock;
    @Autowired
    AdvertisementBlock advertisementBlock;
    @Autowired
    AlertBlock alertBlock;

    public MiniNavigationBlock switchToMiniNavigationBlock() {
        if (!miniNavigationBlock.blockIsDisplayed()) {
            headerBlock.getBtnGuide().click();
        }
        return getMiniNavigationBlock();
    }

    public NavigationBlock switchToNavigationBlock() {
        if (!navigationBlock.getSelf().isDisplayed()) {
            headerBlock.getBtnGuide().click();
        }
        return getNavigationBlock();
    }
}
