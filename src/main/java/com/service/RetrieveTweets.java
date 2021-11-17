package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
