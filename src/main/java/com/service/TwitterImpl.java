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

    public TwitterImpl(TWConfiguration twConfiguration, ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.twConfiguration = twConfiguration;
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
        twitter = twitterFactory.getInstance();
    }

    public Status sendTweets(String args) throws TwitterException {
        Status status = null;
        try {
            if (args.length() != 0)
                status = twitter.updateStatus(args);
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
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
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
}
