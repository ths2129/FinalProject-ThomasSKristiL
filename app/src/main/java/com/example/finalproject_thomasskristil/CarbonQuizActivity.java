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

class CarbonQuizActivity extends AppCompatActivity {

    RadioGroup radioGroup; //contains the radioGroup buttons
    TextView first, second, theScore;
    RadioButton q1, q2, q3, q4; //the four radiobutton questions
    Button button;
    EditText user; //included for user interactivity
    int questionSequence, score; //score
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mHighScoreDataBaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_quiz);
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mHighScoreDataBaseReference = mFireBaseDatabase.getReference("Quiz Score");

        radioGroup = (RadioGroup) findViewById(R.id.parentRadio);
        user = (EditText) findViewById(R.id.name);
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
                user.setEnabled(true);
                button.setText(R.string.next);
                score = 0;

                first.setText(R.string.qu1);
                q1.setText(R.string.anw1);
                q2.setText(R.string.anw2);
                q3.setText(R.string.anw3);
                q4.setText(R.string.anw4); // correct answer
                questionSequence = 1;
                break;
            }
            case 1: {
                user.setEnabled(false);
                first.setText(R.string.qu2);
                q1.setText(R.string.anw5);
                q2.setText(R.string.anw6); // correct answer
                q3.setText(R.string.anw7);
                q4.setText(R.string.anw8);

                if (q4.isChecked()) { //conditional marks correct radiobutton
                    second.setText(R.string.right_answer);
                    score = score + 5;
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();

                } else { // user can learn from their wrong answer
                    second.setText("Incorrect" + "\nD was correct");
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();
                }
                questionSequence = 2;
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                break;
            }
            case 2: {
                first.setText(R.string.qu3);
                q1.setText(R.string.anw9);
                q2.setText(R.string.anw10);
                q3.setText(R.string.anw11); //correct answer
                q4.setText(R.string.anw12);
                if (q2.isChecked()) {
                    score = 5 + score;
                    second.setText(R.string.correct);
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();

                } else {
                    second.setText("Not right" + "\nB was the right answer");
                }
                questionSequence = 3;
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                break;
            }
            case 3: {

                first.setText(R.string.qu4);
                q1.setText(R.string.anw13); //correct answer
                q2.setText(R.string.anw14);
                q3.setText(R.string.anw15);
                q4.setText(R.string.anw16);
                if (q3.isChecked()) {
                    score = score + 5;
                    second.setText(R.string.right_answer);
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();
                } else {
                    second.setText("Not right" + "\nC was the right answer");
                }
                questionSequence = 4; //questions
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                break;
            }
            case 4: {
                first.setText(R.string.qu5);
                q1.setText(R.string.anw17);
                q2.setText(R.string.anw18);
                q3.setText(R.string.anw19); //correct answer
                q4.setText(R.string.anw20);
                if (q1.isChecked()) {
                    score = score + 5;
                    second.setText(R.string.right_answer);
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();
                } else {
                    second.setText("Not right" + "\nA was the right answer");
                }
                questionSequence = 5; //last question
                q1.setChecked(false);
                q2.setChecked(false);
                q3.setChecked(false);
                q4.setChecked(false);
                button.setText("All Done");
                break;
            }
            case 5: {
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);
                if (q3.isChecked()) {
                    score = score + 5;
                    second.setText(R.string.right_answer);
                    Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();

                } else {
                    second.setText("Not right" + "\nC was the right answer");
                }
                if (score <= 1) {
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.better_luck, Toast.LENGTH_SHORT).show();
                } else if (score < 5) {
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.two, Toast.LENGTH_SHORT).show();

                } else if (score < 10) {
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.three, Toast.LENGTH_SHORT).show();

                } else if (score < 15) {
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.four, Toast.LENGTH_SHORT).show();

                } else if (score <= 20) {
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.five, Toast.LENGTH_SHORT).show();

                } else { //perfect score
                    theScore.setText(user.getText() + "'s score is " + score);
                    Toast.makeText(this, R.string.perfect, Toast.LENGTH_SHORT).show();

                }

                ForumConstructor scorePush = new ForumConstructor(theScore.getText().toString(), null, null); //sends only the score to the database
                mHighScoreDataBaseReference.push().setValue(scorePush);
                button.setText(R.string.play_again);
                questionSequence = 0; //back to zero - beginning
                break;
            }
        }
    }
}