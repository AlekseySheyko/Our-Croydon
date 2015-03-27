package com.lbcinternal.sensemble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.lbcinternal.sensemble.R;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

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

        TextView bodyTextView = (TextView) findViewById(R.id.body);
        bodyTextView.setText(body);
    }

    public void viewComments(View view) {
        startActivity(new Intent(this, CommentsActivity.class));
    }
}
