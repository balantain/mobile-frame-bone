package blocks.baseBlocks;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
@Getter
public class FooterBlock{

    @FindBy(xpath = "//ytd-guide-renderer[@id='guide-renderer']//div[@id='footer']")
    private WebElement self;

    @FindBy(xpath = "//ytd-guide-renderer[@id='guide-renderer']//div[@id='footer']//div[@id='footer']//a")
    private List<WebElement> footerElements;

    public enum FooterValues implements Supplier<String>{
        ABOUT_SERVICE("О сервисе"),
        PRESS("Прессе"),
        COPYRIGHT("Авторские права"),
        CONTACT_US("Связаться с нами"),
        CREATORS("Авторам"),
        ADVERTISERS("Рекламодателям"),
        DEVELOPERS("Разработчикам"),
        TERMS("Условия использования"),
        PRIVACY("Конфиденциальность"),
        POLICIES("Правила и безопасность"),
        HOW_YOUTUBE_WORKS("Как работает YouTube"),
        TESTING_NEW_FUNCTIONS("Тестирование новых функций");

        private final String value;

        FooterValues(String value){
            this.value = value;
        }

        @Override
        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(FooterValues::get).collect(Collectors.toList());
        }
    }
}
