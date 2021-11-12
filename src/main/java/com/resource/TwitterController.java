package com.resource;


import com.service.RetrieveTweets;
import com.service.SendTweet;
import com.service.TwitterImpl;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterController {
    TweetPostRequest tweetPostRequest;
    SendTweet sendTweet;
    RetrieveTweets retrieveTweets;
    TwitterImpl twitterimpl=new TwitterImpl();
    private Logger logger= LoggerFactory.getLogger(TwitterController.class);
    public TwitterController(TweetPostRequest tweetPostRequest, SendTweet sendTweet,RetrieveTweets retrieveTweets,TwitterImpl twitterimpl) {
        this.tweetPostRequest = tweetPostRequest;
        this.sendTweet = sendTweet;
        this.retrieveTweets=retrieveTweets;
        this.twitterimpl=twitterimpl;
    }
    public TwitterController() {
    }
    @POST
    @Path("/postTweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(TweetPostRequest tweetPostRequest) {
        String tweet = tweetPostRequest.getMessage();
        if (StringUtil.isEmpty(tweet))
        {
            logger.error("Enter a Valid Tweet");
            return Response.status(400, "Please Enter a valid tweet").build();
        }
        else
        {
            try
            {
                SendTweet sendTweet =tweetPostRequest.getSendTweetObject(twitterimpl);
                Status  status= sendTweet.sendTweets(tweet);
                if (status.getText().equals(tweet))
                {
                    logger.info("Tweet Send Successfully");
                    return Response.status(200, "Tweeted Successfully").build();
                } else {
                    logger.error("Tweet Was Not Done Invalid Request");
                    return Response.status(500, "Request was not completed").build();
                }
            }
            catch (TwitterException e)
            {
                logger.error("Tweet Was Not Done Invalid Request");
                return Response.status(500, "Request Was Not Completed").build();
            }
            catch (NullPointerException e)
            {
                logger.error("Status Was Not found so Not Able to tweet");
                throw new NullPointerException("Not Able To Tweet");
            }
        }
    }

    @GET
    @Path("/getTimeline")
    public Response getTweets(TweetPostRequest tweetPostRequest) {
        RetrieveTweets retrieveTweets =tweetPostRequest.getRetrieveTweetsObject(twitterimpl);
        return retrieveTweets.fetchLatestTweet();
    }
}
