package blocks.settingsPageBlocks;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Block
@Getter
public class SettingsBlock{

    @FindBy(xpath = "//ytd-settings-sidebar-renderer[@class='style-scope ytd-browse']")
    private WebElement self;

    public WebElement getTitle(){
        return getSelf().findElement(By.xpath("//div[text()='Настройки']"));
    }

    @FindBy(xpath = "//ytd-compact-link-renderer")
    private List<WebElement> menuSections;

    public WebElement getSetting(SettingsValues setting){
        var settingValue = menuSections.stream().filter(webElement -> setting.getValue().equals(webElement.getText())).findFirst();
        if(settingValue.isPresent()) return settingValue.get();
        throw new IllegalArgumentException("Can't find settings element with name: " + setting.getValue());
    }

    @Getter
    public enum SettingsValues{
        ACCOUNT("Аккаунт"),
        NOTIFICATIONS("Уведомления"),
        PLAYBACK("Воспроизведение"),
        PRIVACY("Конфиденциальность"),
        CONNECTED_APPS("Связанные приложения"),
        BILLING("Расчеты и платежи"),
        ADVANCED("Расширенные настройки");

        private final String value;

        SettingsValues(String value){
            this.value = value;
        }

        public static List<String> getSettingsStringList(){
            return Arrays.stream(values()).map(SettingsValues::getValue).collect(Collectors.toList());
        }
    }
}
