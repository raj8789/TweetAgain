package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTweets {
    TwitterImpl twitterimpl;
    Logger logger= LoggerFactory.getLogger(RetrieveTweets.class);
    public RetrieveTweets(TwitterImpl twitterimpl)
    {
            this.twitterimpl=twitterimpl;
    }
    public RetrieveTweets()
    {
    }

}
