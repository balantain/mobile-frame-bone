package blocks.baseElements.checkers;

import blocks.baseElements.Checkbox;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public interface CheckListOfCheckbox<T extends Supplier<String>> {
    List<Checkbox> getList();

    default CheckListOfCheckbox setCheckBoxActive(T... values){
        var valuesTexts = Arrays.stream(values).map(Supplier::get).collect(toList());
        getList().stream().filter(i -> valuesTexts.contains(i.getTitle().getText())).forEach(i -> i.set(true));
        return this;
    }
    default CheckListOfCheckbox setCheckboxInactive(T... values){
        var valuesTexts = Arrays.stream(values).map(Supplier::get).collect(toList());
        getList().stream().filter(i -> valuesTexts.contains(i.getTitle().getText())).forEach(i -> i.set(false));
        return this;
    }

    default CheckListOfCheckbox checkListValues(T... values){
        var textValues = Arrays.stream(values).map(Supplier::get).collect(toList());
        var checkboxTexts = getList().stream().map(Checkbox::getTitle).collect(toList());
        Assert.assertEquals(textValues, checkboxTexts);
        return this;
    }

    default CheckListOfCheckbox checkAll(){
        var checkboxList = getList().stream().map(i -> i.set(true)).collect(toList());
        return this;
    }

    default CheckListOfCheckbox uncheckAll(){
        var checkboxList = getList().stream().map(i -> i.set(false)).collect(toList());
        return this;
    }
}
