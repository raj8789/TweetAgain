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
                .setOAuthConsumerKey("Iq2STwqt6DsfrdAX5kZz37RVP")
                .setOAuthConsumerSecret("zhhpECdqZKhgukHibrQstKDPbXOKxEL6whLcw2fROR0U0TEoQ0")
                .setOAuthAccessToken("1451106875525636098-LrOSaFuEOKZDGi3QjzLeAPTZQeopZR")
                .setOAuthAccessTokenSecret("tRtzJ3BnJBJVumiX4BA15xGtwgfyiMTyMZRKCYYDZuOAF");
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
