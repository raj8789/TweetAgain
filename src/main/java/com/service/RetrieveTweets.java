package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTweets {
    TwitterImpl twitterimpl;
    Logger logger= LoggerFactory.getLogger(RetrieveTweets.class);
    public RetrieveTweets(TwitterImpl twitterimpl)
    {
            this.twitterimpl=twitterimpl;
    }
    public RetrieveTweets()
    {

    }
    public  ArrayList<String> fetchLatestTweet() {

        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            Twitter twitter=twitterimpl.getTwitterObject();
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
        return arrayList;
    }
}
