package com.example.livejagoroni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private String videoUrl = "Link Link Link Link";

    private ProgressDialog pd;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading.....");
        pd.setCancelable(true);

        playVideo();


    }

    private void playVideo() {
        try{
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            Uri videoUri = Uri.parse(videoUrl);

            videoView.setMediaController(mediaController);

            videoView.setVideoURI(videoUri);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pd.dismiss();
                    videoView.start();
                }
            });

        }
        catch (Exception e){
            pd.dismiss();
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
}
