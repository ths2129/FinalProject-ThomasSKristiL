package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, R.string.welcome, Toast.LENGTH_LONG).show();
        //Log.d(Tag, "onCreate start");
    }

    public void Begin(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}