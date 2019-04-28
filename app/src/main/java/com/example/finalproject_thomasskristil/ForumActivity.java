package com.example.finalproject_thomasskristil;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ForumActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 130; //limit
    private static final int RC_PHOTO_PICKER = 2;
    private static final String FRIENDLY_MSG_LENGTH_KEY = "friendly_msg_length"; //connects to reconfig
    public static final int RC_SIGN_IN = 1; // Request Code
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private ListView mMessageListView; //objects
    private ForumAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener; //executes when state changes - sign-in and sign-out code (Resume + Pause)
    private FirebaseDatabase mFireBaseDatabase; //entry point for app and database
    private FirebaseFunctions mFunctions;
    private DatabaseReference mMessagesDataBaseReference; //class that references the messages portion
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mFireBaseStorage; // needs getinstance
    private StorageReference mChatPhotoStorageReference;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private FirebaseRemoteConfigSettings mFirebaseSettings;
    private HashMap<String, Object> configDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mUsername = ANONYMOUS;
        mAuth = FirebaseAuth.getInstance();
        mFireBaseStorage = FirebaseStorage.getInstance();
        mFireBaseDatabase = FirebaseDatabase.getInstance();// access point - instantianted
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mMessagesDataBaseReference = mFireBaseDatabase.getReference().child("messages"); //initialized to this location
        mChatPhotoStorageReference = mFireBaseStorage.getReference().child("chat_photos");
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mFunctions = FirebaseFunctions.getInstance();

        addMessage("texts");
        fetchConfig();

        final ForumConstructor friendlyMessage = new ForumConstructor(mMessageEditText.getText().toString(), mUsername, null);
// reference to root and messages portion of database

        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        List<ForumConstructor> Forums = new ArrayList<>(); //contains
        mMessageAdapter = new ForumAdapter(this, R.layout.item_message, Forums); //layout of item_message XML
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });
        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() { //cannot send an empty message!
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) { //cannot send with zero int length count
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumConstructor friendlyMessage = new ForumConstructor(mMessageEditText.getText().toString(), mUsername, null);
                mMessagesDataBaseReference.push().setValue(friendlyMessage); //text sent to reference database

                // Clear input box
                mMessageEditText.setText(""); //text from parameters
            }
        });

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

        /// public FirebaseRemoteConfigSettings getConfigSettings() {
        // return configSettings;
        configDefault = new HashMap<>();
        configDefault.put(FRIENDLY_MSG_LENGTH_KEY, DEFAULT_MSG_LENGTH_LIMIT);
        mFirebaseRemoteConfig.setDefaults(configDefault); //set up remote config
        mFirebaseRemoteConfig.fetch(0);//fetchConfig();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder() //enable devloper mode with remote config
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        //return configSettings;
        //define parameters
    }

    // Fetch the config to determine the allowed length of messages.
    public void fetchConfig() {
        long cacheExpiration; // 1 hour in seconds
        // If developer mode is enabled reduce cacheExpiration to 0 so that
        //each fetch goes to the server. This should not be used in release builds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings()
                .isDeveloperModeEnabled()) { //latest values fro, firebase
            cacheExpiration = 0;

            // this helps with debugging?

            mFirebaseRemoteConfig.fetch(cacheExpiration) //return values
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {  //friendly message length below
                            // Make the fetched config available
                            // via FirebaseRemoteConfig get<type> calls, e.g., getLong, getString.
                            mFirebaseRemoteConfig.fetch(); //as deprecated


                            // Update the EditText length limit with
                            // the newly retrieved values from Remote Config.
                            applyRetrievedLengthLimit();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // An error occurred when fetching the config.

                            // Update the EditText length limit with
                            // the newly retrieved values from Remote Config.
                            applyRetrievedLengthLimit(); //Offline
                        }
                    });
        }
    }

    /**
     * Apply retrieved length limit to edit text field. This result may be fresh from the server or it may be from
     * cached values.
     */

    private void applyRetrievedLengthLimit() { //this updates text length
        Long friendly_msg_length = mFirebaseRemoteConfig.getLong(FRIENDLY_MSG_LENGTH_KEY);
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(friendly_msg_length.intValue())});
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

        } else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef = mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri);
            mChatPhotoStorageReference.putFile(selectedImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return mChatPhotoStorageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        ForumConstructor friendlyMessage = new ForumConstructor(null, mUsername, downloadUri.toString());
                        mMessagesDataBaseReference.push().setValue(friendlyMessage);
                    }
                }
            });
        }
    }

    private Task<String> addMessage(String text) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("text", text);
        data.put("push", true);

        return mFunctions
                .getHttpsCallable("addMessage")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });

    }

    private void onSignedIntitialized(String username) {
        mUsername = username; //variable linked to the sendBUtton method onClick
        //sets username
        attachDatabaseReadListener(); //called listeners
        //Only sending user name and messager list when logged in
    }


    private void attachDatabaseReadListener() {
        if (mChildEventListener == null)
            ; //if only it has been detached will the listener work
        mChildEventListener = new ChildEventListener() { //onbject reponds with new message
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String s) {
                ForumConstructor friendlyMessage = dataSnapshot.getValue(ForumConstructor.class);
                mMessageAdapter.add(friendlyMessage);
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
        mMessagesDataBaseReference.addChildEventListener(mChildEventListener); // this will trigger from the listeners
    }


    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS; //sets back to original
        mMessageAdapter.clear();//user not signed will be detached
        //unset username detack listener
    }

    private void detachDatabaseRedListener() {
        if (mChildEventListener != null) {
            mMessagesDataBaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    protected void onPause() { // passed on onStateListener
        super.onPause();
        if (mChildEventListener != null) { //detached listener
            mAuth.removeAuthStateListener(mAuthStateListener); //on off states
        }
        detachDatabaseRedListener();
        mMessageAdapter.clear(); // activity is destoryed and app cleans up


    }
    @Override
    protected void onResume() { //onState listener
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener); //passed in listener

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mMessagesDataBaseReference.cleanup();

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

}


