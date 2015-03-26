package com.lbcinternal.sensemble;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String body = getIntent().getStringExtra("body");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        TextView dateTextView = (TextView) findViewById(R.id.date);
        dateTextView.setText(date);

        TextView bodyTextView = (TextView) findViewById(R.id.body);
        bodyTextView.setText(body);
    }
}
