package com;

import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import com.service.TwitterImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

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

    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("");
        Response actual = twitterController.sendTweet(tweetPostRequest);
        Response expected = Response.status(400, "Please Enter a valid tweet").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testCase_tweeterControllerErrorStatusGetText_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("Cool");
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("Hot");
        when(twitterimpl.sendTweets("Cool")).thenReturn(status);
        Response actual = twitterController.sendTweet(tweetPostRequest);
        Response expected = Response.status(500, "Request was not completed").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testCase_BadRequestException_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(BadRequestException.class);
        Response actual = twitterController.sendTweet(tweetPostRequest);
        Response expected = Response.status(400, "Request tweet is not correct").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testcase_InterNalServerExceptionError() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(InternalServerErrorException.class);
        Response actual = twitterController.sendTweet(tweetPostRequest);
        Response expected = Response.status(500, "Request Was Not Completed").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testCase_tweeterController_getTweets() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("tweet1");
        arrayList.add("tweet2");
        Response expectedResponse = Response.ok(arrayList).build();
        when(twitterimpl.fetchLatestTweet()).thenReturn(arrayList);
        Response actualResponse = twitterController.getTweets();
        Assertions.assertThat(actualResponse).isEqualToComparingFieldByFieldRecursively(expectedResponse);
    }

    @Test
    public void testCase_Exception_getTweets() {
        when(twitterimpl.fetchLatestTweet()).thenThrow(InternalServerErrorException.class);
        Response actual = twitterController.getTweets();
        Response expected = Response.status(500, "Request Was Not Completed").build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}