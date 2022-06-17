package tests;


import api.dto.search.list.SearchResult;
import api.util.ApiSearchBuilder;
import api.util.ApiSearchVideo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.AbstractWatchService;
import service.WatchStreamService;
import service.WatchVideoService;
import spring.Spring;

import java.util.List;

@Test(groups = "UItest")
public class WatchVideoPageTest extends BaseTest {

    @DataProvider(name = "WatchVideoClassAndVideoApi")
    public Object[][] createDataAndGetAPIVideo() {
        ApiSearchVideo apiSearchVideoAssured = new ApiSearchVideo();
        return new Object[][]
                {
                        { Spring.get(WatchVideoService.class),
                                apiSearchVideoAssured.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                                        .withMaxSearchResults("2")
                                        .withSearchQuery("YURI THE")
                                        .withType("video")
                                        .build())
                                .getItems()},
                };
    }

    @Test(dataProvider = "WatchVideoClassAndVideoApi")
    public void clickOnPlayButton(WatchVideoService watchVideoService, List<SearchResult> items){
        for (SearchResult searchResult:items) {
            String linkAPI = String.format("%1$s/watch?v=%2$s", MAIN_URL, searchResult.getId().getVideoId());
            openUrl(linkAPI);
            watchVideoService.checkPlayerButtonFunctionality(searchResult);
        }
    }

    @DataProvider(name = "AbstractClassAndVariousSearchResults")
    public Object[][] createDataAndGetAPIVideoList() {
        ApiSearchVideo apiSearchVideoAssured = new ApiSearchVideo();
        return new Object[][]
                {
                        { Spring.get(WatchVideoService.class), apiSearchVideoAssured
                                .youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                                        .withMaxSearchResults("2")
                                        .withSearchQuery("YURI THE")
                                        .withType("video")
                                        .build())
                                .getItems()},

                        { Spring.get(WatchStreamService.class), apiSearchVideoAssured.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                                .withMaxSearchResults("2")
                                .withSearchQuery("news russia")
                                .withType("video")
                                .withEventType("live")
                                .build())
                                .getItems()},
                };
    }

    @Test(dataProvider = "AbstractClassAndVariousSearchResults")
    public void baseApiTestWithListOfVideos(AbstractWatchService abstractWatchService, List<SearchResult> item) {
        for (SearchResult searchResult:item) {
            String linkAPI = String.format("%1$s/watch?v=%2$s", MAIN_URL, searchResult.getId().getVideoId());
            openUrl(linkAPI);
            abstractWatchService.checkVideoTitle(searchResult);
        }
    }
}
