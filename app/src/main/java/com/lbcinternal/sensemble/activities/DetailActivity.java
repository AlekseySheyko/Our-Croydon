package com.lbcinternal.sensemble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.lbcinternal.sensemble.CroydonApp;
import com.lbcinternal.sensemble.CroydonApp.TrackerName;
import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.RestClient;
import com.lbcinternal.sensemble.rest.model.IdeaDetails;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);

        String section = sp.getString("section", "");
        if (section.equals("ideas")) {
            findViewById(R.id.feedback_container).setVisibility(View.VISIBLE);
        }

        String title = sp.getString("ideaTitle", "");
        String date = sp.getString("ideaDate", "");
        String body = sp.getString("ideaBody", "");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        TextView dateTextView = (TextView) findViewById(R.id.date);
        dateTextView.setText(date);


        if (!body.isEmpty()) {
            TextView bodyTextView = (TextView) findViewById(R.id.body);
            bodyTextView.setText(body);
        } else {
            String id = sp.getString("ideaId", "");
            ApiService service = new RestClient().getApiService();
            service.getIdeaDetails(id, new Callback<IdeaDetails>() {
                @Override public void success(IdeaDetails ideaDetails, Response response) {
                    TextView bodyTextView = (TextView) findViewById(R.id.body);
                    bodyTextView.setText(ideaDetails.getBody());

                    TextView scoreTextView = (TextView) findViewById(R.id.rating);
                    scoreTextView.setText(ideaDetails.getRating() + " / 5");
                }

                @Override public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }

        sendSessionInfo();
    }

    public void viewComments(View view) {
        startActivity(new Intent(this, CommentsActivity.class));
    }

    private void sendSessionInfo() {
        Tracker tracker = ((CroydonApp) getApplication()).getTracker(
                TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.viewPostScreen));
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }
}
