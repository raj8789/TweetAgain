package com.resource;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TweetPostRequest {
    /**
     * Takes the tweet message from the postman json body and sends to postTweet() to post a tweet on timeline.
     *
     * @param msg is tweet to be posted.
     */
    private String message;
    public TweetPostRequest() {
    }
    public TweetPostRequest(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
