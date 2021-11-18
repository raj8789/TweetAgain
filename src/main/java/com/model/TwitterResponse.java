package com.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterResponse {
    User user;
    ArrayList<String> messages =new ArrayList<>();
    ArrayList<String> dates=new ArrayList<>();

    String name;
    String profileImageUrl;
    String screenName;
    String message;
    String created;
    @Override
    public String toString() {
        return "{" +
                ", message=" + message+
                ", User : {"+
                ", twitterHandle='" + screenName + '\'' +
                ", name='" + name + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                "}, "+
                ", createdAt='" + created + '\'' +
                '}';
    }
    List<String> twitterResponseList=new ArrayList<>();
    public TwitterResponse(String message, String screenName, String name, String profileImageUrl, String created)
    {
        this.name=name;
        this.profileImageUrl=profileImageUrl;
        this.screenName=screenName;
        this.message=message;
        this.created=created;
    }
    List<String> twitterResponseList1=new ArrayList<>();
    public TwitterResponse(User user)
    {
        this.user=user;
    }
    public void setCreatedAt(String createdAt) {
        dates.add(createdAt);
    }
    
    public ArrayList<String> getTweets() {
        return messages;
    }
    public void setTweet(String twitterHandle) {
        messages.add(twitterHandle);
    }
    public List<String> getTwitterResponseList()
    {
        name=user.getName();
        profileImageUrl=user.getProfileImageUrl();
        screenName=user.getScreenName();

        for (int i=0;i<messages.size();i++)
        {
            TwitterResponse twitterResponse=new TwitterResponse(messages.get(i),screenName,name,profileImageUrl,dates.get(i));
            int x=10;
            System.out.println(messages.get(i));
            twitterResponseList.add(twitterResponse.toString());
        }
        /*twitterResponseList1.add(user.getName());
        for (int i = 0; i< messages.size(); i++)
        {
            twitterResponseList1.add(messages.get(i));
        }
        twitterResponseList1.add(user.getProfileImageUrl());*/
        return twitterResponseList;
    }
}


