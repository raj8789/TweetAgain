package com;

import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplTest {
    TwitterFactory twitterFactory;
    TwitterImpl twitterImpl;
    Twitter twitter;

    @Before
    public void setUp() {
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImpl = new TwitterImpl(twitterFactory);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        Status s1 = mock(Status.class);
        Status s2 = mock(Status.class);
        Status s3 = mock(Status.class);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getText()).thenReturn("Tweet1");
        when(responseList.get(1)).thenReturn(s2);
        when(s2.getText()).thenReturn("Tweet2");
        when(responseList.get(2)).thenReturn(s3);
        when(s3.getText()).thenReturn("Tweet3");

        when(twitter.getHomeTimeline()).thenReturn(responseList);

        List<String> expected = Arrays.asList("Tweet1", "Tweet2", "Tweet3");

        List<String> actual = twitterImpl.fetchLatestTweet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCase_fetchNoTweetOnTimeline_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);

        List<String> actual = twitterImpl.fetchLatestTweet();
        Assert.assertEquals(Arrays.asList(), actual);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCase_exceptionCase() throws TwitterException {
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        twitterImpl.fetchLatestTweet();
    }

    @Test
    public void testCase_sendTweet_successCase() throws TwitterException {
        Status expected = mock(Status.class);
        String tweet = "My Tweet";
        when(twitter.updateStatus(tweet)).thenReturn(expected);
        Status actual = twitterImpl.sendTweets(tweet);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = BadRequestException.class)
    public void testCase_SendTweetFailCaseLongLengthTweet() {
        String tweet = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\" +\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n" +
                "        +\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        twitterImpl.sendTweets(tweet);
    }

    @Test(expected = BadRequestException.class)
    public void testCase_SendTweetFailCaseZeroLengthTweet() {
        String tweet = "";
        twitterImpl.sendTweets(tweet);
    }
}