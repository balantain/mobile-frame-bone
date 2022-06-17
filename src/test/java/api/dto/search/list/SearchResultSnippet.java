package api.dto.search.list;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultSnippet {

    private String channelId;
    private String channelTitle;
    private String description;
    private String liveBroadcastContent;

    //private DateTime publishedAt;
    private ThumbnailDetails thumbnails;

    private String title;
}
