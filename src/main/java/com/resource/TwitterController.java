package com.resource;

import com.model.TwitterResponse;
import com.model.SendResponse;
import com.service.TwitterImpl;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sun.jvm.hotspot.memory.HeapBlock;
import twitter4j.Status;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Produces(MediaType.APPLICATION_JSON)
@RestController
public class TwitterController {
    private final Logger logger = LoggerFactory.getLogger(TwitterController.class);
    @Autowired
    TwitterImpl twitterimpl;
    SendResponse sendResponse;
    // used by test class
    public TwitterController(TwitterImpl twitterimpl) {
        this.twitterimpl = twitterimpl;
    }
    public TwitterController() {
    }
    @RequestMapping(method = RequestMethod.POST,value = "postTweet")
    public ResponseEntity<SendResponse> sendTweet(@RequestBody TweetPostRequest tweetPostRequest) {
        String tweet = tweetPostRequest.getMessage();
        HttpHeaders headers = new HttpHeaders();
        if (StringUtil.isEmpty(tweet))
        {
            logger.error("Enter a Valid Tweet");
            return new ResponseEntity<SendResponse>(
                    new SendResponse("Please enter a Valid Tweet"),headers,HttpStatus.BAD_REQUEST_400);
        } else
        {
            try
            {
                Status status = twitterimpl.sendTweets(tweet);
                if (status.getText().equals(tweet))
                {
                    logger.info("Tweet Send Successfully");
                    return new ResponseEntity<SendResponse>(
                            new SendResponse("Tweet posted Successfully"),headers,HttpStatus.OK_200);
                } else
                {
                    logger.error("Tweet Was Not Done Invalid Request");
                    return new ResponseEntity<SendResponse>(
                            new SendResponse("Request tweet is not correct"),headers,HttpStatus.INTERNAL_SERVER_ERROR_500);
                }
            } catch (BadRequestException e) {
                logger.error("Tweet Was Not Done Invalid Request", e);
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR_500, "Please enter a Valid Tweet",e);
            } catch (Exception e) {
                logger.error("Tweet Was Not Sent");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST_400, "Request tweet is not correct",e);
            }
        }
    }
    @RequestMapping("/getTimeline")
    public Response getTweets() {
        ArrayList<TwitterResponse> tweets;
        try {
            tweets = twitterimpl.fetchLatestTweet();
        } catch (Exception e) {
            logger.error("Tweet could not be fetched");
            return Response.status(500, "Request Was Not Completed").build();
        }
        return Response.ok(tweets).build();
    }
    @RequestMapping("/filter/{search}")
    public Response getFilterTweets(@PathVariable String  search) {
        List<TwitterResponse> tweets;
        try
        {
            tweets=twitterimpl.getTweetBasedOnMyFilter(search);
        }
        catch (Exception e)
        {
            logger.error("Tweet could not be fetched");
            return Response.status(500, "Request Was Not Completed").build();
        }
        return Response.ok(tweets).build();
    }
}
