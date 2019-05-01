package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class GameStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
    }


    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }


    public void startGame (View view){
        //startActivity(new Intent(getApplicationContext(), GameActivity.class));

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void goBack (View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
