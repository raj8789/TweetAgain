package com.service;

import com.config.TWConfiguration;
import com.model.TwitterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CacheConfig(cacheNames = {"allTweets", " filters"})
@Component
public class TwitterImpl {
    private final TwitterFactory twitterFactory;
    private final Logger logger = LoggerFactory.getLogger(TwitterImpl.class);
    private final Twitter twitter;
    private ConfigurationBuilder configurationBuilder;
    private TwitterResponse twitterResponse;
    @Autowired
    private TWConfiguration twConfiguration;

    /**
     * Used for test case.
     * this constructor is used for getting Twitter object
     * based on the authentication of user
     */
    public TwitterImpl() {
        twConfiguration = new TWConfiguration();
        configurationBuilder = twConfiguration.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    /**
     * Used for test case.
     *
     * @param twitterFactory
     * @param twitterResponse
     */
    public TwitterImpl(final TwitterFactory twitterFactory, final TwitterResponse twitterResponse) {
        this.twitterFactory = twitterFactory;
        this.twitterResponse = twitterResponse;
        this.twitter = twitterFactory.getInstance();
    }

    /**
     * @param tweet
     * @return This method will return Status object
     * which contains status of tweet which is posted on timeline
     */
    @Cacheable(cacheNames = {"allTweets"})
    @CacheEvict(allEntries = true)
    public Status sendTweets(final String tweet) {
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

    /**
     * fetchLatestTweet() used to get tweets from user timeline.
     *
     * @return returns tweets to resources class.
     */
    @Cacheable(cacheNames = {"allTweets"})
    @Scheduled(fixedRate = 1)
    public ArrayList<TwitterResponse> fetchLatestTweet() {
        ArrayList<TwitterResponse> twitList = new ArrayList<>();
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                Status s = statuses.get(i);
                String profileImageUrl = s.getUser().getProfileImageURL();
                String name = s.getUser().getName();
                String twitterHandle = s.getUser().getScreenName();
                String message = s.getText();
                Date created = s.getCreatedAt();
                Format dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = dateFormat.format(created);
                twitterResponse = new TwitterResponse(message, twitterHandle, name, profileImageUrl, date);
                twitList.add(twitterResponse);
            }
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
            throw new InternalServerErrorException("Server error, could not fetch tweet");
        }
        if (twitList.isEmpty()) {
            logger.info("You Have No Tweets On your Timeline");
        }
        return twitList;
    }

    /**
     * getFilteredTweets() used to get filtered tweets from user timeline.
     *
     * @param searchTweet is used to search in a list of tweets.
     * @return returns filtered tweets.
     */
    @Cacheable(cacheNames = {"filters"})
    public List<TwitterResponse> getTweetBasedOnMyFilter(final String searchTweet) {
        ArrayList<TwitterResponse> twitList = fetchLatestTweet();
        List<TwitterResponse> filterTwitList;
        int end = searchTweet.length();
        CharSequence charSequence = searchTweet.subSequence(0, end);
        filterTwitList = twitList.stream().filter(t -> t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filterTwitList;
    }
}
