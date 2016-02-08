package com.ravimandala.labs.instagramclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    private ArrayList<InstagramPhoto> instagramPhotos;
    private InstagramPhotoAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        instagramPhotos = new ArrayList<>();

        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        aPhotos = new InstagramPhotoAdapter(this, instagramPhotos);
        lvPhotos.setAdapter(aPhotos);

        // Send out API request to get popular photos
        fetchPopularPhotos();
    }

    // Send out the network request
    private void fetchPopularPhotos() {
        AsyncHttpClient client = new AsyncHttpClient();

        String apiEndpoint = "https://api.instagram.com/v1/media/popular?client_id=";
        client.get(apiEndpoint + getString(R.string.client_id), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                for (int i = 0; i < photosJSON.length(); i++) {
                    try {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        InstagramPhoto iPhoto = new InstagramPhoto();
                        if (photoJSON.optJSONObject("user") != null) {
                            iPhoto.username = photoJSON.getJSONObject("user").getString("username");
                        }
                        if (photoJSON.optJSONObject("caption") != null) {
                            iPhoto.caption = photoJSON.getJSONObject("caption").getString("text");
                        }
                        if (photoJSON.optJSONObject("images") != null &&
                                photoJSON.getJSONObject("images").optJSONObject("standard_resolution") != null) {
                            iPhoto.url = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        }
                        iPhoto.type = photoJSON.getString("type");
                        if ("image".equals(iPhoto.type)) {
                            instagramPhotos.add(iPhoto);
                        }
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
                aPhotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Network", "Http request failed with statusCode = " + statusCode + " and error: " + throwable.getMessage());
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
}
