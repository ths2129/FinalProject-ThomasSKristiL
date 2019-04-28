package com.example.finalproject_thomasskristil;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 3;

    private static SoundPool soundPool;
    private static int waterBottle;
    private static int earth;
    private static int garbage;



    public SoundPlayer(Context context) {



        // SoundPool is deprecated in API level 21. (Lollipop)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();

        } else {
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }

        ///*
        waterBottle = soundPool.load(context, R.raw.amazing, 1);
        earth = soundPool.load(context, R.raw.genius, 1);
        garbage = soundPool.load(context, R.raw.garbage_sound, 1);
        //*/
    }

    public void setWaterBottle() { soundPool.play(waterBottle, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void setEarth() {
        soundPool.play(earth, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void setGarbage() {
        soundPool.play(garbage, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
