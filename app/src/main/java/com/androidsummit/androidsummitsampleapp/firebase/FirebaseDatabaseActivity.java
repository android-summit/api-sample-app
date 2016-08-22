package com.androidsummit.androidsummitsampleapp.firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.androidsummit.androidsummitsampleapp.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This activity demonstrates an example of storing and retrieving data with the Firebase Realtime Database API. This example creates
 * customers with just simple first_name and last_name fields and stores them in the databse under a 'cutomers' reference.
 *
 * This example also listens for changes in the 'customers' database, and updates the list of customers when there is a change.
 *
 * Configuration & Setup: https://firebase.google.com/docs/database/android/start/
 */
public class FirebaseDatabaseActivity extends AppCompatActivity {

    private DatabaseReference customerDatabaseReference;

    private List<FirebaseCustomer> mCustomers;

    private EditText firstNameEditText;

    private EditText lastNameEditText;

    private Button saveCustomerButton;

    private ListView customersListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_database_activity);

        mCustomers = new ArrayList<>();

        firstNameEditText = (EditText) findViewById(R.id.input_first_name_edittext);
        lastNameEditText = (EditText) findViewById(R.id.input_last_name_edittext);
        saveCustomerButton = (Button) findViewById(R.id.firebase_save_customer_button);

        customersListView = (ListView) findViewById(R.id.firebase_customers_listview);
        customersListView.setAdapter(new FirebaseCustomerListAdapter(this, R.layout.nessie_customer, mCustomers));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to our database of customers
        customerDatabaseReference = database.getReference("customers");

        customerDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String id = dataSnapshot.getKey();
                String firstName = (String) dataSnapshot.child("firstName").getValue();
                String lastName = (String) dataSnapshot.child("lastName").getValue();
                mCustomers.add(new FirebaseCustomer(id, firstName, lastName));

                ((BaseAdapter) customersListView.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // do something on update
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // try deleting an entry in the Firebase Console, and watch the sample app update in real time!

                String key = dataSnapshot.getKey();
                Iterator<FirebaseCustomer> iter = mCustomers.iterator();

                while (iter.hasNext()) {
                    FirebaseCustomer customer = iter.next();

                    if (customer.getId().equals(key)) {
                        iter.remove();
                        ((BaseAdapter) customersListView.getAdapter()).notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // do something on move
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // do something on cancel
            }
        });

        saveCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomer();
            }
        });
    }

    /*
    Build a customer object and save it in the customers database
     */
    private void saveCustomer() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();

        FirebaseCustomer customer = new FirebaseCustomer(firstName, lastName);

        // Push a new object into the customers database and then set its value to the build customer object
        customerDatabaseReference.push().setValue(customer);

        firstNameEditText.setText("");
        lastNameEditText.setText("");
    }
}
