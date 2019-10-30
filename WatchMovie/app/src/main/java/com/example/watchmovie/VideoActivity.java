package com.example.watchmovie;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    VideoView vid;
    MediaController m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vid = (VideoView)findViewById(R.id.videoView);
        MediaController m = new MediaController(this);
        vid.setMediaController(m);

        String path = "android.resource://com.example.watchmovie/"+R.raw.sample_video;

        Uri u = Uri.parse(path);

        vid.setVideoURI(u);

        vid.start();

    }
}
