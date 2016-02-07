package com.ravimandala.labs.instagramclient;

/**
 * Created by ravi.mandala on 2/7/16.
 */
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
