package com.lbcinternal.sensemble.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.lbcinternal.sensemble.R;

public class PostIdeaWelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_idea_welcome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_idea_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_next) {
            CheckBox showCheckBox = (CheckBox) findViewById(R.id.show_checkbox);

            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(this);
            sharedPrefs.edit().putBoolean("showInstructions", !showCheckBox.isChecked()).apply();

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
