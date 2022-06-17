package api.util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ApiSearchBuilder {

    private final Map<String, String> apiMap = new HashMap<>();

    public ApiSearchBuilder(Map<String, String> values){
        apiMap.put("key", "AIzaSyDI5tHCkQ2fAPSIicVuTU8uWemq-VqBu6Q");
        apiMap.put("part", "id,snippet");
        apiMap.putAll(values);
    }

    @Getter
    public static class BuilderApiQuery {
        private static Map<String, String> apiMapInside = new HashMap<>();

        BuilderApiQuery() {
        }

        public BuilderApiQuery withSearchQuery(String value){
            apiMapInside.putAll(Map.of("q", value));
            return this;
        }
        public BuilderApiQuery withType(String value){
            apiMapInside.putAll(Map.of("type", value));
            return this;
        }

        public BuilderApiQuery withEventType(String value){
            apiMapInside.put("eventType", value);
            return this;
        }

        public static BuilderApiQuery withMaxSearchResults(String value){
            apiMapInside.putAll(Map.of("maxResults", value));
            return new BuilderApiQuery();
        }

        public BuilderApiQuery with(Map<String, String> value){
            apiMapInside.putAll(value);
            return this;
        }

        public Map<String, String> build(){
            return new ApiSearchBuilder(apiMapInside).getApiMap();
        }
    }
}
