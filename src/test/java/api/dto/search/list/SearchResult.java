package api.dto.search.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
        private String etag;
        private ResourceId id;
        private String kind;
        private SearchResultSnippet snippet;
}
