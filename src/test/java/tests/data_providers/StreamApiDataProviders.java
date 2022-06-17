package tests.data_providers;

import api.util.ApiSearchBuilder;
import api.util.ApiSearchVideo;
import org.testng.annotations.DataProvider;
import service.WatchStreamService;
import service.WatchVideoService;
import spring.Spring;

public class StreamApiDataProviders {

    @DataProvider(name = "StreamSearchResults")
    public static Object[][] getApiFromStreamSearchResult(){
        ApiSearchVideo apiSearchVideo = new ApiSearchVideo();
        return new Object[][]{
                { apiSearchVideo.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                        .withMaxSearchResults("2")
                        .withSearchQuery("music")
                        .withType("video")
                        .withEventType("live")
                        .build())
                        .getItems()
                },
                { apiSearchVideo.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                        .withMaxSearchResults("2")
                        .withSearchQuery("news russia")
                        .withType("video")
                        .withEventType("live")
                        .build())
                        .getItems()
                }
        };
    }

    //*data provider of various type of content APIs
    @DataProvider(name="VideoAndApiSearchResults")
    public static Object[][] getApiFromSearchResults(){
        ApiSearchVideo apiSearchVideo = new ApiSearchVideo();
        return new Object[][]{
                {
                    Spring.get(WatchVideoService.class), apiSearchVideo.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                        .withMaxSearchResults("2")
                        .withSearchQuery("YURI THE")
                        .withType("video")
                        .build())
                        .getItems()
                },
                {
                    Spring.get(WatchStreamService.class), apiSearchVideo.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                        .withMaxSearchResults("2")
                        .withSearchQuery("news russia")
                        .withType("video")
                        .withEventType("live")
                        .build())
                        .getItems()
                }
        };
    }

    @DataProvider(name = "SingleStreamSearchResult")
    public static Object[][] getSingleApiFromStreamSearchResult(){
        ApiSearchVideo apiSearchVideo = new ApiSearchVideo();
        return new Object[][]{
                { apiSearchVideo.youtubeSearch(ApiSearchBuilder.BuilderApiQuery
                        .withMaxSearchResults("1")
                        .withSearchQuery("music")
                        .withType("video")
                        .withEventType("live")
                        .build())
                        .getItem()
                }
        };
    }
}
