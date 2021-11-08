package com.resource;

import com.config.TWConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class SendTweet {
    TWConfiguration twConfiguration;
    public SendTweet(TWConfiguration twConfiguration)
    {
        this.twConfiguration=twConfiguration;
    }
    public SendTweet()
    {

    }
    public  Status sendTweets(String args) throws TwitterException {
        TWConfiguration twConfiguration=new TWConfiguration();
        ConfigurationBuilder configurationBuilder = twConfiguration.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        Status status = null;
        try {
            if(args.length()!=0)
             status = twitter.updateStatus(args);
            else
                status=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
