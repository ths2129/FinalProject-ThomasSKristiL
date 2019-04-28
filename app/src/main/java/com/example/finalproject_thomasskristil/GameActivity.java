package com.example.finalproject_thomasskristil;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {

    // Frame
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    private ImageView character, garbage, orange, earth;
    private Drawable imageBoxRight, imageBoxLeft;

    // Size
    private int characterSize;

    // Position
    private float boxX, boxY;
    private float blackX, blackY;
    private float orangeX, orangeY;
    private float earthX, earthY;

    // Score
    private TextView scoreLabel, highScoreLabel;
    private int score, highScore, timeCount;
    private SharedPreferences settings;

    // Class
    private Timer timer;
    private Handler handler = new Handler();
    private SoundPlayer soundPlayer;

    // Status
    private boolean start_flg = false;
    private boolean action_flg = false;
    private boolean earthDrop = false;

    private MediaPlayer background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        background = MediaPlayer.create(this, R.raw.game_music);
        background.setLooping(true);
        background.start();



        soundPlayer = new SoundPlayer(this);

        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        character = findViewById(R.id.character);
        garbage = findViewById(R.id.garbage);
        orange = findViewById(R.id.orange);
        earth = findViewById(R.id.earth);
        scoreLabel = findViewById(R.id.scoreLabel);
        highScoreLabel = findViewById(R.id.highScoreLabel);

        imageBoxLeft = getResources().getDrawable(R.drawable.box_left);
        imageBoxRight = getResources().getDrawable(R.drawable.box_right);

        // High Score
        settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("High Score : " + highScore);
    }

    public void changePos() {

        // Add timeCount
        timeCount += 20;

        // Orange
        orangeY += 12;

        float orangeCenterX = orangeX + orange.getWidth() / 2;
        float orangeCenterY = orangeY + orange.getHeight() / 2;

        if (hitCheck(orangeCenterX, orangeCenterY)) {
            orangeY = frameHeight + 100;
            score += 5;
            soundPlayer.setWaterBottle();
        }

        if (orangeY > frameHeight) {
            orangeY = -100;
            orangeX = (float) Math.floor(Math.random() * (frameWidth - orange.getWidth()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        // Earth Drop
        if (!earthDrop && timeCount % 10000 == 0) {
            earthDrop = true;
            earthY = -20;
            earthX = (float) Math.floor(Math.random() * (frameWidth - earth.getWidth()));
        }

        if (earthDrop) {
            earthY += 20;

            float pinkCenterX = earthX + earth.getWidth() / 2;
            float pinkCenterY = earthY + earth.getWidth() / 2;

            if (hitCheck(pinkCenterX, pinkCenterY)) {
                earthY = frameHeight + 30;
                score += 15;
                // Change FrameWidth
                if (initialFrameWidth > frameWidth * 110 / 100) { //keeping in the decreasing and increasing frame
                    frameWidth = frameWidth * 110 / 100;
                    changeFrameWidth(frameWidth);
                }
                soundPlayer.setEarth(); //sound affect in Sounplayer Folder
            }

            if (earthY > frameHeight) earthDrop = false;
            earth.setX(earthX);
            earth.setY(earthY);
        }

        // Black
        blackY += 18;

        float blackCenterX = blackX + garbage.getWidth() / 2;
        float blackCenterY = blackY + garbage.getHeight() / 2;

        if (hitCheck(blackCenterX, blackCenterY)) {
            blackY = frameHeight + 100;
            score -= 5;


            // Change FrameWidth
            frameWidth = frameWidth * 80 / 100;
            changeFrameWidth(frameWidth);
            soundPlayer.setGarbage();
            if (frameWidth <= characterSize) {
                gameOver();
            }

        }

        if (blackY > frameHeight) {
            blackY = -100;
            blackX = (float) Math.floor(Math.random() * (frameWidth - garbage.getWidth()));
        }

        garbage.setX(blackX);
        garbage.setY(blackY);

        // This is where character ID moves
        if (action_flg) {
            // mouse click
            boxX += 14;
            character.setImageDrawable(imageBoxRight);
        } else {
            // mouse release
            boxX -= 14;
            character.setImageDrawable(imageBoxLeft);
        }

        // Check box position.
        if (boxX < 0) {
            boxX = 0;
            character.setImageDrawable(imageBoxRight);
        }
        if (frameWidth - characterSize < boxX) {
            boxX = frameWidth - characterSize;
            character.setImageDrawable(imageBoxLeft);
        }

        character.setX(boxX);

        scoreLabel.setText("The Score : " + score);

    }

    public boolean hitCheck(float x, float y) {
        if (boxX <= x && x <= boxX + characterSize &&
                boxY <= y && y <= frameHeight) {
            return true;
        }
        return false;
    }

    public void changeFrameWidth(int frameWidth) {
        ViewGroup.LayoutParams params = gameFrame.getLayoutParams();
        params.width = frameWidth;
        gameFrame.setLayoutParams(params);
    }

    public void gameOver() {
        // Stop timer.
        timer.cancel();
        timer = null;
        start_flg = false;
        Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();

        // Before showing startLayout, sleep 1 second.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        changeFrameWidth(initialFrameWidth);

        startLayout.setVisibility(View.VISIBLE);
        character.setVisibility(View.INVISIBLE);
        garbage.setVisibility(View.INVISIBLE);
        orange.setVisibility(View.INVISIBLE);
        earth.setVisibility(View.INVISIBLE);

        // Update High Score
        if (score > highScore) {
            highScore = score;
            highScoreLabel.setText("High Score : " + highScore);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", highScore);
            editor.commit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flg) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;

            }
        }
        return true;
    }

    public void startGame(View view) {
        start_flg = true;
        startLayout.setVisibility(View.INVISIBLE);

        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            initialFrameWidth = frameWidth;

            characterSize = character.getHeight();
            boxX = character.getX();
            boxY = character.getY();
        }

        frameWidth = initialFrameWidth;

        //where the chnaracter and falling pieces begin
        character.setX(0.0f);
        garbage.setY(3000.0f);
        orange.setY(3000.0f);
        earth.setY(3000.0f);

        orangeY = orange.getY();
        earthY = earth.getY();
        blackY = character.getY();


        character.setVisibility(View.VISIBLE);
        garbage.setVisibility(View.VISIBLE);
        orange.setVisibility(View.VISIBLE);
        earth.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        scoreLabel.setText("The Score : 0");


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }
        }, 0, 20);
    }

    public void quitGame(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        background.stop();
    }

}





/*

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
    }}

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
    */
