package service;

import api.dto.search.list.SearchResult;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import pages.detailsPages.WatchVideoPage;
import spring.annotations.PageObject;
import utils.Wait;

import static org.hamcrest.Matchers.*;


@PageObject
@Getter
public class WatchVideoService extends AbstractWatchService{

    @Autowired
    WatchVideoPage watchPage;

    @Override
    public void checkContentTypeAndTitles(SearchResult item) {
        super.checkContentTypeAndTitles(item);
        AssertService.softAssertThat("check if content type is video",
                () -> getWatchPage().getVideoPlayer().getTimeDisplay().getAttribute("class").contains("ytp-live"), equalTo(false));
    }

    public void checkPlayerButtonFunctionality(SearchResult item){
        Wait.waitForElementToBeClickable(getWatchPage().getVideoPlayer().getPlayerPlayPauseButton()).click();
        Wait.forMillis(5000);
        Wait.waitForElementToBeClickable(getWatchPage().getVideoPlayer().getPlayerPlayPauseButton()).click();
        AssertService.softAssertThat("check if player current time doesn't equal to 0:00",
                () -> getWatchPage().getVideoPlayer().getPlayerCurrentTime().getText(), not(equalToIgnoringCase("0:00")));
    }
}
