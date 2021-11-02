package com.resource;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.TwitterException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class SendRetrieveTweets {
        @POST
        @Path("/postTweet")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response sendTweet(TweetPostRequest tweetPostRequest){
            String tweet=tweetPostRequest.getMessage();
            if(StringUtil.isEmpty(tweet)) {
                return Response.status(400,"Please Enter a valid tweet").build();
            }
            else {
                try {
                    SendTweet.sendTweets(tweet);
                }
                catch (TwitterException e)
                {
                    return Response.status(500,"Request Was Not Completed").build();
                }
                return Response.status(200,"Tweeted Successfully").build();
            }
        }
        @GET
        @Path("/getTimeline")
        public ArrayList<String> timeLine()
        {
            return RetrieveTweets.latestTweet();
        }
}
