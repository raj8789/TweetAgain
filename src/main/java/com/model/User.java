package com.model;
import java.util.ArrayList;
public class User {
    String twitterHandle;
    String name;
    String profileImageUrl;
    String screenName;
    String createdAt;

    ArrayList<String> tweets =new ArrayList<>();

    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
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
}

