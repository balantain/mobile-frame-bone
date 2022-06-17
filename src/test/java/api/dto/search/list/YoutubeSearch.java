package api.dto.search.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeSearch {
    private String etag;
    private String eventId;
    private List<SearchResult> items;
    private String kind;
    private String nextPageToken;
    private PageInfo pageInfo;
    private String prevPageToken;
    private String regionCode;
    private TokenPagination tokenPagination;
    private String visitorId;

    public SearchResult getItem(){
       return getItems().stream().findFirst().get();
    }
}
