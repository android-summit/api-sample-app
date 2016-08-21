package com.androidsummit.androidsummitsampleapp.nessie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.androidsummit.androidsummitsampleapp.BuildConfig;
import com.androidsummit.androidsummitsampleapp.R;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.List;

/**
 * An activity demonstrating how to get a list of customers using the Nessie API.
 */
public class NessieGetCustomersActivity extends AppCompatActivity {

    private NessieClient mClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_get_customers_activity);

        String key = BuildConfig.NESSIE_API_KEY;
        mClient = NessieClient.getInstance(key);

        Button customersBtn = (Button) findViewById(R.id.nessie_get_customers_button);
        customersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the customers and display them here
                mClient.CUSTOMER.getCustomers(new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {
                        // successfully got customers, display them
                        List<Customer> customers = (List<Customer>) result;

                        // load results into list view
                        ListView customerListView = (ListView) findViewById(R.id.customers_result_listview);
                        NessieCustomerListAdapter nessieCustomerListAdapter =
                            new NessieCustomerListAdapter(getBaseContext(), R.layout.nessie_customer, customers);
                        customerListView.setAdapter(nessieCustomerListAdapter);
                    }

                    @Override
                    public void onFailure(NessieError error) {
                        // handle error here...
                        // probably should show an error dialog that says the call to the API failed
                    }
                });
            }
        });
    }
}
