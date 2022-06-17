package blocks.baseElements.checkers;

import blocks.baseElements.Toggle;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.Assert;
import utils.Wait;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public interface CheckListOfToggles<T extends Supplier<String>>{
    List<Toggle> getList();

    default CheckListOfToggles setToggleActive(T... values){
        var valuesTexts = Arrays.stream(values).map(i -> i.get()).collect(toList());
        getList().stream().filter(i -> valuesTexts.contains(i.getTitle().getText())).forEach(i -> i.set(true));
        return this;
    }
    default CheckListOfToggles setToggleInactive(T... values){
        var valuesTexts = Arrays.stream(values).map(i -> i.get()).collect(toList());
        getList().stream().filter(i -> valuesTexts.contains(i.getTitle().getText())).forEach(i -> i.set(false));
        return this;
    }

    default CheckListOfToggles checkListValues(T... values){
        var textValues = Arrays.stream(values).map(Supplier::get).collect(toList());
        var toggleTexts = getList().stream().map(Toggle::getTitle).collect(toList());
        Assert.assertEquals(textValues, toggleTexts);
        return this;
    }

    default CheckListOfToggles checkAll(){
        for (int i = 0; i < 10; i++){
            var visibleToggles = getList();
            var activeToggles = getList().stream().filter(Toggle::isActive).collect(toList());
            if (activeToggles.size() == visibleToggles.size()) {
                break;
            }
            else {
                visibleToggles.forEach(j -> j.set(true));
                Wait.forAjax();
            }
        }
        return this;
    }

    default CheckListOfToggles uncheckAll(){
        for (int i = 0; i < 10; i++){
            var activeToggles = getList().stream().filter(Toggle::isActive).collect(toList()); //10
            if (activeToggles.size()!=0){
                try {
                    getList().forEach(j -> j.set(false));
                    Wait.forAjax();
                } catch (ElementNotInteractableException ignored) {} // in case toggle disappear
            } else {
                break;
            }
        }
        return this;
    }
}