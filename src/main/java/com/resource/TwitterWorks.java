package com.resource;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterWorks {
    @POST
    @Path("/postTweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(String tweet)
    {
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
    public String[] timeLine()
    {
           int size=RetrieveTweets.latestTweet().length;
            String ar[]=new String[size];
            int i=0;
            for(String s:RetrieveTweets.latestTweet())
            {
                ar[i]=s;
                i+=1;
            }
            return ar;
    }
}
class TweetPostRequest
{
    String message;
    TweetPostRequest()
    {

    }
    public String getMessage()
    {
        return message;
    }
    public  void setMessage(String message)
    {
                this.message=message;
    }
}
