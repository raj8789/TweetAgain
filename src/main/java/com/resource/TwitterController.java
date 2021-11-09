package com.resource;

import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterController {
    @POST
    @Path("/postTweet")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(TweetPostRequest tweetPostRequest) {
        String tweet = tweetPostRequest.getMessage();
        if (StringUtil.isEmpty(tweet)) {
            return Response.status(400, "Please Enter a valid tweet").build();
        } else {
            try {
                SendTweet sendTweet = new SendTweet();
                Status status = sendTweet.sendTweets(tweet);
                if (status.getText().equals(tweet)) {
                    return Response.status(200, "Tweeted Successfully").build();
                } else {
                    return Response.status(500, "Request was not completed").build();
                }
            } catch (TwitterException e) {
                return Response.status(500, "Request Was Not Completed").build();
            }
        }
    }

    @GET
    @Path("/getTimeline")
    public Response getTweets() {
        RetrieveTweets retrieveTweets = new RetrieveTweets();
        return retrieveTweets.fetchLatestTweet();
    }
}
