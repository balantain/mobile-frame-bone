package blocks.baseBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

@Block
@Getter
public class FilterBlock {

    @FindBy(xpath = "//ytd-feed-filter-chip-bar-renderer[@class='style-scope ytd-rich-grid-renderer']")
    WebElement self;

    @FindBy (xpath = "//div[@id='right-arrow-button']//button[@id='button']")
    private WebElement btnScrollRight;

    @FindBy (xpath = "//div[@id='left-arrow-button']//button[@id='button']")
    private WebElement btnScrollLeft;

    @FindBy (xpath = "//ytd-feed-filter-chip-bar-renderer[@class='style-scope ytd-rich-grid-renderer']" +
            "//iron-selector/yt-chip-cloud-chip-renderer/yt-formatted-string" )
    private List<WebElement> filters;

    public enum Filters implements Supplier<String> {

        ALL("Все"),
        HOME_AND_RENOVATION("Дом и ремонт"),
        CRAFTS("Ремесла"),
        REALTY("Недвижимость"),
        COCKING_SHOWS("Кулинарные шоу"),
        MATH("Математика"),
        GAMES("Видеоигры"),
        SKETCH_SHOW("Скетч-шоу"),
        TOURISM("Туристические направления"),
        MUSIC("Музыка"),
        CARTOONS("Мультфильмы"),
        LIVE("Сейчас в эфире"),
        FOOTBALL("Футбол"),
        RAP("Рэп"),
        CULINARY("Кулинария"),
        FIGURATIVE_ART("Изобразительное искусство"),
        PETS("Домашние животные"),
        ACTIONS_AND_ADVENTURE("Экшен и приключения"),
        LAST_POSTED_VIDEO("Последние опубликованные видео");

        private final String value;

        Filters(String value) {
            this.value = value;
        }

        @Override
        public String get() {
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(Filters::get).collect(Collectors.toList());
        }
    }
}
