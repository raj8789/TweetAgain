package com.model;
import java.util.ArrayList;
public class User {
    String twitterHandle;
    String name;
    String profileImageUrl;
    ArrayList<String> twitterHandles=new ArrayList<>();
    public User(){
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    public ArrayList<String> getTwitterHandles() {
        return twitterHandles;
    }
    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
        twitterHandles.add(twitterHandle);
    }
}

