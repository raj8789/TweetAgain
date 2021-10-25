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
                    .setOAuthConsumerKey("Mf351TGneOo3mkOQsL5yQclXa")
                    .setOAuthConsumerSecret("FLFbjaFBahZSqK93U3I9CAxqR1lbeLQmDNgi7bXoqquPYE7NQT")
                    .setOAuthAccessToken("1451106875525636098-6gxBFUre0fQ9ud6vYdhAQXzeTI6G2d")
                    .setOAuthAccessTokenSecret("yyOC7bGUzFxdeccWpX17I1pIR5TRT2PLrp2JprREC80h4");
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
