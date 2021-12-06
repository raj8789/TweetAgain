import com.model.SendResponse;
import com.model.TwitterResponse;
import com.resource.TweetPostRequest;
import com.resource.TwitterController;
import com.service.TwitterImpl;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Status;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {
    TwitterController twitterController;
    TwitterImpl twitterimpl;
    HttpHeaders headers = null;

    @Before
    public void setUp() {
        twitterimpl = mock(TwitterImpl.class);
        twitterController = new TwitterController(twitterimpl);
        headers = new HttpHeaders();
    }

    @Test
    public void testCase_tweeterController_sendTweet() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("My Tweet");
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("My Tweet");
        when(twitterimpl.sendTweets("My Tweet")).thenReturn(status);
        ResponseEntity<SendResponse> actual = twitterController.sendTweet(tweetPostRequest);
        ResponseEntity<SendResponse> expected = new ResponseEntity<SendResponse>(new SendResponse("Tweet posted Successfully"), headers, HttpStatus.OK_200);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    // 1, empty tweet, 2. status.getText -"error" 3. BadRequestException 4. InternalServerErrorException
    @Test
    public void testCase_tweeterControllerNull_sendTweet() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("");
        ResponseEntity<SendResponse> actual = twitterController.sendTweet(tweetPostRequest);
        ResponseEntity<SendResponse> expected = new ResponseEntity<SendResponse>(new SendResponse("Please enter a Valid Tweet"), headers, HttpStatus.BAD_REQUEST_400);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testCase_tweeterControllerErrorStatusGetText_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("Cool");
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("Hot");
        when(twitterimpl.sendTweets("Cool")).thenReturn(status);
        ResponseEntity<SendResponse> actual = twitterController.sendTweet(tweetPostRequest);
        ResponseEntity<SendResponse> expected = new ResponseEntity<SendResponse>(new SendResponse("Request tweet is not correct"), headers, HttpStatus.INTERNAL_SERVER_ERROR_500);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test(expected = ResponseStatusException.class)
    public void testCase_BadRequestException_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(BadRequestException.class);
        ResponseEntity<SendResponse> actual = twitterController.sendTweet(tweetPostRequest);
    }

    @Test(expected = ResponseStatusException.class)
    public void testcase_InterNalServerExceptionError() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(InternalServerErrorException.class);
        ResponseEntity<SendResponse> actual = twitterController.sendTweet(tweetPostRequest);
    }

    @Test
    public void testCase_tweeterController_getTweets() {
        String message = "Tweet done";
        String twitterHandle = "@masum";
        String name = "Masum Raj";
        String profileurl = "www.MasumRaj.com";
        String date = "";
        TwitterResponse twitterResponse = new TwitterResponse(message, twitterHandle, name, profileurl, date);
        ArrayList<TwitterResponse> twitList = new ArrayList<>();
        twitList.add(twitterResponse);
        Response expectedResponse = Response.ok(twitList).build();
        when(twitterimpl.fetchLatestTweet()).thenReturn(twitList);
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

    @Test
    public void testCase_FilterTweet() {
        String message = "be cool always";
        String twitterHandle = "@masum";
        String name = "Masum Raj";
        String profileurl = "www.MasumRaj.com";
        String date = "";
        String search = "cool";
        TwitterResponse twitterResponse = new TwitterResponse(message, twitterHandle, name, profileurl, date);
        List<TwitterResponse> twitList = new ArrayList<>();
        twitList.add(twitterResponse);
        when(twitterimpl.getTweetBasedOnMyFilter(search)).thenReturn(twitList);
        Response actual = twitterController.getFilterTweets(search);
        Response expected = Response.ok(twitList).build();
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}