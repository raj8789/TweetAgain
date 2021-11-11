package com.resource;

import com.config.TWConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTweets {
    TWConfiguration twConfiguration;
    Logger logger= LoggerFactory.getLogger(RetrieveTweets.class);
    public RetrieveTweets()
    {

    }
    public RetrieveTweets(TWConfiguration twConfiguration)
    {
        this.twConfiguration=twConfiguration;
    }
    public  Response fetchLatestTweet() {
        TWConfiguration twConfiguration=new TWConfiguration();
        ConfigurationBuilder configurationBuilder = twConfiguration.configurationBuilder();
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
            Twitter twitter = twitterFactory.getInstance();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }

        } catch (TwitterException e) {
            logger.error("Error Occur",e);
        }
        if (arrayList.isEmpty()) {
            logger.info("You Have No Tweets On your Timeline");
            arrayList.add("No Tweet Found On TimeLine");
        }
        return Response.ok(arrayList).build();
    }
}
