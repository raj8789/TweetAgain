
import com.model.TwitterResponse;
import com.service.TwitterImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplTest
{
    TwitterFactory twitterFactory;
    TwitterImpl twitterImpl;
    Twitter twitter;
    TwitterResponse twitterResponse;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String twitterHandle="@masum";
    String name="Raushan";
    String message="tweet1";
    String profileImageUrl="www.RajProfile.com";
    Date created;
    String date;
    {
        try {
            created = dateFormat.parse("2015-12-06 17:03:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = dateFormat.format(created);
    }
    @Before
    public void setUp() {
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        twitterResponse = spy(new TwitterResponse(message, twitterHandle, name, profileImageUrl, date));
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImpl = new TwitterImpl(twitterFactory,twitterResponse);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException{
        Status s1 = mock(Status.class);
        User user=mock(User.class);
        ResponseList<Status> responseList = mock(ResponseList.class);
        ArrayList<TwitterResponse> twitListExpected=mock(ArrayList.class);
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getProfileImageURL()).thenReturn(profileImageUrl);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getUser().getScreenName()).thenReturn(twitterHandle);
        when(s1.getText()).thenReturn(message);
        when(s1.getCreatedAt()).thenReturn(created);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        twitListExpected.add(twitterResponse);
        ArrayList<TwitterResponse> actualListExpected = twitterImpl.fetchLatestTweet();
        Assert.assertEquals(twitListExpected,actualListExpected);
    }
    @Test
    public void testCase_fetchFilterTweet_successCase() throws Exception {
        Status s1 = mock(Status.class);
        User user=mock(User.class);
        ResponseList<Status> responseList = mock(ResponseList.class);
        List<TwitterResponse> twitListExpected=mock(ArrayList.class);
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getProfileImageURL()).thenReturn(profileImageUrl);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getUser().getScreenName()).thenReturn(twitterHandle);
        when(s1.getText()).thenReturn(message);
        when(s1.getCreatedAt()).thenReturn(created);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        whenNew(TwitterResponse.class).withAnyArguments().thenReturn(twitterResponse);
        twitListExpected.add(null);
        List<TwitterResponse> actualListExpected = twitterImpl.getTweetBasedOnMyFilter("ee");
        Assert.assertEquals(twitListExpected,actualListExpected);
    }
    @Test
    public void testCase_fetchNoTweetOnTimeline_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
       // List<String> actual = twitterImpl.fetchLatestTweet();
       // Assert.assertEquals(Arrays.asList(), actual);
    }
    @Test(expected = InternalServerErrorException.class)
    public void testCase_exceptionCase() throws TwitterException {
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        twitterImpl.fetchLatestTweet();
    }

    @Test
    public void testCase_sendTweet_successCase() throws TwitterException {
        Status expected = mock(Status.class);
        String tweet = "My Tweet";
        when(twitter.updateStatus(tweet)).thenReturn(expected);
        Status actual = twitterImpl.sendTweets(tweet);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = BadRequestException.class)
    public void testCase_SendTweetFailCaseLongLengthTweet() {
        String tweet = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\" +\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n" +
                "        +\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\n" +
                "                \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"+\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        twitterImpl.sendTweets(tweet);
    }

    @Test(expected = BadRequestException.class)
    public void testCase_SendTweetFailCaseZeroLengthTweet() {
        String tweet = "";
        twitterImpl.sendTweets(tweet);
    }
}