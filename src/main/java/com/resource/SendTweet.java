package com.resource;

import com.config.TWConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

public class SendTweet {
    public static Status sendTweets(String args)throws TwitterException {
        ConfigurationBuilder configurationBuilder= TWConfiguration.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        Status status = null;
        try {
            status = twitter.updateStatus(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
