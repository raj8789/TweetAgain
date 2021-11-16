package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SendTweet {

    TwitterImpl twitterimpl;
    public SendTweet(TwitterImpl twitterimpl)
    {
        this.twitterimpl=twitterimpl;
    }
    public SendTweet(){

    }
}
