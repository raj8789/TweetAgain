package com.resource;

public class TweetPostRequest {
    String message;

    public TweetPostRequest() {

    }

    public TweetPostRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
