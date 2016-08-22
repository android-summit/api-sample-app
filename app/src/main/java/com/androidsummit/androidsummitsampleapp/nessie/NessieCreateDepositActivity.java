package com.androidsummit.androidsummitsampleapp.nessie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.BuildConfig;
import com.androidsummit.androidsummitsampleapp.R;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionMedium;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.models.Deposit;
import com.reimaginebanking.api.nessieandroidsdk.models.PostResponse;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An activity demonstrating how to create a deposit using the Nessie API.
 */
public class NessieCreateDepositActivity extends AppCompatActivity {

    private NessieClient mClient;

    private EditText amountEditText;

    private EditText descriptionEditText;

    private Spinner mediumSpinner;

    private TextView resultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_create_deposit_activity);

        mClient = NessieClient.getInstance(BuildConfig.NESSIE_API_KEY);

        resultTextView = (TextView) findViewById(R.id.deposit_result_textview);

        amountEditText = (EditText) findViewById(R.id.input_amount_edittext);
        descriptionEditText = (EditText) findViewById(R.id.input_description_edittext);
        mediumSpinner = (Spinner) findViewById(R.id.input_medium_spinner);

        Button depositButton = (Button) findViewById(R.id.nessie_create_deposit_button);
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                There is no validation here, whatever is inputted in the EditText is used.
                Make sure the amount is an integer and you have made a selection on the "medium" spinner.
                 */

                // first need to get list of accounts so you have an account id to create a deposit for
                mClient.ACCOUNT.getAccounts(new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {
                        List<Account> accounts = (List<Account>) result;
                        if (accounts.size() > 0) {
                            createDeposit(accounts.get(0).getId());
                        }
                    }

                    @Override
                    public void onFailure(NessieError error) {
                        // handle failure here...
                        // should probably show an error message stating why the accounts call failed
                    }
                });



            }
        });
    }

    /*
    The POST request which calls the API to create the deposit
     */
    private void createDeposit(String accountId) {
        Deposit deposit = buildDeposit();

        mClient.DEPOSIT.createDeposit(accountId, deposit, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                PostResponse<Deposit> response = (PostResponse<Deposit>) result;
                resultTextView.setText(response.getMessage());
            }

            @Override
            public void onFailure(NessieError error) {
                // try changing the 'medium' in the created deposit to null and see what happens!
                resultTextView.setText(error.getMessage());
            }
        });
    }

    /*
    Helper method to build and return a deposit object (which will be sent to the API for creation)
     */
    private Deposit buildDeposit() {
        Integer amount;
        try {
            amount = Integer.valueOf(amountEditText.getText().toString());
        } catch (NumberFormatException e) {
            amount = null;
        }

        String description = descriptionEditText.getText().toString();

        TransactionMedium medium;
        try {
            medium = TransactionMedium.valueOf(mediumSpinner.getSelectedItem().toString());
        } catch (IllegalArgumentException e) {
            medium = TransactionMedium.BALANCE;
        }


        // date must be in this String format -> 2016-08-08
        Date currentDate = new Date();
        SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = fmtOut.format(currentDate);

        // build deposit object to create
        Deposit deposit = new Deposit.Builder()
            .amount(amount)
            .description(description)
            .medium(medium)
            .transactionDate(stringDate)
            .build();

        return deposit;
    }
}