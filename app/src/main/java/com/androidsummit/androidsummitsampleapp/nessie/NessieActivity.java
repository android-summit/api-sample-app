package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.androidsummit.androidsummitsampleapp.R;

/**
 * This activity demonstrates usage of the Nessie API - Capital One's hackathon API.
 *
 * Nessie Android SDK - https://github.com/nessieisreal/nessie-android-sdk Nessie API Documentation - api.reimaginebanking.com
 */
public class NessieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_activity);

        Button customersButton = (Button) findViewById(R.id.nessie_customers_button);
        customersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NessieGetCustomersActivity.class);
                startActivity(intent);
            }
        });


        Button depositButton = (Button) findViewById(R.id.nessie_deposit_button);
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NessieCreateDepositActivity.class);
                startActivity(intent);
            }
        });
    }
}
