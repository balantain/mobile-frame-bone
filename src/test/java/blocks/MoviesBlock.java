package blocks;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spring.annotations.Block;

import java.util.List;
import java.util.stream.Collectors;

@Block
@Getter
public class MoviesBlock {

    @FindBy(xpath = "//ytd-rich-grid-renderer[@class='style-scope ytd-two-column-browse-results-renderer']/div[@id='contents']")
    private WebElement self;

    public List<MovieBlock> getMovieBlock() {
        var list = self.findElements(By.xpath(".//ytd-rich-item-renderer[@class='style-scope ytd-rich-grid-row']"));
        return list.stream().map(i -> new MovieBlock(i)).collect(Collectors.toList());
    }
}

