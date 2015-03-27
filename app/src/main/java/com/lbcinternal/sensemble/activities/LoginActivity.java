package com.lbcinternal.sensemble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.lbcinternal.sensemble.R;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(getString(
                R.string.title_activity_login));
    }

    public void signIn(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
