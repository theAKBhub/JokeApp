package com.example.android.libjokeviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        String joke;

        joke = "****  Displaying from JokeActivity   *****\n\n";

        if (getIntent().getExtras() != null) {
            joke += getIntent().getExtras().getString("joke");
        }

        TextView textviewJokeDisplay = findViewById(R.id.textview_joke_display);
        textviewJokeDisplay.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
