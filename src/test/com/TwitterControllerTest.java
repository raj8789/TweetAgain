
import com.model.SendResponse;
import com.model.TwitterResponse;
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
        SendResponse actual = twitterController.sendTweet(tweetPostRequest);
        SendResponse expected = new SendResponse("Tweet posted Successfully",200);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);

    }
    // 1, empty tweet, 2. status.getText -"error" 3. BadRequestException 4. InternalServerErrorException
    @Test
    public void testCase_tweeterControllerNull_sendTweet() throws TwitterException {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("");
        SendResponse actual = twitterController.sendTweet(tweetPostRequest);
        SendResponse expected = new SendResponse("Please enter a Valid Tweet",400);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
    @Test
    public void testCase_tweeterControllerErrorStatusGetText_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("Cool");
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("Hot");
        when(twitterimpl.sendTweets("Cool")).thenReturn(status);
        SendResponse actual = twitterController.sendTweet(tweetPostRequest);
        SendResponse expected = new SendResponse("Request was not completed",500);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
    @Test
    public void testCase_BadRequestException_error() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(BadRequestException.class);
        SendResponse actual = twitterController.sendTweet(tweetPostRequest);
        SendResponse expected = new SendResponse("Request tweet is not correct",400);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
    @Test
    public void testcase_InterNalServerExceptionError() {
        TweetPostRequest tweetPostRequest = new TweetPostRequest("hello");
        when(twitterimpl.sendTweets("hello")).thenThrow(InternalServerErrorException.class);
        SendResponse actual = twitterController.sendTweet(tweetPostRequest);
        SendResponse expected =new SendResponse("Request was not completed",500);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
    @Test
    public void testCase_tweeterController_getTweets() {
        String message="Tweet done";
        String twitterHandle="@masum";
        String name="Masum Raj";
        String profileurl="www.MasumRaj.com";
        String date="";
        TwitterResponse twitterResponse=new TwitterResponse(message,twitterHandle,name,profileurl,date);
        ArrayList<TwitterResponse> twitList=new ArrayList<>();
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
}