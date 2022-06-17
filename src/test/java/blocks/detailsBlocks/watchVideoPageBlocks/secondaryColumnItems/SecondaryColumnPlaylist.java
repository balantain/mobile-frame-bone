package blocks.detailsBlocks.watchVideoPageBlocks.secondaryColumnItems;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class SecondaryColumnPlaylist {

    @FindBy(xpath = "//div[@id='secondary-inner']/ytd-playlist-panel-renderer[@id='playlist']")
    private WebElement self;

    /*
    Playlist box in the secondary column
     */
    public WebElement getRemoveALLItemsFromPlaylist(){
        return getSelf().findElement(By.xpath(".//div[@id='end-actions']"));
    }
    //all items
    public WebElement getPlaylistBoxItems(){
        return getSelf().findElement(By.xpath(".//div[@id='items' and @class='playlist-items style-scope ytd-playlist-panel-renderer']"));
    }
    //specific item
    public WebElement getPlaylistBoxItem(){
        return getSelf().findElement(By.xpath(".//ytd-playlist-panel-video-renderer[@id='playlist-items']"));
    }
    public WebElement getPlaylistBoxItemThumbnail(){
        return getSelf().findElement(By.xpath(".//a[@id='thumbnail']"));
    }
    public WebElement getPlaylistBoxItemVideoTitle(){
        return getSelf().findElement(By.xpath(".//span[@id='video-title']"));
    }
    public WebElement getPlaylistItemAuthor(){
        return getSelf().findElement(By.xpath(".//span[@id='byline']"));
    }
}
