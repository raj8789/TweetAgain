package test.java.com;

import com.resource.RetrieveTweets;
import com.resource.SendTweet;
import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TweetPostRequest tweetPostRequest;
    SendTweet sendTweet;
    RetrieveTweets retrieveTweets;
    Logger logger= LoggerFactory.getLogger(TwitterControllerTest.class);
    @Before
    public void setUp()
    {
        tweetPostRequest= Mockito.mock(TweetPostRequest.class);
        sendTweet=Mockito.mock(SendTweet.class);
        retrieveTweets=Mockito.mock(RetrieveTweets.class);
        twitterController=new TwitterController(tweetPostRequest,sendTweet,retrieveTweets);
    }
    @Test
    public void testCase_tweeterController_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("Sleep");
         Response responseActual= twitterController.sendTweet(tweetPostRequest);
         Response responseExpected= Response.ok("Sleep").build();
        Assert.assertEquals(responseExpected.getStatus(),responseActual.getStatus());
    }
    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("Stay calm");
        String response="Not Able To Tweet";
        String actual="";
        try
        {
            Response responseActual= twitterController.sendTweet(tweetPostRequest);
        }
        catch (NullPointerException e)
        {
            actual=e.getMessage();
            logger.error("Exception occur",e);
        }
        Response responseExpected= Response.ok("Stay calm").build();
        Assert.assertEquals(response,actual);
    }

    @Test
    public void testCase_tweeterController_getTweets() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("tweet1");
        arrayList.add("tweet2");
        Response expectedResponse= Response.ok(arrayList).build();
        when(retrieveTweets.fetchLatestTweet()).thenReturn(expectedResponse);
        Response actualResponse=twitterController.getTweets();
        Assert.assertEquals(expectedResponse.getLength(),actualResponse.getLength());
    }
}