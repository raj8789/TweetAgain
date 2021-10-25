import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

    public class SendTweet {
        public void sendTweet(String args)throws TwitterException {
            //String tw="";
            /*for (int i=1;i< args.length();i++)
            {
                tw=tw+args[i];
                tw=tw+"  ";
            }*/
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("*********************")
                    .setOAuthConsumerSecret("***********************************************")
                    .setOAuthAccessToken("***************************************************")
                    .setOAuthAccessTokenSecret("*********************************************");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            Status status = null;
            try {
                status = twitter.updateStatus(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        }
    }
