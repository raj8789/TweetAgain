package test.java.com;


import com.resource.TweetPostRequest;
import com.service.RetrieveTweets;
import com.service.SendTweet;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class TweetPostRequestTest {
    TwitterImpl twitterImpl;
    SendTweet sendTweet;
    TweetPostRequest tweetPostRequest;
    RetrieveTweets retrieveTweets;
    @Before
    public void setUp()
    {
        twitterImpl= Mockito.mock(TwitterImpl.class);
        sendTweet=new SendTweet();
        retrieveTweets=new RetrieveTweets();
        tweetPostRequest=new TweetPostRequest(twitterImpl);
    }

    @Test
    public void getSendTweetObject()
    {
        SendTweet sendTweet1Actual=tweetPostRequest.getSendTweetObject(twitterImpl);
        Assert.assertEquals(sendTweet1Actual.getClass().getName(),sendTweet.getClass().getName());
    }

    @Test
    public void getRetrieveTweetsObject()
    {
       RetrieveTweets retrieveTweetsActual=tweetPostRequest.getRetrieveTweetsObject(twitterImpl);
        Assert.assertEquals(retrieveTweetsActual.getClass().getName(),retrieveTweets.getClass().getName());
    }
}