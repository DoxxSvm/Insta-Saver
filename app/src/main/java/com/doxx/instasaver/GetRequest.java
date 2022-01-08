package com.doxx.instasaver;

import android.app.Activity;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;

public class GetRequest {
    static ReturnClass returnClass = new ReturnClass();
    public static ReturnClass get(String url, VideoView videoView, MediaController mediaController){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                MainURL mainURL = gson.fromJson(response, MainURL.class);
                String reelurl = mainURL.getGraphql().getShortcode_media().getVideo_url();
                Uri uri = Uri.parse(reelurl);
                Toast.makeText(videoView.getContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                returnClass.uri = uri;
                returnClass.success=true;
                Toast.makeText(videoView.getContext(), (returnClass.uri).toString(),Toast.LENGTH_SHORT).show();
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(videoView.getContext(), "Error", Toast.LENGTH_SHORT).show();
                returnClass.success=false;
                //returnClass.uri=null;
            }
        }) ;

        RequestQueue queue = Volley.newRequestQueue(videoView.getContext());
        queue.add(stringRequest);
        Toast.makeText(videoView.getContext(), (returnClass.uri).toString(),Toast.LENGTH_SHORT).show();
        return returnClass;



    }


}
