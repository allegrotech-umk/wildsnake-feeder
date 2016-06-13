package tech.allegro.io.twitter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {

    private List<Media> media;

    @JsonCreator
    public Entities( @JsonProperty("media") List<Media> media) {
        this.media = media;
    }

    public List<Media> getMedia() {
        return media;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "media=" + media +
                '}';
    }
}
