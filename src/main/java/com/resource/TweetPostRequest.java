package com.resource;
import com.service.TwitterImpl;

public class TweetPostRequest {
    String message;
    TwitterImpl twitterImpl;
    public TweetPostRequest()
    {}
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
