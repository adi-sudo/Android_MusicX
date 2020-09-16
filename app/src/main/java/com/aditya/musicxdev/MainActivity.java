package com.aditya.musicxdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audioManager;

    //For playing music
    public void play(View view) {

        player.start();

    }

    //For pausing music
    public void pause(View view) {

        player.pause();

    }

    //For stoping music
    public void stop(View view) {

        player.stop();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        player = MediaPlayer.create(this, R.raw.music);

        SeekBar seekVol= findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress ,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekProg= findViewById(R.id.seekProg);
        seekProg.setMax(player.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProg.setProgress(player.getCurrentPosition());
            }
        } ,0,7000 );

        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}