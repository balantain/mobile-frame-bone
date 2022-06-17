package blocks.baseBlocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
@Getter
public class HeaderBlock{

    @Autowired
    SearchBlock searchBlock;

    @FindBy(xpath = "//div[@id='masthead-container']")
    private WebElement self;

    @FindBy(xpath = "//div[@id='start']//a[@id='logo']")
    protected WebElement logoIcon;

    @FindBy(xpath = "//div[@id='start']//yt-icon-button[@id='guide-button']")
    private WebElement btnGuide;

    @FindBy(xpath = "//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']")
    private WebElement btnSignIn;

    @FindBy(xpath = "//ytd-topbar-menu-button-renderer")
    private List<WebElement> topBarButtons; // if size>2 user is logged in

    @FindBy(xpath = "//img[@id='img'][@alt='Фото профиля']")
    private WebElement userLogo;

    @FindBy(xpath = "//ytd-topbar-menu-button-renderer//button[@aria-label='Настройки']")
    private WebElement dropDownMenuSettings;

    @FindBy(xpath = "//ytd-topbar-menu-button-renderer//button[@aria-label='Приложения YouTube']")
    private WebElement dropDownAppsYoutube;

    /*
    choose settings by text
     */
    @FindBy(xpath = "//ytd-multi-page-menu-renderer[@menu-style='multi-page-menu-style-type-system']" + "//ytd-compact-link-renderer")
    private List<WebElement> settingsYoutube;

    public enum YoutubeSettings implements Supplier<String>{

        THEME_SIMILAR_TO_PHONE("Тема: как на устройстве"),
        LANGUAGE("Язык:Русский"),
        COUNTRY("\nСтрана:\n  Беларусь"),
        SETTINGS("Настройки"),
        OWN_DATA("Личные данные на YouTube"),
        REFERENCE("Справка"),
        FEEDBACK("Отправить отзыв"),
        HOT_KEYS("Быстрые клавиши"),
        SAFE_MODE("Безопасный режим: откл.");

        private final String value;

        YoutubeSettings(String value){
            this.value = value;
        }

        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(YoutubeSettings::get).collect(Collectors.toList());
        }
    }

    /*
    choose youtubeApps by text
     */
    @FindBy(xpath = "//ytd-multi-page-menu-renderer[@menu-style='multi-page-menu-style-type-yt-apps']" + "//ytd-compact-link-renderer")
    private List<WebElement> youtubeApps;

    public enum YoutubeApps implements Supplier<String>{

        YOUTUBE_TV("YouTube TV"), YOUTUBE_MUSIC("YouTube Music"), YOUTUBE_FOR_KIDS("YouTube Детям"), YOUTUBE_FOR_MUSICIANS("YouTube для музыкантов");

        private final String value;

        YoutubeApps(String value){
            this.value = value;
        }

        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(YoutubeApps::get).collect(Collectors.toList());
        }
    }
}
