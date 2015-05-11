package com.lbcinternal.sensemble.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import org.json.JSONException;

public class FeedbackFragment extends Fragment {

    private EditText mMessageEditText;

    public FeedbackFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback,
                container, false);

        mMessageEditText = (EditText) rootView.findViewById(R.id.message);

        Button submitButton = (Button) rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendEmailTask().execute();

                Toast.makeText(getActivity(), "Thanks for your feedback!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return rootView;
    }

    private class SendEmailTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                SendGrid sendgrid = new SendGrid("OurCroydonAndroid", "TME1921hs");

                SendGrid.Email email = new SendGrid.Email();

                // Get values from edit text to compose email
                email.addTo(new String[] {"internal.communications@croydon.gov.uk", "harry@sensemble.com"});
                email.setFrom("harry@sensemble.com");
                email.setSubject("Customer feedback - Our Croydon");
                email.setText(mMessageEditText.getText().toString());

                // Send email, execute http request
                SendGrid.Response response = sendgrid.send(email);
                String msgResponse = response.getMessage();

                Log.d("SendAppExample", msgResponse);

            } catch (SendGridException | JSONException e) {
                Log.e("SendAppExample", e.toString());
            }

            return null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Feedback");
    }
}
