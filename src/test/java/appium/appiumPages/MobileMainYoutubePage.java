package appium.appiumPages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MobileMainYoutubePage extends BaseAppiumPage {

    @FindBy(xpath = "//div[@class='mobile-topbar-header-content non-search-mode cbox']" +
            "/button[@class='icon-button topbar-menu-button-avatar-button']")
    private WebElement btnSearch;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchTextEdit;

    @FindBy(xpath = "//ytm-topbar-menu-button-renderer/button[@class='icon-button topbar-menu-button-avatar-button']")
    private WebElement btnAccount;

    @FindBy(xpath = "//ytm-profile-icon[@class='topbar-menu-button-avatar']")
    private WebElement accountIcon; //if element is enabled account is logged in
}
