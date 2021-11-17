package com.service;

import com.config.TWConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterImpl {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Logger logger = LoggerFactory.getLogger(TwitterImpl.class);
    Twitter twitter;
    TwitterImpl twitterImpl;
    Status status;
    public  TwitterImpl(){
    }
    public TwitterImpl(TWConfiguration twConfiguration, ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory,Twitter twitter,TwitterImpl twitterImpl,Status status) {
        this.twConfiguration = twConfiguration;
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
        this.twitter=twitter;
        this.twitterImpl=twitterImpl;
        this.status=status;
    }
    public Status sendTweets(String args) throws TwitterException {
         status = null;
        try {
            if (args.length() != 0) {
                twitter = this.getTwitterObject();
                status = twitter.updateStatus(args);
            }
            else
                status = null;
        } catch (Exception e) {
            if (args.length() > 280) {
                logger.error("Tweets Length Is to Long Need to be shorter");
                throw new TwitterException("Tweet needs to be a shorter");
            }
            if (status == null) {
                logger.error("Tweets is Duplicated");
                throw new TwitterException("Tweet is duplicate tweet");
            }
        }
        return status;
    }
    public ArrayList<String> fetchLatestTweet() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            twitter=this.getTwitterObject();
            List<Status> statuses = twitter.getHomeTimeline();
            for(int i=0;i<statuses.size();i++)
            {
                Status s=statuses.get(i);
                arrayList.add(s.getText());
            }
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
        }
        if (arrayList.isEmpty()) {
            logger.info("You Have No Tweets On your Timeline");
            arrayList.add("No Tweet Found On TimeLine");
        }
        return arrayList;
    }
    public Twitter getTwitterObject() {
         twConfiguration=new TWConfiguration();
         configurationBuilder = twConfiguration.configurationBuilder();
         twitterFactory = new TwitterFactory(configurationBuilder.build());
        return twitterFactory.getInstance();
    }
}
