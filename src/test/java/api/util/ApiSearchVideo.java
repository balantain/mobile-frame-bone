package api.util;


import api.dto.search.list.YoutubeSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class ApiSearchVideo {

    /**
     * Receiving query parameters from ApiParametersMaps
     * @return Youtube search results class. "Part" param can be: id (returns video id), snippet (returns title, description, thumbnail image link)
     */
    public YoutubeSearch youtubeSearch(Map<String, String> map){
        Response response =
                given()
                .accept(ContentType.JSON)
                .queryParams(map)
                .when()
                .get("https://www.googleapis.com/youtube/v3/search")
                .then()
//                .log()
//                .body()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        return response.as(YoutubeSearch.class);
    }
}
