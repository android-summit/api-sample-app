package com.androidsummit.androidsummitsampleapp.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidsummit.androidsummitsampleapp.R;

/**
 * This is an example of using the Firebase Auth service for logging in.  This example uses the Google Auth provider.
 */
public class ExampleAuthFirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_auth_firebase_activity);
    }
}
