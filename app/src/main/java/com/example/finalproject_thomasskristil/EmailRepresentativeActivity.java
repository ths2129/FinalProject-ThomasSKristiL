package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.finalproject_thomasskristil.Keys.PERSON;
import static com.example.finalproject_thomasskristil.Keys.REQUEST_CODE;

public class EmailRepresentativeActivity extends AppCompatActivity {


    private Spinner representative;
    private EditText emailBody;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mMessagesDataBaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_representative);
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDataBaseReference = mFireBaseDatabase.getReference().child("email");

        emailBody = (EditText) findViewById(R.id.email_body);



        representative = findViewById(R.id.district);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.name, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        representative.setAdapter(spinnerAdapter);


    }

    public void SubmitHome(View view) {

        String hasRep = (String) representative.getSelectedItem();

        final ForumConstructor emailToRep = new ForumConstructor(emailBody.getText().toString(), hasRep, null);
        mMessagesDataBaseReference.push().setValue(emailToRep);


        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",hasRep,null));
        email.putExtra(Intent.EXTRA_SUBJECT, "Environmental concerns from constituency"); // uses key
        email.putExtra(Intent.EXTRA_TEXT,emailBody.getText());
        setResult(REQUEST_CODE, email); //sends the whole email
        startActivity(Intent.createChooser(email,"Send email "));
        // finish();//launches to activity result - essential!


    }

}
