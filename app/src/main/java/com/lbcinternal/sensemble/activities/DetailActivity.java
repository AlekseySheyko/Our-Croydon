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

        String title = sp.getString("title", "");
        String date = sp.getString("date", "");
        String body = sp.getString("body", "");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        TextView dateTextView = (TextView) findViewById(R.id.date);
        dateTextView.setText(date);

        if (!body.isEmpty()) {
            TextView bodyTextView = (TextView) findViewById(R.id.body);
            bodyTextView.setText(body);
        } else {
            ApiService service = new RestClient().getApiService();
            // TODO: Get idea details
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
