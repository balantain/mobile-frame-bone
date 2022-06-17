package appium.appiumPages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AccountPage extends BaseAppiumPage {

    @FindBy(xpath = "//a[contains(@href,'https://accounts.google.com')]")
    private WebElement logIn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//span[text()='Далее' or text()='Next']")
    WebElement nextButton;
}