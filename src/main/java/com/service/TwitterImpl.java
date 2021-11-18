package com.service;

import com.config.TWConfiguration;
import com.model.TwitterResponse;
import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.util.List;

public class TwitterImpl {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Logger logger = LoggerFactory.getLogger(TwitterImpl.class);
    Twitter twitter;
    User user;
    TwitterResponse twitterResponse;

    // controller usage
    public TwitterImpl() {
        twConfiguration = new TWConfiguration();
        configurationBuilder = twConfiguration.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
        user=new User();
        twitterResponse=new TwitterResponse(user);
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

    public List<String> fetchLatestTweet() {
        List<String> twitterHandle;
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            user.setName("User Name  : "+statuses.get(0).getUser().getName());
            user.setProfileImageUrl("Profile Image Url : "+statuses.get(0).getUser().getProfileImageURL());
            for (int i = 0; i < statuses.size(); i++) {
                Status s = statuses.get(i);
                //arrayList.add(s.getText());
                user.setTwitterHandle(s.getText()+"      Date "+s.getCreatedAt());
            }
            twitterHandle=twitterResponse.getTwitterResponseList();
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
            throw new InternalServerErrorException("Server error, could not fetch tweet");
        }
        if (twitterHandle.isEmpty()) {
            logger.info("You Have No Tweets On your Timeline");
        }
        return twitterHandle;
    }
}
