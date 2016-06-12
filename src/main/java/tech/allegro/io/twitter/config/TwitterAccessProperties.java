package tech.allegro.io.twitter.config;

import org.springframework.stereotype.Component;

@Component
public class TwitterAccessProperties {

    private final String consumerKey;
    private final String consumerSecret;
    private final String accessToken;
    private final String accessTokenSecret;

    public TwitterAccessProperties() {
        this.consumerKey = System.getenv("TWITTER_CONSUMER_KEY");
        this.consumerSecret= System.getenv("TWITTER_CONSUMER_SECRET");
        this.accessToken = System.getenv("TWITTER_ACCESS_TOKEN");
        this.accessTokenSecret = System.getenv("TWITTER_ACCESS_TOKEN_SECRET");
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }
}
