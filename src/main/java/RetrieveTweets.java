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
                .setOAuthConsumerKey("*********************")
                .setOAuthConsumerSecret("***********************************************")
                .setOAuthAccessToken("***************************************************")
                .setOAuthAccessTokenSecret("*********************************************");
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

