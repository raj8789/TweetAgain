/**
 * User class contain user details
 */
package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    /**
     * .
     * twitterHandle takes timeline
     */
    private String twitterHandle;
    /**
     * .
     * name takes name of user
     */
    private String name;
    /**
     * .
     * profileImageUrl takes profileImageUrl
     */
    private String profileImageUrl;

    /**
     * @param ttwitterHandle   specifies ScreenName of user who posted tweet.
     * @param nname            specifies name of user who posted tweet.
     * @param pprofileImageUrl specifies URL profileImage of user who posted tweet.
     */
    public User(final String ttwitterHandle, final String nname, final String pprofileImageUrl) {
        this.twitterHandle = ttwitterHandle;
        this.name = nname;
        this.profileImageUrl = pprofileImageUrl;
    }
}