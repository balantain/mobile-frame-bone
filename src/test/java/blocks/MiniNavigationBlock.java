package blocks;

import driver.SingletonDriver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
@Getter
public class MiniNavigationBlock{

    @FindBy(xpath = "//ytd-mini-guide-renderer[@role='navigation']")
    private WebElement self;

    @FindBy(xpath = "//ytd-mini-guide-renderer[@role='navigation']//a")
    private List<WebElement> miniNavigationElements;

    public enum MiniNavigationValuesEnum implements Supplier<String>{
        MAIN("Главная"), NAVIGATOR("Навигатор"), SUBSCRIPTIONS("Подписки"), LIBRARY("Библиотека"), HISTORY("История");

        private final String value;

        MiniNavigationValuesEnum(String value){
            this.value = value;
        }

        @Override
        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(MiniNavigationValuesEnum::get).collect(Collectors.toList());
        }
    }

    public boolean blockIsDisplayed(){
        return SingletonDriver.getDriver().findElements(By.xpath("//ytd-mini-guide-renderer[@role='navigation']")).size() != 0;
    }
}
