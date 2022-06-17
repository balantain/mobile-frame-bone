package blocks.baseBlocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class SearchBlock {

    @FindBy (id = "center")
    private WebElement self;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchField;

    @FindBy(xpath = "//a[@class='gsst_a']")
    private WebElement btnOpenVirtualKeyword;

    @FindBy(id = "search-icon-legacy")
    private WebElement btnSearch;

    @FindBy (id = "voice-search-button")
    protected WebElement btnVoiceSearch;

}
