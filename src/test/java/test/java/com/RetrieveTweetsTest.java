package test.java.com;

import com.config.TWConfiguration;
import com.resource.RetrieveTweets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveTweetsTest {
    TWConfiguration twConfiguration;
    RetrieveTweets retrieveTweets;

    @Before
    public void setUp()
    {
        twConfiguration= Mockito.mock(TWConfiguration.class);
        retrieveTweets=new RetrieveTweets(twConfiguration);
    }
    @Test
    public void testCase_fetchTweet_successCase()
    {
        when(twConfiguration.configurationBuilder()).thenReturn(new ConfigurationBuilder());
        ArrayList<String> arrayList = new ArrayList<>();
        TWConfiguration twConfiguration=new TWConfiguration();
        ConfigurationBuilder configurationBuilder = twConfiguration.configurationBuilder();
        try {
            TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
            Twitter twitter = twitterFactory.getInstance();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Response expectedTweet= Response.ok(arrayList).build();
        Response actualTweet=retrieveTweets.fetchLatestTweet();
        Assert.assertEquals(expectedTweet.getLength(),actualTweet.getLength());
    }
}