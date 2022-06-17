package blocks.baseElements;

import blocks.baseElements.checkers.CheckListOfToggles;
import driver.SingletonDriver;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Wait;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Getter
public class ListOfToggle<T extends Supplier<String>> implements CheckListOfToggles<T> {
    private final By by;

    @SneakyThrows
    public ListOfToggle(By by){
        this.by = by;
    }

    @SneakyThrows
    public List<Toggle> getList(){
        Wait.forMillis(1000);
        var elements = SingletonDriver.getDriver().findElements(by);
        return elements.stream().filter(WebElement::isDisplayed).map(Toggle::new).collect(toList());
    }
}
