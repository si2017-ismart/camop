package com.mynewproject;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    VideoView videoview;

    String VideoURL="http://192.168.12.228:8082/stream.wmv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoview = (VideoView) findViewById(R.id.VideoView);
        pDialog = new ProgressDialog(VideoViewActivity.this);

        pDialog.setTitle("Video streaming");
        pDialog.setMessage("Loading...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try{
            MediaController mediaController = new MediaController(VideoViewActivity.this);
            mediaController.setAnchorView(videoview);
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(video);

        }
        catch (Exception e){
            Log.e("Error",e.getMessage());
            e.printStackTrace();

        }
        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                pDialog.dismiss();
                videoview.start();
            }
        });
    }
}
