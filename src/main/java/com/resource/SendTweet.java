package com.resource;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SendTweet {
    public static void sendTweets(String args)throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("DtOw1uS0ZGAjTlxZy2ajWNfxr")
                .setOAuthConsumerSecret("9qaRx2cp8CGwZ9F9fReGOIbQoGCIPM9wEw1E4bvS8OnO4zZA87")
                .setOAuthAccessToken("1451106875525636098-a1t9UjpVutXX8BlStF9SKz6uckKDMU")
                .setOAuthAccessTokenSecret("G4KhZ6AjS1q56zoxyqM8z9IGoZpA0bDYxE7l1ePla50B7");
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
