package com.resource;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterWorks {
    @POST
    @Path("/tweet")
    public Response sendTweet(String tweet){
        //String tweet=Request.getMessage(request);
        String ret=null;
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
    @Path("/timeline")
    public String[] timeline()
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
class Request
{
    public static String getMessage(Request request)
    {
        return null;
    }

}
