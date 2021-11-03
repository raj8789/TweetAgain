package com.resource;
import com.config.TWConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
public class RetrieveTweets {
    public  static Response fetchLatestTweet(){
        ConfigurationBuilder configurationBuilder= TWConfiguration.configurationBuilder();
        ArrayList<String> arrayList=new ArrayList<String>();
        try {
            TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
            Twitter twitter = twitterFactory.getInstance();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses)
            {
                arrayList.add(status.getText());
            }

        }
        catch (TwitterException e)
        {
            e.printStackTrace();
        }
        if(arrayList.isEmpty())
        {
            arrayList.add("No Tweet Found On TimeLine");
        }
        return Response.ok(arrayList).build();
    }
}
