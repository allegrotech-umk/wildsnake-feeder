package tech.allegro.io.twitter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Twitt {
    private final String text;

    private final Entities entities;

    @JsonCreator
    public Twitt(@JsonProperty("text") String text, @JsonProperty("entities") Entities entities) {
        this.text = text;
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public Entities getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return "Twitt{" +
                "text='" + text + '\'' +
                ", entities=" + entities +
                '}';
    }
}
