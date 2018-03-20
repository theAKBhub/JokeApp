package com.example.android.libjokeviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke;

        joke = "****  Displaying from JokeActivity   *****\n\n";

        if (getIntent().getExtras() != null) {
            joke += getIntent().getExtras().getString("joke");
        }

        TextView textviewJokeDisplay = findViewById(R.id.textview_joke_display);
        textviewJokeDisplay.setText(joke);
    }
}
