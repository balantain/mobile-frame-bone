package blocks.detailsBlocks.watchVideoPageBlocks.secondaryColumnItems;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class SecondaryColumnVideoCard {


    //card
    @FindBy(xpath = "//ytd-compact-video-renderer[@class='style-scope ytd-watch-next-secondary-results-renderer']")
    private WebElement self;

    public WebElement getTumbnailImages(){
        return getSelf().findElement(By.xpath(".//a[@id='thumbnail']"));
    }
    public WebElement getVideoTitle(){
        return getSelf().findElement(By.xpath(".//span[@id='video-title']"));
    }
    public WebElement getChannelName(){
        return getSelf().findElement(By.xpath(".//yt-formatted-string[@class='style-scope ytd-channel-name']"));
    }
    public WebElement getViewCounter(){
        return getSelf().findElement(By.xpath(".//div[@id='metadata-line']//span[@class='style-scope ytd-video-meta-block'][1]"));
    }
    public WebElement getDateCounter(){
        return getSelf().findElement(By.xpath(".//div[@id='metadata-line']//span[@class='style-scope ytd-video-meta-block'][2]"));
    }
    public WebElement getThreeDotsMenu(){
        return getSelf().findElement(By.xpath(".//yt-icon[@class='style-scope ytd-menu-renderer']"));
    }
    public WebElement getAddToQueueLink(){
        return getSelf().findElement(By.xpath(".//tp-yt-paper-item[@class='style-scope ytd-menu-service-item-renderer']"));
    }

}
