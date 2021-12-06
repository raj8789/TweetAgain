/*** this class task to take tweet from user
 */
package com.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetPostRequest {
    /**
     * .
     * Takes the tweet message from the postman
     * json body and sends to postTweet() to post a tweet on timeline.
     *
     * @param msg is tweet to be posted.
     */
    private String message;

    /**
     * .
     * constructor
     */
    public TweetPostRequest() {
    }

    /**
     * .
     * Constructor takes message and store
     *
     * @param messages
     */
    public TweetPostRequest(final String messages) {
        this.message = messages;
    }

    /**
     * @return retrun tweet
     */
    public String getMessage() {
        return message;
    }
}
