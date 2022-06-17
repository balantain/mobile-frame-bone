package blocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MovieBlock {

    WebElement self;

    public MovieBlock(WebElement self) {
        this.self = self;
    }

    @FindBy (id = "video-title")
    private WebElement titleOfMovie;

    @FindBy (xpath = "//a[@id='thumbnail']//img[@id='img']")
    private WebElement imageOfMovie;

    @FindBy (id = "avatar")
    private WebElement avatar;

    @FindBy (xpath = "//*[@id='avatar-link']//img")
    private WebElement imageOfAvatar;

    @FindBy (id = "channel-name")
    private WebElement channelName;

    @FindBy (xpath = "//div[@id='metadata-line']/span[1]")
    private WebElement numberOfViews;

    @FindBy (xpath = "//div[@id='metadata-line']/span[2]")
    private WebElement dateOfRelease;
}

