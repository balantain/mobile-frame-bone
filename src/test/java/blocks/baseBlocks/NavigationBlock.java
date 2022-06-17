package blocks.baseBlocks;

import blocks.navigationSubBlocks.*;
import driver.SingletonDriver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import spring.annotations.Block;

@Block
@Getter
public class NavigationBlock{

    @FindBy(xpath = "//ytd-guide-renderer[@id='guide-renderer']")
    private WebElement self;

    @Autowired
    Main main;

    @Autowired
    UserHistory userHistory;

    @Autowired
    Channels channels;

    @Autowired
    BestOnYoutube bestOnYoutube;

    @Autowired
    OtherOpportunities otherOpportunities;

    @Autowired
    Advanced advanced;

    @Autowired
    Promo promo;

    public boolean blockIsDisplayed(){
        return SingletonDriver.getDriver().findElements(By.xpath("//ytd-guide-renderer[@id='guide-renderer']")).size() != 0;
    }
}
