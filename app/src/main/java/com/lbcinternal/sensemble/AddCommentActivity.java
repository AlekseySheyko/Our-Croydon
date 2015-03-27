package com.lbcinternal.sensemble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class AddCommentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        EditText editText = (EditText) findViewById(R.id.field);
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    startActivity(new Intent(AddCommentActivity.this,
                            CommentsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
