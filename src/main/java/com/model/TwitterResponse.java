package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwitterResponse {
    /**
     * .
     * Message of user
     */
    private String message;
    /**
     * .
     * User class object
     */
    private User user;
    /**
     * .
     * it takes date created
     */
    private String createdAt;

    /**
     * .
     * TwitterResponse returns the details of user who posted tweet.Details are as followed:
     *
     * @param messages         specifies tweet that has been posted.
     * @param twitterHandles   specifies ScreenName of user who posted the tweet.
     * @param names            specifies name of user who posted the tweet.
     * @param profileImageUrls specifies URL of profile image of user who posted the tweet.
     * @param createdat        specifies date and time tweet posted.
     */
    public TwitterResponse(final String messages, final String twitterHandles, final String names, final String profileImageUrls, final String createdat) {
        this.message = messages;
        this.user = new User(twitterHandles, names, profileImageUrls);
        this.createdAt = createdat;
    }

    /**
     * .
     * Constructor
     */
    public TwitterResponse() {
    }
}