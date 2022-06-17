package blocks.navigationSubBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
public class BestOnYoutube{

    @FindBy(xpath = "//yt-formatted-string[text()='Лучшее на YouTube']//ancestor::ytd-guide-section-renderer")
    private WebElement self;

    @FindBy(xpath = "//yt-formatted-string[text()='Лучшее на YouTube']//ancestor::ytd-guide-section-renderer//a")
    private WebElement elementsOfBestOnYoutube;

    public enum BestOnYoutubeValues implements Supplier<String>{

        MUSIC("Музыка"), SPORT("Спорт"), GAMES("Видеоигры"), NEWS("Новости"), TRANSLATIONS("Трансляции"), PANORAMIC_VIDEOS("Панорамные видео");

        private final String value;

        BestOnYoutubeValues(String value){
            this.value = value;
        }

        @Override
        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(BestOnYoutubeValues::get).collect(Collectors.toList());
        }
    }
}
