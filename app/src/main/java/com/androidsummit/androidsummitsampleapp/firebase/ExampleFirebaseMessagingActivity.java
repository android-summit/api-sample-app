package com.androidsummit.androidsummitsampleapp.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;

/**
 * An activity which gives instructions on how to send a notification to the app using Firebase.
 */
public class ExampleFirebaseMessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_example_messaging_activity);

        TextView instructionsTextView = (TextView) findViewById(R.id.firebase_messaging_instructions_textview);
        instructionsTextView.setText(Html.fromHtml(getString(R.string.messaging_setup_instructions)));
        instructionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
