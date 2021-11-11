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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendTweetTest {
    SendTweet sendTweet;
    TWConfiguration twConfiguration;  // Mock object reference Variable
    TweetPostRequest tweetPostRequest;
    Logger logger= LoggerFactory.getLogger(SendTweetTest.class);
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
        tweetPostRequest.setMessage("Sad moment missing Home");
        String expectedTweet=tweetPostRequest.getMessage();
        Status status=null;
        try {
             status=sendTweet.sendTweets(expectedTweet);
        } catch (TwitterException e) {
            logger.error("Exception Occur",e);
        }
        String actualTweet=status.getText();
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test
    public void testCase_sendTweet_checkLength_successCase()
    {
        when(twConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        tweetPostRequest.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String expectedTweetNoNull=tweetPostRequest.getMessage();
        String actualTweet="";
        String expectedTweet="";
            Status status = null;
            try {
                status = sendTweet.sendTweets(expectedTweet);
            } catch (TwitterException e) {
                 actualTweet=e.getMessage();
                 expectedTweet="Tweet needs to be a shorter";
                 logger.error("Exception occur",e);
            }
        if(expectedTweetNoNull.length()<280) {
             actualTweet = status.getText();
            Assert.assertEquals(expectedTweetNoNull, actualTweet);
        }
        else
        {
            Assert.assertEquals(expectedTweet,actualTweet);
        }
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
            logger.error("Exception occur",e);
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
        }
        else {
            expectedLength=expectedTweet.length();
            actuallength=actualTweet.length();
        }

        Assert.assertEquals(expectedLength,actuallength);
    }
    @Test
    public void testCase_sendReTweetedTweet_successCase()
    {
        when(twConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        tweetPostRequest.setMessage("Raj");
        String expectedTweet="Tweet is duplicate tweet";
        String actualTweet="";
        Status status=null;
        try {
            status=sendTweet.sendTweets(tweetPostRequest.getMessage());
        } catch (TwitterException e) {
            actualTweet=e.getMessage();
            logger.error("Exception occur",e);
        }
        Assert.assertEquals(expectedTweet,actualTweet);
    }

}