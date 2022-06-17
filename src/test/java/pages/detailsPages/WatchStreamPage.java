package pages.detailsPages;

import blocks.baseElements.ChatMessage;
import driver.SingletonDriver;
import exceptions.TestFailureException;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.PageObject;

import java.util.List;
import java.util.stream.Collectors;

@PageObject
@Getter
public class WatchStreamPage extends AbstractWatchPage{
    @FindBy(xpath = "//yt-live-chat-text-input-field-renderer//div[@id='input']")
    private WebElement messageInput;

    @FindBy(id = "send-button")
    private WebElement sendMessageBtn;

    @FindBy(xpath = "//ytd-live-chat-frame//tp-yt-paper-button[@id='button']")
    private WebElement hideShowChatButton;

    @FindBy(id = "chatframe")
    private WebElement chatFrame;

    public List<ChatMessage> getMessageList() {

        return SingletonDriver.getDriver().findElements(By.xpath("//yt-live-chat-text-message-renderer")).stream().map(ChatMessage::new).collect(Collectors.toList());
    }

    public boolean isLiveMessageExist(String author, String message) {
        switchToLiveChat();
        boolean result = getMessageList().stream().filter(i -> i.isAuthor(author)).anyMatch(i -> i.isMessageContent(message));
        SingletonDriver.getDriver().switchTo().defaultContent();
        return result;
    }

    public void typeIntoLiveChat(String message){
        switchToLiveChat();
        getMessageInput().sendKeys(message);
        getSendMessageBtn().click();
        SingletonDriver.getDriver().switchTo().defaultContent();
    }

    public void switchToLiveChat() {
        if (getHideShowChatButton().isDisplayed()) {
            SingletonDriver.getDriver().switchTo().frame(chatFrame);
        } else {
            throw new TestFailureException("Chat is turned off in this stream");
        }
    }
}