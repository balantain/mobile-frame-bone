package service;

import api.dto.search.list.SearchResult;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import pages.detailsPages.WatchStreamPage;
import spring.annotations.PageObject;

import static org.hamcrest.Matchers.equalTo;

@PageObject
@Getter(value = AccessLevel.PROTECTED)
public class WatchStreamService extends AbstractWatchService{

    @Autowired
    protected WatchStreamPage watchPage;

    public void checkLiveChatFunctionality(String message){
        watchPage.typeIntoLiveChat(message);
        AssertService.softAssertThat("check if message is posted",
                ()-> getWatchPage().isLiveMessageExist("AT", message), equalTo(true));
    }

    @Override
    public void checkContentTypeAndTitles(SearchResult item) {
        super.checkContentTypeAndTitles(item);
        AssertService.softAssertThat("check if content type is stream",
                () -> getWatchPage().getVideoPlayer().getTimeDisplay().getAttribute("class").contains("ytp-live"), equalTo(true));
    }
}
