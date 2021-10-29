package com.resource;

import com.config.TWConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SendTweet {
    public static void sendTweets(String args)throws TwitterException {
        ConfigurationBuilder cb= TWConfiguration.configurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Status status = null;
        try {
            status = twitter.updateStatus(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }
}
