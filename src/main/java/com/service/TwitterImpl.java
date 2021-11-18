package com.service;

import com.config.TWConfiguration;
import com.model.TwitterResponse;
import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitterImpl {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Logger logger = LoggerFactory.getLogger(TwitterImpl.class);
    Twitter twitter;

    String twitterHandle;
    String name;
    String message;
    String profileImageUrl;
    Date created = null;

    TwitterResponse twitterResponse;
    // controller usage
    public TwitterImpl() {
        twConfiguration = new TWConfiguration();
        configurationBuilder = twConfiguration.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    // used for test case
    public TwitterImpl(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
    }

    public Status sendTweets(String tweet) {
        int tweetLength = tweet.length();
        if (tweetLength > 280 || tweetLength == 0) {
            logger.error("Tweet can should be between 0 to 280");
            throw new BadRequestException("Tweet can should be between 0 to 280");
        }
        Status status;
        try {
            status = twitter.updateStatus(tweet);
        } catch (TwitterException e) {
            logger.error("Exception while send tweet", e);
            throw new InternalServerErrorException("Server error, could not post tweet");
        }
        return status;
    }
    public ArrayList<TwitterResponse> fetchLatestTweet() {
        ArrayList<TwitterResponse> twitList=new ArrayList<>();
        try
        {
            List<Status> statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                Status s = statuses.get(i);
                profileImageUrl = s.getUser().getProfileImageURL();
                name = s.getUser().getName();
                twitterHandle =s.getUser().getScreenName();
                message = s.getText();
                created = s.getCreatedAt();
                Format dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = dateFormat.format(created);
                twitterResponse= new TwitterResponse(message,twitterHandle,name,profileImageUrl,date);
                twitterResponse.setCreatedAt(date);
                twitList.add(twitterResponse);
            }
            //twitterHandle=twitterResponse.getTwitterResponseList();
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
            throw new InternalServerErrorException("Server error, could not fetch tweet");
        }
        if (twitList.isEmpty()) {
            logger.info("You Have No Tweets On your Timeline");
        }
        return twitList;
    }
}
