package blocks.baseElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ChatMessage extends BaseElementBlock{

    public ChatMessage(WebElement self) {
        super(self);
    }

    public WebElement getAuthor(){
        return getSelf().findElement(By.id("author-name"));
    }
    public boolean isAuthor(String author){
        return getAuthor().getText().equalsIgnoreCase(author);
    }
    public boolean isMessageContent(String message){
        return getMessageContent().getText().equalsIgnoreCase(message);
    }

    public WebElement getMessageContent(){
        return getSelf().findElement(By.id("message"));
    }


}
