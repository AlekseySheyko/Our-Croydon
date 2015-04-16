package com.lbcinternal.sensemble.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.adapters.CommentsAdapter;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.RestClient;
import com.lbcinternal.sensemble.rest.model.Comment;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CommentsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String ideaId = sp.getString("ideaId", "");

        ApiService service = new RestClient(this, "yyyy-MM-dd HH:mm").getApiService();
        service.listComments(ideaId, new Callback<List<Comment>>() {
            @Override public void success(List<Comment> comments, Response response) {

                CommentsAdapter adapter = new CommentsAdapter(CommentsActivity.this, comments);

                final ListView listView = (ListView) findViewById(R.id.comments_list);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new OnItemClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override public void onItemClick(AdapterView<?> parent, View view,
                                                      int position, long id) {
                        TextView commentLabel = (TextView) view.findViewById(R.id.message);
                        if (commentLabel.getMaxLines() == 2) {
                            commentLabel.setMaxLines(Integer.MAX_VALUE);
                        } else {
                            commentLabel.setMaxLines(2);
                        }
                    }
                });
            }

            @Override public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });


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
