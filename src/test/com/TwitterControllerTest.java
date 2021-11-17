package com;

import com.service.RetrieveTweets;
import com.service.SendTweet;
import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import com.service.TwitterImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TweetPostRequest tweetPostRequest;
    Logger logger= LoggerFactory.getLogger(TwitterControllerTest.class);
    Status status;
    TwitterImpl twitterimpl;
    @Before
    public void setUp()
    {
        tweetPostRequest= Mockito.mock(TweetPostRequest.class);
        twitterimpl=Mockito.mock(TwitterImpl.class);
        status=Mockito.mock(Status.class);
        twitterController=new TwitterController(tweetPostRequest,twitterimpl);
    }
    @Test
    public void testCase_tweeterController_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("Sleep");
        when(twitterimpl.sendTweets("Sleep")).thenReturn(status);
        when(status.getText()).thenReturn("Sleep");
         Response responseActual= twitterController.sendTweet(tweetPostRequest);
         Response responseExpected= Response.ok("Sleep").build();
        Assert.assertEquals(responseExpected.getStatus(),responseActual.getStatus());
    }
    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("");
        Response responseActual= twitterController.sendTweet(tweetPostRequest);
        Response responseExpected= Response.status(400).build();
        Assert.assertEquals(responseExpected.getEntity(),responseActual.getEntity());
    }

    @Test
    public void testCase_tweeterController_getTweets() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("tweet1");
        arrayList.add("tweet2");
        Response expectedResponse= Response.ok(arrayList).build();
        when(twitterimpl.fetchLatestTweet()).thenReturn(arrayList);
        Response actualResponse=twitterController.getTweets();
        Assert.assertEquals(expectedResponse.getLength(),actualResponse.getLength());
    }
}