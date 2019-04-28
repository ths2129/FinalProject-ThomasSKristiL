
package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.finalproject_thomasskristil.ForumActivity.ANONYMOUS;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUsername;
    public static final int RC_SIGN_IN = 1; // Request Code
    private ForumAdapter mForumAdapter;
    private ChildEventListener mChildEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, R.string.welcome, Toast.LENGTH_LONG).show();
        //Log.d(Tag, "onCreate start");
        mAuth = FirebaseAuth.getInstance();
        List<ForumConstructor> forumMessages = new ArrayList<>(); //contains

        mForumAdapter = new ForumAdapter(this, R.layout.item_message, forumMessages); //layout of item_message XML


        mAuthStateListener = new FirebaseAuth.AuthStateListener() { //state listener is created, but attached yet, that is onResume emthod
            //This is always pop up the logins
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //parameter will reveal if authenticated
                FirebaseUser user = firebaseAuth.getCurrentUser(); //is the user logged in? Conditionas will check

                if (user != null) { //user logged in?
                    //user is signedin and Toast helps user know
                    // Toast.makeText(MainActivity.this, "You're now signed in. Welcome to FriendlyChat.", Toast.LENGTH_SHORT).show();
                    onSignedIntitialized(user.getDisplayName());//pass in username into helper emthod
                } else { // signed out?
                    onSignedOutCleanup();
                    //user is signed out
                    // Choose authentication providers
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());
                    // new AuthUI.IdpConfig.FacebookBuilder().build();

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(true)// save credentials!!
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN); // Defined constant - Flag for return
                }
            }

            ;
        };

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void onSignedIntitialized(String username) {
        mUsername = username; //variable linked to the sendBUtton method onClick
        //sets username
        //attachDatabaseReadListener(); //called listeners
        //Only sending user name and messager list when logged in
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS; //sets back to original
        //mMessageAdapter.clear();//user not signed will be detached
        //unset username detack listener
    }

    public void Begin(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onPause() { // passed on onStateListener
        super.onPause();
        if (mChildEventListener != null) { //detached listener
            mAuth.removeAuthStateListener(mAuthStateListener); //on off states
        }
       // detachDatabaseRedListener();
        mForumAdapter.clear(); // activity is destoryed and app cleans up
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null)
            ; //if only it has been detached will the listener work
        mChildEventListener = new ChildEventListener() { //onbject reponds with new message
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String s) {
                ForumConstructor friendlyMessage = dataSnapshot.getValue(ForumConstructor.class);
                mForumAdapter.add(friendlyMessage);
                //datasnapshot will contain the message data
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            public void onCancelled(DatabaseError databaseError) {
            }

        };
    }
    @Override
    protected void onResume() { //onState listener
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener); //passed in listener

    }}