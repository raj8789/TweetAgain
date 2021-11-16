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
}