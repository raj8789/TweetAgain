package com;

import com.service.RetrieveTweets;
import com.service.SendTweet;
import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import com.service.TwitterImpl;
import junit.framework.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import static org.hamcrest.Matchers.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TwitterImpl twitterimpl;

    @Before
    public void setUp() {
        twitterimpl = mock(TwitterImpl.class);
        twitterController = new TwitterController(twitterimpl);
    }

    @Test
    public void testCase_tweeterController_sendTweet() throws TwitterException {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("My Tweet");
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("My Tweet");
        when(twitterimpl.sendTweets("My Tweet")).thenReturn(status);
        Response actual = twitterController.sendTweet(tweetPostRequest);

        Response expected = Response.status(200, "Tweeted Successfully").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);

    }

    // 1, empty tweet, 2. status.getText -"error" 3. BadRequestException 4. InternalServerErrorException

/*    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        when(tweetPostRequest.getMessage()).thenReturn("");
        Response responseActual = twitterController.sendTweet(tweetPostRequest);
        Response responseExpected = Response.status(400).build();
        Assert.assertEquals(responseExpected.getEntity(), responseActual.getEntity());
    }

    @Test
    public void testCase_tweeterController_getTweets() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("tweet1");
        arrayList.add("tweet2");
        Response expectedResponse = Response.ok(arrayList).build();
        when(twitterimpl.fetchLatestTweet()).thenReturn(arrayList);
        Response actualResponse = twitterController.getTweets();
        Assert.assertEquals(expectedResponse.getLength(), actualResponse.getLength());
    }*/
}