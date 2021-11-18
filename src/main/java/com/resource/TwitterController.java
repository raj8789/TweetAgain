package com.resource;


import com.model.TwitterResponse;
import com.service.TwitterImpl;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterController {

    private final Logger logger = LoggerFactory.getLogger(TwitterController.class);
    TwitterImpl twitterimpl;

    // used by test class
    public TwitterController(TwitterImpl twitterimpl) {
        this.twitterimpl = twitterimpl;
    }

    public TwitterController() {
        twitterimpl = new TwitterImpl();
    }

    @POST
    @Path("/postTweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(TweetPostRequest tweetPostRequest) {
        String tweet = tweetPostRequest.getMessage();
        if (StringUtil.isEmpty(tweet)) {
            logger.error("Enter a Valid Tweet");
            return Response.status(400, "Please Enter a valid tweet").build();
        } else {
            try {
                Status status = twitterimpl.sendTweets(tweet);
                if (status.getText().equals(tweet)) {
                    logger.info("Tweet Send Successfully");
                    return Response.status(200, "Tweeted Successfully").build();
                } else {
                    logger.error("Tweet Was Not Done Invalid Request");
                    return Response.status(500, "Request was not completed").build();
                }
            } catch (BadRequestException e) {
                logger.error("Tweet Was Not Done Invalid Request", e);
                return Response.status(400, "Request tweet is not correct").build();
            } catch (Exception e) {
                logger.error("Tweet Was Not Sent");
                return Response.status(500, "Request Was Not Completed").build();
            }
        }
    }

    @GET
    @Path("/getTimeline")
    public Response getTweets() {
        List<String> tweets;
        try {
            tweets = twitterimpl.fetchLatestTweet();
        } catch (Exception e) {
            logger.error("Tweet could not be fetched");
            return Response.status(500, "Request Was Not Completed").build();
        }
        return Response.ok(tweets).build();
    }
}
