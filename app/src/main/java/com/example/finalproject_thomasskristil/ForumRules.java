package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForumRules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_rules);
    }

    public void Begin(View view) {
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }
}