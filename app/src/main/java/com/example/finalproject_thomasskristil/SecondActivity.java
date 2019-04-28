package com.example.finalproject_thomasskristil;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    RecyclerView recyclerView;
    private ArrayList<Selection>selections; //Connects to the intialData method
    private SelectionAdapter selectionAdapter; //calling the adapter
    private MediaPlayer background;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);


        background = MediaPlayer.create(this, R.raw.recycler_screen_music);
        background.setLooping(true);
        background.start();

        dataToInitialize();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectionAdapter = new SelectionAdapter(selections, this); //calling the variable
        recyclerView.setAdapter(selectionAdapter);
            }



    public void dataToInitialize () {
                    selections = new ArrayList<>(); //This is calling the global array variable
                    selections.add(new Selection(R.string.quiz, R.string.quiz_description, R.drawable.foot)); //linked to global array and Question class
                    selections.add(new Selection(R.string.email, R.string.email_description, R.drawable.letter)); //linked to global array and Question class
                    selections.add(new Selection(R.string.forum, R.string.forum_description, R.drawable.forum)); //linked to global array and Question class
                    selections.add(new Selection(R.string.company, R.string.company_description, R.drawable.company)); //linked to global array and Question class
                    selections.add(new Selection(R.string.recycling_centers, R.string.recycling_centers_description, R.drawable.recycle));
                    selections.add(new Selection(R.string.game, R.string.game_description, R.drawable.game));
                }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Intent intent = new Intent();

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "Share it Baby", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sign_out_menu:
                Toast.makeText(this,"Bye", Toast.LENGTH_LONG).show();
                signOut();
                return true;
            case R.id.camera:
                Toast.makeText(this, "capture Baby", Toast.LENGTH_SHORT).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //(takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
            }}

    private void signOut() {
        signOut();
    }


    public void onDestroy() {
        super.onDestroy();
        background.stop();
    }
    public void onPause() {
        super.onPause();
        background.stop();
}}

