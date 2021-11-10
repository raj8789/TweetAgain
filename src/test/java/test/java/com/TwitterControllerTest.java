package test.java.com;

import com.resource.SendTweet;
import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TweetPostRequest tweetPostRequest;
    SendTweet sendTweet;
    @Before
    public void setUp()
    {
        tweetPostRequest= Mockito.mock(TweetPostRequest.class);
        sendTweet=Mockito.mock(SendTweet.class);
        twitterController=new TwitterController(tweetPostRequest,sendTweet);
    }
    @Test
    public void testCase_tweeterController_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("Chath pooja");
         Response responseActual= twitterController.sendTweet(tweetPostRequest);
         Response responseExpected= Response.ok("Chath pooja").build();
        Assert.assertEquals(responseExpected.getStatus(),responseActual.getStatus());
    }
    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("Raja");
        String response="Not Able To Tweet";
        String actual="";
        try {
            Response responseActual= twitterController.sendTweet(tweetPostRequest);
        }
        catch (NullPointerException e)
        {
            actual=e.getMessage();
        }
        Response responseExpected= Response.ok("Raja").build();
        Assert.assertEquals(response,actual);
    }
}