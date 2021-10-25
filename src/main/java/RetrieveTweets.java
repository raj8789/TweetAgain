import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class RetrieveTweets {
    public  void latestTweet() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Mf351TGneOo3mkOQsL5yQclXa")
                .setOAuthConsumerSecret("FLFbjaFBahZSqK93U3I9CAxqR1lbeLQmDNgi7bXoqquPYE7NQT")
                .setOAuthAccessToken("1451106875525636098-6gxBFUre0fQ9ud6vYdhAQXzeTI6G2d")
                .setOAuthAccessTokenSecret("yyOC7bGUzFxdeccWpX17I1pIR5TRT2PLrp2JprREC80h4");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status1 : statuses) {
            System.out.println(status1.getUser().getName() + ":" +
                    status1.getText());
        }
    }
}

