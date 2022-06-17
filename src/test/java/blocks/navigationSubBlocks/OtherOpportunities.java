package blocks.navigationSubBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Block
public class OtherOpportunities{

    @FindBy(xpath = "//yt-formatted-string[text()='Другие возможности']//ancestor::ytd-guide-section-renderer")
    private WebElement self;

    @FindBy(xpath = "//yt-formatted-string[text()='Другие возможности']//ancestor::ytd-guide-section-renderer//a")
    private List<WebElement> elementsOfOpportunities;

    public enum OtherOpportunitiesValuesEnum implements Supplier<String>{

        CHANNELS("Каталог каналов");

        private final String value;

        OtherOpportunitiesValuesEnum(String value){
            this.value = value;
        }

        @Override
        public String get(){
            return value;
        }

        public static List<String> getTexts(){
            return Arrays.stream(values()).map(OtherOpportunitiesValuesEnum::get).collect(Collectors.toList());
        }
    }
}
