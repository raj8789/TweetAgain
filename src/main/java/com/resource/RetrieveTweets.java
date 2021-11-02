package com.resource;
import com.config.TWConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
public class RetrieveTweets {
    public  static ArrayList<String> latestTweet(){
        ConfigurationBuilder cb= TWConfiguration.configurationBuilder();
        ArrayList<String> ar=new ArrayList<String>();
        try {
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses)
            {
                ar.add(status.getText());
            }

        }
        catch (TwitterException e)
        {
            e.printStackTrace();
        }
        if(ar.isEmpty())
        {
            ar.add("No Tweet Found On TimeLine");
        }
        return ar;
    }
}
