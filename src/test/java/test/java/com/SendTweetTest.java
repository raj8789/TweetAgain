package test.java.com;

import com.config.TWConfiguration;
import com.resource.SendTweet;
import com.resource.TweetPostRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendTweetTest {
    SendTweet sendTweet;
    TWConfiguration twConfiguration;  // Mock object reference Variable
    TweetPostRequest tweetPostRequest;

    @Before
    public void seTup()
    {
            twConfiguration= Mockito.mock(TWConfiguration.class);
            sendTweet=new SendTweet(twConfiguration);
            tweetPostRequest=new TweetPostRequest();

    }
    @Test
    public void testCase_sendTweet_successCase()
    {
        when(twConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        tweetPostRequest.setMessage("Anjali is my love");
        String expectedTweet=tweetPostRequest.getMessage();
        Status status=null;
        try {
             status=sendTweet.sendTweets(expectedTweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        String actualTweet=status.getText();
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test
    public void testCase_NoTweetSend_successCase()
    {
        when(twConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        tweetPostRequest.setMessage("");
        String expectedTweet=tweetPostRequest.getMessage();
        Status status=null;
        try {
            status=sendTweet.sendTweets(expectedTweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        int expectedLength=0;
        int actuallength=0;
        String actualTweet="";
        if(status!=null)
        {
             actualTweet=status.getText();
        }
        if(status==null)
        {
            expectedLength = expectedTweet.length();
            actuallength=0;
        }
        else {
            expectedLength=expectedTweet.length();
            actuallength=actualTweet.length();
        }

        Assert.assertEquals(expectedLength,actuallength);
    }

}