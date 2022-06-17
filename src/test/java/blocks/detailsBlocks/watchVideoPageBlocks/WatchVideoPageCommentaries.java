package blocks.detailsBlocks.watchVideoPageBlocks;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class WatchVideoPageCommentaries {

    /*
    Commentaries
     */
//    @FindBy(xpath = "//ytd-comments[@id='comments']")
//    private WebElement commentariesMainBlock;

    @FindBy(xpath = "//ytd-comments[@id='comments']")
    private WebElement self;

    public WebElement getcommentariesCounter(){
        //or h2[@class='style-scope ytd-comments-header-renderer']
        return getSelf().findElement(By.xpath(".//span[@class='style-scope yt-formatted-string']/ancestor::h2"));
    }
    public WebElement getcommentBody(){
        return getSelf().findElement(By.xpath(".//div[@id='body']"));
    }
    public WebElement getcommentAuthorThumbnail(){
        return getSelf().findElement(By.xpath(".//div[@id='body']/child::div[@id='author-thumbnail']"));
    }
    //name and comment date
    public WebElement getcommentAuthorHeader(){
        return getSelf().findElement(By.xpath(".//div[@id='body']/descendant::div[@id='header-author']"));
    }
    public WebElement getcommentContentText(){
        return getSelf().findElement(By.xpath(".//div[@id='body']/descendant::div[@id='content']"));
    }
    public WebElement getcommentVoteCounter(){
        return getSelf().findElement(By.xpath(".//div[@id='body']/descendant::span[@id='vote-count-left']]"));
    }
    public WebElement getcommentExpandReplies(){
        return getSelf().findElement(By.xpath(".//div[@id='body']/preceding::div[@id='replies']"));
    }
}
