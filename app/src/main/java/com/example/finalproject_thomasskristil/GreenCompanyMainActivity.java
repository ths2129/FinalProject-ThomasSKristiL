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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.List;


public class GreenCompanyMainActivity extends AppCompatActivity {


    RecyclerView recyclerViews;
    private ArrayList<GreenCompanyConstructor> greencompanies; //Connects to the intialData method
    private GreenCompanyAdapter greenAdapter; //calling the adapter
    // private MediaPlayer background;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_green);

        // background = MediaPlayer.create(this, R.raw.recycler_screen_music);
        // background.setLooping(true);
        // background.start();

        dataToInitialize();
        recyclerViews = findViewById(R.id.recycler_view_green); //recycler object
        recyclerViews.setHasFixedSize(true);
        recyclerViews.setLayoutManager(new LinearLayoutManager(this));
        greenAdapter = new GreenCompanyAdapter(greencompanies, this); //calling the variable
        recyclerViews.setAdapter(greenAdapter);
    }


    public void dataToInitialize() {
        greencompanies = new ArrayList<>(); //This is calling the global array variable
        greencompanies.add(new GreenCompanyConstructor(R.string.email, R.string.email_description, R.drawable.tesla)); //linked to global array and Question class
        //greencompanies.add(new Selection(R.string.forum, R.string.forum_description, R.drawable.forum)); //linked to global array and Question class
        // greencompanies.add(new Selection(R.string.company, R.string.company_description, R.drawable.company)); //linked to global array and Question class
        // greencompanies.add(new Selection(R.string.recycling_centers, R.string.recycling_centers_description, R.drawable.recycle));
        // greencompanies.add(new Selection(R.string.game, R.string.game_description, R.drawable.game));*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
                //onDestroy();
                return true;
            case R.id.camera:
                Toast.makeText(this, "capture Baby", Toast.LENGTH_SHORT).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //(takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        signOut();
    }

    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
    }

    public void onPause() {
        super.onPause();
    }
}



