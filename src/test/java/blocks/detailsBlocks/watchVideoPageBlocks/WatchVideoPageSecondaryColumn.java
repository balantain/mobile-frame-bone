package blocks.detailsBlocks.watchVideoPageBlocks;

import blocks.detailsBlocks.watchVideoPageBlocks.secondaryColumnItems.SecondaryColumnPlaylist;
import blocks.detailsBlocks.watchVideoPageBlocks.secondaryColumnItems.SecondaryColumnVideoCard;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import spring.annotations.Block;

@Block
@Getter
public class WatchVideoPageSecondaryColumn {

    @FindBy(xpath = "//div[@id='secondary' and @class='style-scope ytd-watch-flexy']")
    private WebElement self;

    //Video card in the list on the right side
    @Autowired
    SecondaryColumnVideoCard videoCard;
    @Autowired
    SecondaryColumnPlaylist playlist;

    /*
    Dialog Popup container
     */
    public WebElement getPopupContainer(){
        return getSelf().findElement(By.xpath(".//tp-yt-paper-dialog[@class='style-scope ytd-popup-container']"));
    }
    public WebElement getPopupContainerDismissButton(){
        return getSelf().findElement(By.xpath(".//ytd-button-renderer[@id='dismiss-button']"));
    }







}
