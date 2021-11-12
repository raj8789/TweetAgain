package com.resource;

import com.service.RetrieveTweets;
import com.service.SendTweet;
import com.service.TwitterImpl;

public class TweetPostRequest {
    String message;
    TwitterImpl twitterImpl;
   public TweetPostRequest(TwitterImpl twitterImpl) {
       this.twitterImpl=twitterImpl;
    }
    public TweetPostRequest()
    {}
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public SendTweet getSendTweetObject(TwitterImpl twitterImpl)
    {
        return new SendTweet(twitterImpl);
    }
    public RetrieveTweets getRetrieveTweetsObject(TwitterImpl twitterImpl)
    {
        return new RetrieveTweets(twitterImpl);
    }
}
