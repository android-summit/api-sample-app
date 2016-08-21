package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;

import java.util.List;

/**
 * An adapter to display the results from the GET customers call in {@link NessieActivity}.
 */
public class NessieCustomerListAdapter extends ArrayAdapter<Customer> {

    private List<Customer> mCustomers;

    private int mResourceId;

    public NessieCustomerListAdapter(Context context, int resource, List<Customer> customers) {
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

        Customer customer = mCustomers.get(position);

        if (customer != null) {
            TextView firstNameTextView = (TextView) view.findViewById(R.id.first_name_textview);
            firstNameTextView.setText(customer.getFirstName());

            TextView lastNameTextView = (TextView) view.findViewById(R.id.last_name_textview);
            lastNameTextView.setText(customer.getLastName());
        }

        return view;
    }
}
