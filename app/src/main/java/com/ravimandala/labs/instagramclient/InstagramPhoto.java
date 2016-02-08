package com.ravimandala.labs.instagramclient;

import java.util.Date;

public class InstagramPhoto {

    public String username;
    public String caption;
    public String mediaUrl;
    public String type;
    public String profilePicUrl;
    public int likes;
    public String createdTime;

    @Override
    public String toString() {
        return "Username: " + username + "; caption = " + caption + "; image url = " + mediaUrl
                + "; type = " + type + "; profile pic: " + profilePicUrl + "; likes = " + likes + "; createdTime = " + createdTime;
    }
}
