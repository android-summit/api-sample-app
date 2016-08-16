package com.androidsummit.androidsummitsampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

/**
 * This activity demonstrates usage of the Nessie API - Capital One's hackathon API.
 *
 * Nessie Android SDK - https://github.com/nessieisreal/nessie-android-sdk
 * Nessie API Documentation - api.reimaginebanking.com
 */
public class NessieActivity extends AppCompatActivity {

    NessieClient mClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_activity);

        String key = BuildConfig.NESSIE_API_KEY;
        mClient = NessieClient.getInstance(key);
    }
}
