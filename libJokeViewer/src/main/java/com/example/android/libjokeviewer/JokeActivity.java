package com.example.android.libjokeviewer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_KEY_JOKE = "joke_list";
    public static final String INTENT_KEY_JOKE_CATEGORY = "joke_category";
    public static final String TYPEFACE = "fonts/roboto_condensed_l.ttf";
    public static final String TYPEFACE_NOTO = "fonts/notosans_r.ttf";

    private String mJoke;
    private String mJokeCategory;
    private ArrayList<String> mJokeList;
    Typeface mTypefaceJoke;
    Typeface mTypefaceJokeCategory;

    private TextView mTextViewJokeCategory;
    private TextView mTextViewJokeDisplay;
    private Button mButtonJokeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_joke_viewer_activity);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // initialize variables
        mJokeList = new ArrayList<>();
        mTypefaceJoke = Typeface.createFromAsset(getAssets(), TYPEFACE);
        mTypefaceJokeCategory = Typeface.createFromAsset(getAssets(), TYPEFACE_NOTO);

        // initialize views
        initializeViews();

        // receive joke list via Intent sent from main app
        if (getIntent().getExtras() != null) {
            mJokeList = getIntent().getStringArrayListExtra(INTENT_KEY_JOKE);
            mJokeCategory = getIntent().getExtras().getString(INTENT_KEY_JOKE_CATEGORY);
        }

        // display joke category
        displayJokeCategory();

        // if joke list is not received successfully display a Toast with error message, else display a joke
        if (mJokeList == null) {
            Toast.makeText(this, R.string.error_display_joke, Toast.LENGTH_SHORT).show();
            return;
        } else {
            displayRandomJoke();
        }

        // display a random joke when refresh button is clicked
        mButtonJokeRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRandomJoke();
            }
        });
    }

    /**
     * Method to initialize all View elements
     */
    public void initializeViews() {
        mTextViewJokeCategory = findViewById(R.id.textview_joke_category);
        mTextViewJokeDisplay = findViewById(R.id.textview_joke_display);
        mButtonJokeRefresh = findViewById(R.id.button_refresh_joke);

        mTextViewJokeCategory.setTypeface(mTypefaceJokeCategory);
        mTextViewJokeDisplay.setTypeface(mTypefaceJoke);
    }

    /**
     * Method to display Joke Category if it's not null
     */
    public void displayJokeCategory() {
        if (!Utils.isEmptyString(mJokeCategory)) {
            mTextViewJokeCategory.setText(String.format(getString(R.string.label_joke_category),
                    Utils.convertStringToFirstCapital(mJokeCategory)));
        }
    }

    /**
     * Method to display a random joke
     */
    public void displayRandomJoke() {
        mJoke = mJokeList.get(new Random().nextInt(mJokeList.size()-1));
        mTextViewJokeDisplay.setText(mJoke);
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
