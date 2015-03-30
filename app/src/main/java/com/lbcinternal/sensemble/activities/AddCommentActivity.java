package com.lbcinternal.sensemble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.RestClient;

import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AddCommentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        final EditText editText = (EditText) findViewById(R.id.message);
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(
                            AddCommentActivity.this);
                    String ideaId = sp.getString("ideaId", "");
                    String message = editText.getText().toString();

                    if (message.isEmpty()) {
                        Toast.makeText(AddCommentActivity.this, "Comment cannot be empty",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    ApiService service = new RestClient().getApiService();
                    service.postComment(message, ideaId, new ResponseCallback() {
                        @Override public void success(Response response) {
                        }

                        @Override public void failure(RetrofitError error) {
                            error.printStackTrace();
                        }
                    });

                    startActivity(new Intent(AddCommentActivity.this,
                            CommentsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
