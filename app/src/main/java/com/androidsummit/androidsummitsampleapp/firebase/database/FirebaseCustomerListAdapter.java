package com.androidsummit.androidsummitsampleapp.firebase.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;

import java.util.List;

/**
 * An adapter to display the list of FirebaseCustomers retrieved from the Firebase Database in {@link FirebaseDatabaseActivity}.
 */
public class FirebaseCustomerListAdapter extends ArrayAdapter<FirebaseCustomer> {

    private List<FirebaseCustomer> mCustomers;

    private int mResourceId;

    public FirebaseCustomerListAdapter(Context context, int resource, List<FirebaseCustomer> customers) {
        super(context, resource, customers);
        mCustomers = customers;
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            view = inflator.inflate(mResourceId, null);
        }

        FirebaseCustomer customer = mCustomers.get(position);

        if (customer != null) {
            TextView firstNameTextView = (TextView) view.findViewById(R.id.first_name_textview);
            firstNameTextView.setText(customer.getFirstName());

            TextView lastNameTextView = (TextView) view.findViewById(R.id.last_name_textview);
            lastNameTextView.setText(customer.getLastName());
        }

        return view;
    }
}
