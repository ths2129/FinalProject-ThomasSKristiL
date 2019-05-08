package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GreenChoice extends AppCompatActivity {



    RadioGroup radioGroup; //contains the radioGroup buttons
    TextView first, second, theScore;
    RadioButton q1, q2, q3, q4; //the four radiobutton questions
    Button button;
    int questionSequence, score; //score
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mHighScoreDataBaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_choice);
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mHighScoreDataBaseReference = mFireBaseDatabase.getReference("Quiz Score");

        radioGroup = (RadioGroup) findViewById(R.id.parentRadio);
        q1 = (RadioButton) findViewById(R.id.choice1);
        q2 = (RadioButton) findViewById(R.id.choice2);
        q3 = (RadioButton) findViewById(R.id.choice3);
        q4 = (RadioButton) findViewById(R.id.choice4);
        first = (TextView) findViewById(R.id.ques);
        second = (TextView) findViewById(R.id.response);
        theScore = (TextView) findViewById(R.id.score);
        button = (Button) findViewById(R.id.next);
        questionSequence = 0; //always start with 0
        score = 0;

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
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
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

    public void quiz(View v) { //View shows it
        switch (questionSequence) { //switch is better than conditional for new screen
            case 0: {
                radioGroup.setVisibility(View.VISIBLE);
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                second.setText(""); //blank for now
                theScore.setText("");
                q1.setEnabled(true);
                q2.setEnabled(true);
                q3.setEnabled(true);
                q4.setEnabled(true);
                //button.setText(R.string.next);

                first.setText(R.string.map_question);
                q1.setText(R.string.makeup);
                q2.setText(R.string.clothing);
                q3.setText(R.string.technology);
                q4.setText(R.string.food); // correct answer
                questionSequence = 1;
                break;
            }
            case 1: {
                if (q1.isChecked()) { //conditional marks correct radiobutton
                   Intent intent = new Intent(this, GreenCOmpany.class);
                   startActivity(intent);
                    Toast.makeText(this, R.string.makeup, Toast.LENGTH_SHORT).show();

                } if (q2.isChecked()) { // user can learn from their wrong answer
                    Intent intent = new Intent(this, GreenClothing.class);
                    startActivity(intent);
                    Toast.makeText(this, R.string.clothing, Toast.LENGTH_SHORT).show();
                } if (q3.isChecked()) { // user can learn from their wrong answer
                    Intent intent = new Intent(this, GreenTech.class);
                    startActivity(intent);
                    Toast.makeText(this, R.string.technology, Toast.LENGTH_SHORT).show();
                } if (q4.isChecked()) { // user can learn from their wrong answer
                    Intent intent = new Intent(this, GreenFood.class);
                    startActivity(intent);
                    Toast.makeText(this, R.string.food, Toast.LENGTH_SHORT).show();

                questionSequence = 2;
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                break;
            }}}}}
