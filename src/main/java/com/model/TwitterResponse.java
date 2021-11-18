package com.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterResponse {
    User user;
    ArrayList<String> twitterHandles=new ArrayList<>();
    List<String> twitterResponseList=new ArrayList<>();
    public TwitterResponse(User user)
    {
        this.user=user;
    }
    public List<String> getTwitterResponseList() {
        twitterHandles=user.getTwitterHandles();
        twitterResponseList.add(user.getName());
        for (int i=0;i<twitterHandles.size();i++)
        {
            twitterResponseList.add(twitterHandles.get(i));
        }
        twitterResponseList.add(user.getProfileImageUrl());
        return twitterResponseList;
    }
}


