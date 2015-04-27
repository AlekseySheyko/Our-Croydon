package com.lbcinternal.sensemble.fragments;


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
                try {
                    SendGrid sendgrid = new SendGrid("api_user", "api_key");

                    SendGrid.Email email = new SendGrid.Email();

                    email.addTo("test@sendgrid.com");
                    email.setFrom("you@youremail.com");
                    email.setSubject("Sending with SendGrid is Fun");
                    email.setHtml("and easy to do anywhere, even with Java");

                    SendGrid.Response response = sendgrid.send(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getActivity(), "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void sendEmail() {
        try {
            String message = mMessageEditText.getText().toString();

            SendGrid sendgrid = new SendGrid("OurCroydonAndroid", "TME1921hs");

            SendGrid.Email email = new SendGrid.Email();

            email.addTo("our.croydon.test@gmail.com");
            email.setFrom("our.croydon.test@gmail.com");
            email.setSubject("Customer feedback - Our Croydon");
            email.setHtml(message);

            sendgrid.send(email);
        } catch (Exception e) {
            Log.e("FeedbackFragment", "Failed to send email. Cause: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Feedback");
    }
}
