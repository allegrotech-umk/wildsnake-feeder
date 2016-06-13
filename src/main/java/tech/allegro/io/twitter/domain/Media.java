package tech.allegro.io.twitter.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {


    private String media_url;

    private String media_url_https;

    @JsonCreator
    public Media(@JsonProperty("media_url") String media_url, @JsonProperty("media_url_https") String media_url_https) {
        this.media_url = media_url;
        this.media_url_https = media_url_https;
    }


    public String getMedia_url() {
        return media_url;
    }

    public String getMedia_url_https() {
        return media_url_https;
    }

    @Override
    public String toString() {
        return "Media{" +
                "media_url='" + media_url + '\'' +
                ", media_url_https='" + media_url_https + '\'' +
                '}';
    }
}

