package blocks.baseElements;

import driver.SingletonDriver;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Getter
public class RadioButtonsBlock<T extends Supplier<String>>{
    private final List<RadioButton> buttons;

    @SneakyThrows
    public RadioButtonsBlock(By by){
        buttons = SingletonDriver.getDriver().findElements(by).stream().map(RadioButton::new).collect(toList());
    }

    @SneakyThrows
    public RadioButton getButton(T value){
        var button = buttons.stream().filter(i -> value.get().equals(i.getTitle().getText())).findFirst();
        if(button.isPresent()) return button.get();
        throw new IllegalArgumentException("Can't find button with name: " + value.get());
    }
}
