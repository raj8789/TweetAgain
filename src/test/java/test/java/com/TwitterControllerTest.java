package test.java.com;

import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TweetPostRequest tweetPostRequest;
    @Before
    public void setUp()
    {
        tweetPostRequest=new TweetPostRequest();
        twitterController=new TwitterController();
    }
    @Test
    public void testCase_tweeterController_sendTweet()
    {
        tweetPostRequest.setMessage("Biren");
         Response responseActual= twitterController.sendTweet(tweetPostRequest);
         System.out.println(responseActual);
    }
}