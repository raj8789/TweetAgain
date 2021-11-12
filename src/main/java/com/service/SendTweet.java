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
    public  Status sendTweets(String args) throws TwitterException
    {
        Logger logger= LoggerFactory.getLogger(SendTweet.class);
        Twitter twitter=twitterimpl.getTwitterObject();
        Status status = null;
        try {
            if(args.length()!=0)
             status = twitter.updateStatus(args);
            else
                status=null;
        } catch (Exception e) {
            if(args.length()>280) {
                logger.error("Tweets Length Is to Long Need to be shorter");
                throw new TwitterException("Tweet needs to be a shorter");
            }
            if(status==null)
            {
                logger.error("Tweets is Duplicated");
                throw new TwitterException("Tweet is duplicate tweet");
            }
        }
        return status;
    }
}
