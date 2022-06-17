package service;

import api.dto.search.list.SearchResult;
import lombok.Getter;
import pages.detailsPages.AbstractWatchPage;
import spring.annotations.PageObject;
import utils.Wait;

import static org.hamcrest.Matchers.containsString;

@PageObject
@Getter
public abstract class AbstractWatchService {

    protected abstract <T extends AbstractWatchPage> T getWatchPage();

    public void checkVideoTitle(SearchResult item){
        String videoTitle = item.getSnippet().getTitle();
        AssertService.softAssertThat("check video title",
                () -> getWatchPage().getPrimaryInfoBox().getInfoTitle().getText(), containsString(videoTitle));
    }

    public void checkChannelTitle(SearchResult item){
        String channelTitle = item.getSnippet().getChannelTitle();
        AssertService.softAssertThat("check channel title",
                () -> getWatchPage().getSecondaryInfoBox().getAuthorChannelTitle().getText(), containsString(channelTitle));
    }

    public void checkContentTypeAndTitles(SearchResult item){
        checkVideoTitle(item);
        checkChannelTitle(item);
    }
}
