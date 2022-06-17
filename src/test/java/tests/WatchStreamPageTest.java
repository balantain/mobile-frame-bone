package tests;

import api.dto.search.list.SearchResult;
import driver.SingletonDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import service.*;
import tests.data_providers.StreamApiDataProviders;

import java.util.List;

import static org.hamcrest.Matchers.*;

@Test(groups = "APItest")
public class WatchStreamPageTest extends BaseTest{

    @Autowired
    WatchStreamService watchStreamService;

    @Test(dataProvider = "SingleStreamSearchResult", dataProviderClass = StreamApiDataProviders.class)
    public void canPostAMessageInLiveChat(SearchResult item){
        String linkApi = String.format("%1$s/watch?v=%2$s", MAIN_URL, item.getId().getVideoId());
        String message = "hello to everybody";
        openUrl(linkApi);
        watchStreamService.checkLiveChatFunctionality(message);
    }

    @Test(dataProvider = "VideoAndApiSearchResults", dataProviderClass = StreamApiDataProviders.class)
    public void checkVideoAndStreamTitles(AbstractWatchService abstractWatchService, List<SearchResult> items){
        for( SearchResult item : items){
            String linkApi = String.format("%1$s/watch?v=%2$s", MAIN_URL, item.getId().getVideoId());
            openUrl(linkApi);
            abstractWatchService.checkContentTypeAndTitles(item);
        }
    }

    @Test(dataProvider = "StreamSearchResults", dataProviderClass = StreamApiDataProviders.class)
    public void ifApiSearchResultReturnsOnlyStreams(List<SearchResult> items){
        AssertService.softAssertThat("check if API search result returns only live items",
                ()-> items.stream().map(item -> item.getSnippet().getLiveBroadcastContent())
                        .filter(contentType -> contentType.equalsIgnoreCase("live")).count(), equalTo(2L));
    }
}
