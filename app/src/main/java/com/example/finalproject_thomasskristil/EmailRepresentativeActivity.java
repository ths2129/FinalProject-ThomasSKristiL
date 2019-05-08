package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.finalproject_thomasskristil.Keys.PERSON;
import static com.example.finalproject_thomasskristil.Keys.REQUEST_CODE;
import static com.example.finalproject_thomasskristil.Keys.REQUEST_IMAGE_CAPTURE;

public class EmailRepresentativeActivity extends AppCompatActivity {


    private Spinner representative;
    private EditText emailBody;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mMessagesDataBaseReference;
    private int theRep = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_representative);
        Toast.makeText(this, "Select your District", Toast.LENGTH_LONG).show();

        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDataBaseReference = mFireBaseDatabase.getReference().child("email");

        emailBody = (EditText) findViewById(R.id.email_body);

        representative = findViewById(R.id.district);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.name, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        representative.setPrompt("Select your favorite Planet!");
        representative.setAdapter(spinnerAdapter);


        final String hasRep = (String) representative.getSelectedItem();
        representative.setPrompt("Select council district.");


            }
    public void SubmitHome(View view) {
        getRep();
    }


        public void getRep(){

        representative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0: // for item 1
                        Intent email1 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mguerra@council.nyc.gov;", null));
                        email1.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 1 constituent"); // uses key
                        //email1.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email1); //sends the whole email
                        startActivity(Intent.createChooser(email1, "Send email "));
                        break;
                    case 1:
                        Intent email2 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District2@council.nyc.gov", null));
                        email2.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 2 constituent"); // uses key
                        email2.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email2); //sends the whole email
                        startActivity(Intent.createChooser(email2, "Send email "));
                        break;
                    case 2:
                        Intent email3 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "SpeakerJohnson@council.nyc.gov; \u200E", null));
                        email3.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 3 constituent"); // uses key
                        email3.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email3); //sends the whole email
                        startActivity(Intent.createChooser(email3, "Send email "));
                        break;
                    case 3:
                        Intent email4 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "KPowers@council.nyc.gov", null));
                        email4.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 4 constituent"); // uses key
                        email4.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email4); //sends the whole email
                        startActivity(Intent.createChooser(email4, "Send email "));
                        break;
                    case 4:
                        Intent email5 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "BKallos@BenKallos.com", null));
                        email5.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 5 constituent"); // uses key
                        email5.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email5); //sends the whole email
                        startActivity(Intent.createChooser(email5, "Send email "));
                        break;
                    case 5:
                        Intent email6 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Helen@HelenRosenthal.com", null));
                        email6.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 6 constituent"); // uses key
                        email6.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email6); //sends the whole email
                        startActivity(Intent.createChooser(email6, "Send email "));
                        break;
                    case 6:
                        Intent email7 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District7@council.nyc.gov", null));
                        email7.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 7 constituent"); // uses key
                        email7.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email7); //sends the whole email
                        startActivity(Intent.createChooser(email7, "Send email "));
                        break;
                    case 7:
                        Intent email8 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "DAyala@council.nyc.gov", null));
                        email8.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 8 constituent"); // uses key
                        email8.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email8); //sends the whole email
                        startActivity(Intent.createChooser(email8, "Send email "));
                        break;
                    case 8:
                        Intent email9 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "D09perkins@council.nyc.gov", null));
                        email9.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 9 constituent"); // uses key
                        email9.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email9); //sends the whole email
                        startActivity(Intent.createChooser(email9, "Send email "));
                        break;
                    case 9:
                        Intent email10 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "yrodriguez@council.nyc.gov", null));
                        email10.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 10 constituent"); // uses key
                        email10.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email10); //sends the whole email
                        startActivity(Intent.createChooser(email10, "Send email "));
                        break;
                    case 10:
                        Intent email11 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District11@council.nyc.gov;", null));
                        email11.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 11 constituent"); // uses key
                        email11.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email11); //sends the whole email
                        startActivity(Intent.createChooser(email11, "Send email "));
                        break;
                    case 11:
                        Intent email12 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Andy.King@council.nyc.gov", null));
                        email12.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 12 constituent"); // uses key
                        email12.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email12); //sends the whole email
                        startActivity(Intent.createChooser(email12, "Send email "));
                        break;
                    case 12:
                        Intent email13 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "MGjonaj@council.nyc.gov", null));
                        email13.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 13 constituent"); // uses key
                        email13.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email13); //sends the whole email
                        startActivity(Intent.createChooser(email13, "Send email "));
                        break;
                    case 13:
                        Intent email14 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Mfcabrera@council.nyc.gov", null));
                        email14.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 14 constituent"); // uses key
                        email14.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email14); //sends the whole email
                        startActivity(Intent.createChooser(email14, "Send email "));
                        break;
                    case 14:
                        Intent email15 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Rtorres@council.nyc.gov", null));
                        email15.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 15 constituent"); // uses key
                        email15.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email15); //sends the whole email
                        startActivity(Intent.createChooser(email15, "Send email "));
                        break;
                    case 15:
                        Intent email16 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District16Bronx@council.nyc.gov", null));
                        email16.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 16 constituent"); // uses key
                        email16.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email16); //sends the whole email
                        startActivity(Intent.createChooser(email16, "Send email "));
                        break;
                    case 16:
                        Intent email17 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Dsalamanca@council.nyc.gov", null));
                        email17.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 17 constituent"); // uses key
                        email17.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email17); //sends the whole email
                        startActivity(Intent.createChooser(email17, "Send email "));
                        break;
                    case 17:
                        Intent email18 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "RDiaz@council.nyc.gov", null));
                        email18.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 18 constituent"); // uses key
                        email18.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email18); //sends the whole email
                        startActivity(Intent.createChooser(email18, "Send email "));
                        break;
                    case 18:
                        Intent email19 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "district19@council.nyc.gov", null));
                        email19.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 19 constituent"); // uses key
                        email19.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email19); //sends the whole email
                        startActivity(Intent.createChooser(email19, "Send email "));
                        break;
                    case 19:
                        Intent email20 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "pkoo@council.nyc.gov", null));
                        email20.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 20 constituent"); // uses key
                        email20.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email20); //sends the whole email
                        startActivity(Intent.createChooser(email20, "Send email "));
                        break;
                    case 20:
                        Intent email21 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "FMoya@council.nyc.gov", null));
                        email21.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 21 constituent"); // uses key
                        email21.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email21); //sends the whole email
                        startActivity(Intent.createChooser(email21, "Send email "));
                        break;
                    case 21:
                        Intent email22 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "No Email", null));
                        email22.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 22 constituent"); // uses key
                        email22.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email22); //sends the whole email
                        startActivity(Intent.createChooser(email22, "Send email "));
                        break;
                    case 22:
                        Intent email23 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "BGrodenchik@council.nyc.gov", null));
                        email23.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 23 constituent"); // uses key
                        email23.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email23); //sends the whole email
                        startActivity(Intent.createChooser(email23, "Send email "));
                        break;
                    case 23:
                        Intent email24 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "RLancman@council.nyc.gov <RLancman@council.nyc.gov>;", null));
                        email24.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 24 constituent"); // uses key
                        email24.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email24); //sends the whole email
                        startActivity(Intent.createChooser(email24, "Send email "));
                        break;
                    case 24:
                        Intent email25 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "dromm@council.nyc.gov <dromm@council.nyc.gov>;", null));
                        email25.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 25 constituent"); // uses key
                        email25.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email25); //sends the whole email
                        startActivity(Intent.createChooser(email25, "Send email "));
                        break;
                    case 25:
                        Intent email26 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "JVanBramer@council.nyc.gov <JVanBramer@council.nyc.gov>;", null));
                        email26.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 26 constituent"); // uses key
                        email26.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email26); //sends the whole email
                        startActivity(Intent.createChooser(email26, "Send email "));
                        break;
                    case 26:
                        Intent email27 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District27@council.nyc.gov <District27@council.nyc.gov>;", null));
                        email27.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 27 constituent"); // uses key
                        email27.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email27); //sends the whole email
                        startActivity(Intent.createChooser(email27, "Send email "));
                        break;
                    case 27:
                        Intent email28 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Adams@council.nyc.gov <Adams@council.nyc.gov>;", null));
                        email28.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 28 constituent"); // uses key
                        email28.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email28); //sends the whole email
                        startActivity(Intent.createChooser(email28, "Send email "));
                        break;
                    case 28:
                        Intent email29 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Koslowitz@council.nyc.gov <Koslowitz@council.nyc.gov>;", null));
                        email29.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 29 constituent"); // uses key
                        email29.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email29); //sends the whole email
                        startActivity(Intent.createChooser(email29, "Send email "));
                        break;
                    case 29:
                        Intent email30 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District30@council.nyc.gov <District30@council.nyc.gov>;", null));
                        email30.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 30 constituent"); // uses key
                        email30.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email30); //sends the whole email
                        startActivity(Intent.createChooser(email30, "Send email "));
                        break;
                    case 30:
                        Intent email31 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "drichards@council.nyc.gov <drichards@council.nyc.gov>;", null));
                        email31.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 31 constituent"); // uses key
                        email31.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email31); //sends the whole email
                        startActivity(Intent.createChooser(email31, "Send email "));
                        break;
                    case 31:
                        Intent email32 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "eulrich@council.nyc.gov <eulrich@council.nyc.gov>;", null));
                        email32.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 32 constituent"); // uses key
                        email32.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email32); //sends the whole email
                        startActivity(Intent.createChooser(email32, "Send email "));
                        break;
                    case 32:
                        Intent email33 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "eulrich@council.nyc.gov <eulrich@council.nyc.gov>;", null));
                        email33.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 33 constituent"); // uses key
                        email33.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email33); //sends the whole email
                        startActivity(Intent.createChooser(email33, "Send email "));
                        break;
                    case 33:
                        Intent email34 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "areynoso@council.nyc.gov <areynoso@council.nyc.gov>;", null));
                        email34.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 34 constituent"); // uses key
                        email34.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email34); //sends the whole email
                        startActivity(Intent.createChooser(email34, "Send email "));
                        break;
                    case 34:
                        Intent email35 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "LCumbo@council.nyc.gov <LCumbo@council.nyc.gov>;", null));
                        email35.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 35 constituent"); // uses key
                        email35.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email35); //sends the whole email
                        startActivity(Intent.createChooser(email35, "Send email "));
                        break;
                    case 35:
                        Intent email36 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "district36@council.nyc.gov <district36@council.nyc.gov>;", null));
                        email36.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 36 constituent"); // uses key
                        email36.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email36); //sends the whole email
                        startActivity(Intent.createChooser(email36, "Send email "));
                        break;
                    case 36:
                        Intent email37 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "district36@council.nyc.gov <district36@council.nyc.gov>;", null));
                        email37.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 37 constituent"); // uses key
                        email37.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email37); //sends the whole email
                        startActivity(Intent.createChooser(email37, "Send email "));
                        break;
                    case 37:
                        Intent email38 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info38@council.nyc.gov <info38@council.nyc.gov>;", null));
                        email38.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 38 constituent"); // uses key
                        email38.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email38); //sends the whole email
                        startActivity(Intent.createChooser(email38, "Send email "));
                        break;
                    case 38:
                        Intent email39 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "lander@council.nyc.gov <lander@council.nyc.gov>;", null));
                        email39.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 39 constituent"); // uses key
                        email39.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email39); //sends the whole email
                        startActivity(Intent.createChooser(email39, "Send email "));
                        break;
                    case 39:
                        Intent email40 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "meugene@council.nyc.gov <meugene@council.nyc.gov>;", null));
                        email40.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 40 constituent"); // uses key
                        email40.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email40); //sends the whole email
                        startActivity(Intent.createChooser(email40, "Send email "));
                        break;
                    case 40:
                        Intent email41 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "District41@council.nyc.gov <District41@council.nyc.gov>;", null));
                        email41.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 41 constituent"); // uses key
                        email41.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email41); //sends the whole email
                        startActivity(Intent.createChooser(email41, "Send email "));
                        break;
                    case 41:
                        Intent email42 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Ibarron@council.nyc.gov <Ibarron@council.nyc.gov>;", null));
                        email42.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 42 constituent"); // uses key
                        email42.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email42); //sends the whole email
                        startActivity(Intent.createChooser(email42, "Send email "));
                        break;
                    case 42:
                        Intent email43 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "AskJB@council.nyc.gov <AskJB@council.nyc.gov>;", null));
                        email43.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 43 constituent"); // uses key
                        email43.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email43); //sends the whole email
                        startActivity(Intent.createChooser(email43, "Send email "));
                        break;
                    case 43:
                        Intent email44 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "AskKalman@council.nyc.gov <AskKalman@council.nyc.gov>;", null));
                        email44.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 44 constituent"); // uses key
                        email44.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email44); //sends the whole email
                        startActivity(Intent.createChooser(email44, "Send email "));
                        break;
                    case 44:
                        Intent email45 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Vacant", null));
                        email45.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 45 constituent"); // uses key
                        email45.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email45); //sends the whole email
                        startActivity(Intent.createChooser(email45, "Send email "));
                        break;
                    case 45:
                        Intent email46 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "AMaisel@council.nyc.gov <AMaisel@council.nyc.gov>;", null));
                        email46.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 46 constituent"); // uses key
                        email46.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email46); //sends the whole email
                        startActivity(Intent.createChooser(email46, "Send email "));
                        break;
                    case 46:
                        Intent email47 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "MTreyger@council.nyc.gov <MTreyger@council.nyc.gov>;", null));
                        email47.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 47 constituent"); // uses key
                        email47.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email47); //sends the whole email
                        startActivity(Intent.createChooser(email47, "Send email "));
                        break;
                    case 47:
                        Intent email48 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "cdeutsch@council.nyc.gov <cdeutsch@council.nyc.gov>;", null));
                        email48.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 48 constituent"); // uses key
                        email48.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email48); //sends the whole email
                        startActivity(Intent.createChooser(email48, "Send email "));
                        break;
                    case 48:
                        Intent email49 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "DROSE@Council.nyc.gov <DROSE@Council.nyc.gov>;", null));
                        email49.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 49 constituent"); // uses key
                        email49.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email49); //sends the whole email
                        startActivity(Intent.createChooser(email49, "Send email "));
                        break;
                    case 49:
                        Intent email50 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "SMatteo@council.nyc.gov <SMatteo@council.nyc.gov>;", null));
                        email50.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 50 constituent"); // uses key
                        email50.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email50); //sends the whole email
                        startActivity(Intent.createChooser(email50, "Send email "));
                        break;
                    case 50:
                        Intent email51 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "borelli@council.nyc.gov <borelli@council.nyc.gov>;", null));
                        email51.putExtra(Intent.EXTRA_SUBJECT, "Environmental concern from district 51 constituent"); // uses key
                        email51.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                        setResult(REQUEST_CODE, email51); //sends the whole email
                        startActivity(Intent.createChooser(email51, "Send email "));
                        break;





                    /* you can have any number of case statements */
                    default:

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                selectionScreen();
                return true;
            case R.id.camera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //(takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void selectionScreen() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }



/*        final String hasRep = (String) representative.getSelectedItem();


        final ForumConstructor emailToRep = new ForumConstructor(emailBody.getText().toString(), hasRep, null);
        mMessagesDataBaseReference.push().setValue(emailToRep);


        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", hasRep, null));
        email.putExtra(Intent.EXTRA_SUBJECT, "Environmental concerns from constituency"); // uses key
        email.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
        setResult(REQUEST_CODE, email); //sends the whole email
        startActivity(Intent.createChooser(email, "Send email "));
        // finish();//launches to activity result - essential!*/

}