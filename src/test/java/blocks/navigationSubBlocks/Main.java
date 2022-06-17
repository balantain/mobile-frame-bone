package blocks.navigationSubBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
public class Main{

    @FindBy(xpath = "//yt-formatted-string[text()='Главная']//ancestor::ytd-guide-section-renderer")
    private WebElement self;

    @FindBy(xpath = "//yt-formatted-string[text()='Главная']//ancestor::ytd-guide-section-renderer//a")
    private List<WebElement> elementsOfMainBlock;

    public enum MainValuesEnum implements Supplier<String>{

        MAIN("Главная"), NAVIGATOR("Навигатор"), SUBSCRIPTIONS("Подписки");

        private final String value;

        MainValuesEnum(String value){
            this.value = value;
        }

        @Override
        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(MainValuesEnum::get).collect(Collectors.toList());
        }
    }
}

