package com.androidsummit.androidsummitsampleapp.firebase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;
import com.firebase.ui.auth.AuthUI;

/**
 * This is an example of using the Firebase Auth service for logging in.  This example uses the Google Auth provider.
 *
 * FirebaseUI Auth: https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md
 * Firebase Auth: https://firebase.google.com/docs/auth/
 * Currently Signed In User: https://firebase.google.com/docs/auth/android/manage-users#get_the_currently_signed-in_user
 */
public class ExampleAuthFirebaseActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 200;

    private TextView authStatusTextView;

    private TextView userNameTextView;

    private LinearLayout authContent;

    private ProgressBar authProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_auth_firebase_activity);

        Button signInBtn = (Button) findViewById(R.id.firebase_auth_sign_in_btn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        Button signOutBtn = (Button) findViewById(R.id.firebase_auth_sign_out_btn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

        userNameTextView = (TextView) findViewById(R.id.user_name_text_view);
        authContent = (LinearLayout) findViewById(R.id.auth_content);
        authProgressBar = (ProgressBar) findViewById(R.id.auth_progres_bar);

        authStatusTextView = (TextView) findViewById(R.id.auth_status_textview);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            setTextSignedIn(true);
        } else {
            setTextSignedIn(false);
        }
    }

    // signs the user in using Firebase Auth - Google Provider
    private void signInUser() {
        showProgressBar(true);
        Intent authIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setProviders(AuthUI.GOOGLE_PROVIDER)
            .build();

        startActivityForResult(authIntent, RC_SIGN_IN);
    }

    // signs the user out using Firebase Auth
    private void signOutUser() {
        showProgressBar(true);
        AuthUI.getInstance()
            .signOut(this)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                showProgressBar(false);
                setTextSignedIn(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            showProgressBar(false);
            if (resultCode == RESULT_OK) {
                setTextSignedIn(true);
            } else {
                // handle failed sign in here...
            }
        }
    }

    private void setTextSignedIn(boolean signedIn) {
        if (signedIn) {
            authStatusTextView.setText(R.string.signed_in_status);
            authStatusTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.signed_in));
            setUsernameText();
        } else {
            authStatusTextView.setText(R.string.signed_out_status);
            authStatusTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.signed_out));
            setUsernameText();
        }
    }

    private void setUsernameText() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userNameTextView.setText(user.getDisplayName());
        } else {
            userNameTextView.setText("");
        }
    }

    private void showProgressBar(boolean show) {
        if (show) {
            authContent.setVisibility(View.GONE);
            authProgressBar.setVisibility(View.VISIBLE);
        } else {
            authContent.setVisibility(View.VISIBLE);
            authProgressBar.setVisibility(View.GONE);
        }
    }
}
