package com.resource;

import com.config.TWConfiguration;
import com.service.TwitterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTweets {
    TwitterImpl twitterimpl;
    Logger logger= LoggerFactory.getLogger(RetrieveTweets.class);
    public RetrieveTweets(TwitterImpl twitterimpl)
    {
            this.twitterimpl=twitterimpl;
    }
    public  Response fetchLatestTweet() {

        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            Twitter twitter=twitterimpl.getTwitterObject();
            List<Status> statuses = twitter.getHomeTimeline();
            for (int i=1;i<=statuses.size();i++)
            {
                arrayList.add(statuses.get(i).getText());
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
