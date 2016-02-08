package com.ravimandala.labs.instagramclient;

public class InstagramPhoto {

    public String username;
    public String caption;
    public String url;
    public String type;

    @Override
    public String toString() {
        return "Username: " + username + "; caption = " + caption + "; image url = " + url + "; type = " + type;
    }
}
