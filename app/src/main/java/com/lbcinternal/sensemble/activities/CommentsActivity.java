package com.lbcinternal.sensemble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.lbcinternal.sensemble.R;
import com.melnykov.fab.FloatingActionButton;


public class CommentsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        // CommentsAdapter adapter = new CommentsAdapter(this, );

        ListView listView = (ListView) findViewById(R.id.comments_list);
        // listView.setAdapter(adapter);


        FloatingActionButton addCommentButton = (FloatingActionButton)
                findViewById(R.id.fab);
        addCommentButton.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(CommentsActivity.this,
                        AddCommentActivity.class));
            }
        });
    }
}
