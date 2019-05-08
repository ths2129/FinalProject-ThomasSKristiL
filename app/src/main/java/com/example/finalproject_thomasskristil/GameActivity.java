package com.example.finalproject_thomasskristil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout; //llibrary
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity { //use Viewgroup for User components * enevt handling

    // the screen
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout; //XML

    private ImageView character, garbage, plastic, recycle, earth, bus;
    private Drawable imageBoxRight, imageBoxLeft;
    // Size
    private int characterSize;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    // osition of pieces
    private float characterX, characterY; //decimals
    private float garbageX, garbageY;
    private float plasticX, plasticY;
    private float recycleX, recycleY;
    private float earthX, earthY;
    private float busX, busY;

    // Scores
    private TextView scoreLabel, highScoreLabel;
    private int score, highScore, timeCount;
    private SharedPreferences settings;


    private Timer timing;//imported library
    private Handler handler = new Handler();
    private SoundPlayer soundPlayer;

    // status
    private boolean start_flg = false; //OnTouchEvent Method
    private boolean action_flg = false;
    private boolean earthDrop = false;

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mHighScoreDataBaseReference;

    private MediaPlayer background; //My main music


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        background = MediaPlayer.create(this, R.raw.game_music);
        background.setLooping(true); //keeps going
        background.start(); //begins

        soundPlayer = new SoundPlayer(this); //main music

        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mHighScoreDataBaseReference = mFireBaseDatabase.getReference("High Score");

        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        character = findViewById(R.id.character);
        garbage = findViewById(R.id.garbage);
        plastic = findViewById(R.id.plastic);
        recycle = findViewById(R.id.recycle_game_piece);
        earth = findViewById(R.id.earth);
        bus = findViewById(R.id.bus);
        scoreLabel = findViewById(R.id.scoreLabel);
        highScoreLabel = findViewById(R.id.highScoreLabel);

        imageBoxLeft = getResources().getDrawable(R.drawable.box_left); //character image

        imageBoxRight = getResources().getDrawable(R.drawable.box_right);

        // High Score
        settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("HIgh Score : " + highScore);
    }

    public void changePos() {

        // adds to the fall
        timeCount += 20;

        // plastic bottle
        float plasticCenterX = plasticX + plastic.getWidth() / 2;

        float plasticCenterY = plasticY + plastic.getHeight() / 2;
        plasticY += 12;

        if (hitCheck(plasticCenterX, plasticCenterY)) {
            plasticY = frameHeight + 100;
            score += 5;
            soundPlayer.setWaterBottle();
        }

        if (plasticY > frameHeight) {
            plasticY = -100;
            plasticX = (float) Math.floor(Math.random() * (frameWidth - plastic.getWidth()));
        }
        plastic.setX(plasticX);
        plastic.setY(plasticY);


        recycleY += 40;

        float recycleCenterX = recycleX + recycle.getWidth() / 2;
        float recycleCenterY = recycleY + recycle.getHeight() / 2;

        if (hitCheck(recycleCenterX, recycleCenterY)) {
            recycleY = frameHeight + 100;
            score += 25;
            soundPlayer.setWaterBottle();
        }

        if (recycleY > frameHeight) {
            recycleY = -12000;
            recycleX = (float) Math.floor(Math.random() * (frameWidth - recycle.getWidth()));
        }
        recycle.setX(recycleX);
        recycle.setY(recycleY);


//DOve not a bus!
        busX += 10; //speed

        float busCenterX = busX + bus.getWidth() / 2;
        float busCenterY = busY + bus.getHeight() / 2;

        if (hitCheck(busCenterX, busCenterY)) {
            busX = frameWidth + 0;
            score += 0;
            soundPlayer.setWaterBottle();
        }

        if (busX > frameWidth) {
            busX = -11000;
            busY = (float) Math.floor(Math.random() * (frameWidth - bus.getWidth()));
        }
        bus.setX(busX);
        bus.setY(busY);


        // Earth Drop
        if (!earthDrop && timeCount % 10000 == 0) { //starts over again
            earthDrop = true;
            earthY = -20;
            earthX = (float) Math.floor(Math.random() * (frameWidth - earth.getWidth()));
        }

        if (earthDrop) {
            earthY += 20;

            float earthCenterX = earthX + earth.getWidth() / 2;
            float earthCenterY = earthY + earth.getWidth() / 2;

            if (hitCheck(earthCenterX, earthCenterY)) {
                earthY = frameHeight + 30;
                score += 15;
                // Change FrameWidth
                if (initialFrameWidth > frameWidth * 110 / 100) { //opens the frame, but won't bypass orginalsize
                    frameWidth = frameWidth * 110 / 100;
                    changeFrameWidth(frameWidth);
                }
                soundPlayer.setEarth(); //sound affect in Sounplayer Folder
            }

            if (earthY > frameHeight) earthDrop = false;
            earth.setX(earthX);
            earth.setY(earthY);
        }

        // Garbage
        garbageY += 18; // count

        float garbageCenterX = garbageX + garbage.getWidth() / 2;
        float garbageCenterY = garbageY + garbage.getHeight() / 2;

        if (hitCheck(garbageCenterX, garbageCenterY)) {
            garbageY = frameHeight + 100;
            score -= 5;


            // Change FrameWidth this is how you lose!
            frameWidth = frameWidth * 80 / 100;
            changeFrameWidth(frameWidth);
            soundPlayer.setGarbage();
            if (frameWidth <= characterSize) { //loser
                gameOver(); // toast message
                ForumConstructor scorePush = new ForumConstructor(scoreLabel.getText().toString(), null, null);
                mHighScoreDataBaseReference.push().setValue(scorePush); // pushes high score to database
            }

        }

        if (garbageY > frameHeight) {
            garbageY = -100; //falls down the Y axis
            garbageX = (float) Math.floor(Math.random() * (frameWidth - garbage.getWidth())); //random x fall
        }

        garbage.setX(garbageX); //setting its location
        garbage.setY(garbageY);

        // This is where character ID moves
        if (action_flg) {
            characterX += 15; //clicking the mouse adds a 15 plus to the x axis
            character.setImageDrawable(imageBoxRight); //character moves
        } else {
            // mouse release
            characterX -= 15; //clicking the mouse deletes a 15 plus to the x axis
            character.setImageDrawable(imageBoxLeft);
        }

        if (characterX < 0) {
            characterX = 0;
            character.setImageDrawable(imageBoxRight);
        }
        if (frameWidth - characterSize < characterX) {
            characterX = frameWidth - characterSize;
            character.setImageDrawable(imageBoxLeft);
        }

        character.setX(characterX);

        scoreLabel.setText("High Score : " + score);

    }

    public boolean hitCheck(float x, float y) {
        if (characterX <= x && x <= characterX + characterSize &&
                characterY <= y && y <= frameHeight) {
            return true;
        }
        return false;
    }

    public void changeFrameWidth(int frameWidth) { //Frame chnane method
        ViewGroup.LayoutParams params = gameFrame.getLayoutParams(); //viewgroup
        params.width = frameWidth;
        gameFrame.setLayoutParams(params);
    }

    public void gameOver() {
        // Stop timer.
        timing.cancel();
        timing = null;
        start_flg = false; //no move moving
        Toast.makeText(this, R.string.game_over, Toast.LENGTH_LONG).show();

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
        plastic.setVisibility(View.INVISIBLE);
        recycle.setVisibility(View.INVISIBLE);//can see after game end
        earth.setVisibility(View.INVISIBLE);
        bus.setVisibility(View.INVISIBLE);

        // Update High Score in database
        if (score > highScore) {
            highScore = score; //keeps highest score
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
            characterX = character.getX();
            characterY = character.getY();
        }

        frameWidth = initialFrameWidth;

        //where the chnaracter and falling pieces begin
        character.setX(0.0f);
        garbage.setY(3000.0f);
        plastic.setY(3000.0f);
        recycle.setY(3000.0f);
        earth.setY(3000.0f);
        bus.setX(0.0f);

        plasticY = plastic.getY();
        earthY = earth.getY();
        recycleY = recycle.getY();
        garbageY = character.getY();
        busY = bus.getY();


        character.setVisibility(View.VISIBLE); //pieces are now vivible
        garbage.setVisibility(View.VISIBLE);
        plastic.setVisibility(View.VISIBLE);
        recycle.setVisibility(View.VISIBLE);
        earth.setVisibility(View.VISIBLE);
        bus.setVisibility(View.VISIBLE);


        timeCount = 0;
        score = 0;
        scoreLabel.setText(R.string.starting_score);
        timing = new Timer();
        timing.schedule(new TimerTask() {
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

    public void quitGame(View view) { //onClick
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selection_screen:
                Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
                selectionScreen();
                background.stop();
                return true;
            case R.id.camera:
                Toast.makeText(this, "capture Baby", Toast.LENGTH_SHORT).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //(takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                background.stop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void selectionScreen() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        background.stop();
    }

    private void signOut() {
        background.stop();
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

    public void onPause() {
        super.onPause();
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
