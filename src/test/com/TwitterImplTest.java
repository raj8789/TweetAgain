package com;

import com.config.TWConfiguration;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplTest {
    TWConfiguration twConfiguration;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    TwitterImpl twitterImpl;

    @Before
    public void setUp() {
        // mocking is pending
        twConfiguration = mock(TWConfiguration.class);
        configurationBuilder = mock(ConfigurationBuilder.class);
        twitterFactory = mock(TwitterFactory.class);
        twitterImpl = new TwitterImpl(twConfiguration, configurationBuilder, twitterFactory);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
      /*  responseList.add(s1);
        responseList.add(s2);
        responseList.add(s3);*/
       /* when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        when( twitter.getHomeTimeline()).thenReturn(responseList);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(1)).thenReturn(s1);
        when(s1.getText()).thenReturn("Tweet1");
        when(responseList.get(2)).thenReturn(s2);
        when(s2.getText()).thenReturn("Tweet2");
        when(responseList.get(3)).thenReturn(s3);
        when(s3.getText()).thenReturn("Tweet3");
        Response responseExpected= Response.ok(Arrays.asList("Tweet1","Tweet2","Tweet3")).build();*/
        Response responseActual = Response.ok(twitterImpl.fetchLatestTweet()).build();
//        Assert.assertEquals(responseExpected.getLength(),responseActual.getLength());
    }
   /* @Test
    public void testCase_fetchNoTweetOnTimeline_successCase() throws TwitterException {
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        responseList.add(null);
        when(responseList.size()).thenReturn(0);
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);
        when( twitter.getHomeTimeline()).thenReturn(responseList);
        Response responseExpected= Response.ok(responseList).build();
        Response responseActual= Response.ok(retrieveTweets.fetchLatestTweet()).build();
        Assert.assertEquals(responseExpected.getLength(),responseActual.getLength());

    }
*/
}