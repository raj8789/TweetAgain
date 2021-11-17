package com;

import com.config.TWConfiguration;
import com.resource.TweetPostRequest;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplTest {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    TwitterImpl twitterImpl;
    Twitter twitter;
    Status status;
    org.slf4j.Logger logger = LoggerFactory.getLogger(TwitterImplTest.class);
    @Before
    public void setUp() {
        // mocking is pending
        twConfiguration = Mockito.mock(TWConfiguration.class);
        configurationBuilder = Mockito.mock(ConfigurationBuilder.class);
        twitterFactory = Mockito.mock(TwitterFactory.class);
        twitter =Mockito.mock(Twitter.class);
        twitterImpl=Mockito.mock(TwitterImpl.class);
        status=Mockito.mock(Status.class);
        new TwitterImpl(twConfiguration, configurationBuilder, twitterFactory,twitter,twitterImpl,status);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
        ResponseList<Status> responseList=Mockito.mock(ResponseList.class);
        Status s1=Mockito.mock(Status.class);
        Status s2=Mockito.mock(Status.class);
        Status s3=Mockito.mock(Status.class);
        responseList.add(s1);
        responseList.add(s2);
        responseList.add(s3);
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        when( twitter.getHomeTimeline()).thenReturn(responseList);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(1)).thenReturn(s1);
        when(s1.getText()).thenReturn("Tweet1");
        when(responseList.get(2)).thenReturn(s2);
        when(s2.getText()).thenReturn("Tweet2");
        when(responseList.get(3)).thenReturn(s3);
        when(s3.getText()).thenReturn("Tweet3");
        Response responseExpected= Response.ok(Arrays.asList("Tweet1","Tweet2","Tweet3")).build();
        Response responseActual = Response.ok(twitterImpl.fetchLatestTweet()).build();
       Assert.assertEquals(responseExpected.getLength(),responseActual.getLength());
    }
   @Test
    public void testCase_fetchNoTweetOnTimeline_successCase() throws TwitterException {
       ResponseList<Status> responseList=Mockito.mock(ResponseList.class);
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        responseList.add(null);
        when(responseList.size()).thenReturn(0);
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        when( twitter.getHomeTimeline()).thenReturn(responseList);
        Response responseExpected= Response.ok(responseList).build();
        Response responseActual= Response.ok(twitterImpl.fetchLatestTweet()).build();
        Assert.assertEquals(responseExpected.getLength(),responseActual.getLength());
    }
   @Test
   public void testCase_sendTweet_successCase() throws TwitterException {
       String expectedTweet = "Error";
       when(twitterImpl.getTwitterObject()).thenReturn(twitter);
       when(twitter.updateStatus(expectedTweet)).thenReturn(status);
       when(status.getText()).thenReturn(expectedTweet);
       try {
           status = twitterImpl.sendTweets(expectedTweet);
       } catch (TwitterException e) {
           logger.error("Exception Occur", e);
       }
       String actualTweet = status.getText();
            Assert.assertEquals(expectedTweet,actualTweet);
        }
   }