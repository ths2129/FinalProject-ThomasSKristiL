package com.example.finalproject_thomasskristil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Button rotateButton, blinkButton, bounceButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        rotateButton = findViewById(R.id.rotate_button);
        blinkButton = findViewById(R.id.blink_button);
        bounceButton = findViewById(R.id.bounce_button);

        final ImageView trash = findViewById(R.id.trash_bin);
        final ImageView recycle = findViewById(R.id.recycle_bin);
        final ImageView compost = findViewById(R.id.compost_bin);


        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trash.startAnimation(rotate);
            }
        });

        blinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycle.startAnimation(blink);
            }
        });
        bounceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compost.startAnimation(bounce);
            }
        });
    }}
