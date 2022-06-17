package blocks.baseElements;

import blocks.baseElements.checkers.CheckListOfCheckbox;
import driver.SingletonDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class ListOfCheckbox<T extends Supplier<String>> implements CheckListOfCheckbox<T> {
    private final By by;

    @SneakyThrows
    public ListOfCheckbox(By by){
        this.by = by;
    }

    @SneakyThrows
    public List<Checkbox> getList(){
        var elements = SingletonDriver.getDriver().findElements(by);
        return elements.stream().filter(WebElement::isDisplayed).map(Checkbox::new).collect(toList());
    }
}
