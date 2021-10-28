package com.resource;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;
public class RetrieveTweets {
    public  static  String[] latestTweet(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("DtOw1uS0ZGAjTlxZy2ajWNfxr")
                .setOAuthConsumerSecret("9qaRx2cp8CGwZ9F9fReGOIbQoGCIPM9wEw1E4bvS8OnO4zZA87")
                .setOAuthAccessToken("1451106875525636098-a1t9UjpVutXX8BlStF9SKz6uckKDMU")
                .setOAuthAccessTokenSecret("G4KhZ6AjS1q56zoxyqM8z9IGoZpA0bDYxE7l1ePla50B7");
        String []ar=null;
        int size=0;
        try {
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            List<Status> statuses = twitter.getHomeTimeline();
            size=statuses.size();
             ar=new String[size];
            int i=0;
            for (Status status1 : statuses)
            {
                ar[i]=status1.getText();
                i++;
            }

        }
        catch (TwitterException e)
        {

        }
        if(size==0) {
            ar[0] = "No Tweet Found on Timeline";
        }
        return ar;
    }
}
