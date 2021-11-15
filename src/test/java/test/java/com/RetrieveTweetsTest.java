package test.java.com;

import com.service.RetrieveTweets;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RetrieveTweetsTest {
    TwitterImpl twitterImpl;
    RetrieveTweets retrieveTweets;
    Twitter twitter;
    Logger logger= LoggerFactory.getLogger(RetrieveTweetsTest.class);
    Status s1;
    Status s2;
    Status s3;
    ResponseList<Status> responseList;
    @Before
    public void setUp()
    {
        twitter=Mockito.mock(Twitter.class);
        twitterImpl= Mockito.mock(TwitterImpl.class);
         s1=Mockito.mock(Status.class);
         s2=Mockito.mock(Status.class);
         s3=Mockito.mock(Status.class);
        responseList=Mockito.mock(ResponseList.class);
        retrieveTweets=new RetrieveTweets(twitterImpl);
    }
    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
        when(twitterImpl.getTwitterObject()).thenReturn(twitter);

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
        Response responseActual= Response.ok(retrieveTweets.fetchLatestTweet()).build();
        Assert.assertEquals(responseExpected.getLength(),responseActual.getLength());
    }
    @Test
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
}