package com.lbcinternal.sensemble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        String section = getIntent().getStringExtra("section");
        if (section.equals("ideas")) {
            findViewById(R.id.feedback_container).setVisibility(View.VISIBLE);
        }

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

    public void viewComments(View view) {
        startActivity(new Intent(this, CommentsActivity.class));
    }
}
