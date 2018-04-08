package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.android.libjokeviewer.JokeActivity;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;
import java.util.ArrayList;


/**
 * Fragment for paid flavour
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private Context mContext;
    private ProgressBar mProgressBar;
    private int mGridViewPosition;

    public static final String[] JOKE_CATEGORIES_PAID = new String[] {
            "daily", "technology", "work", "animal", "health", "success"
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // get reference to progress bar
        mProgressBar = root.findViewById(R.id.progress_indicator);

        // set up GridView to display joke categories
        GridView gridView = root.findViewById(R.id.gridview_joke_categories);
        gridView.setAdapter(new CategoryAdapter(mContext, JOKE_CATEGORIES_PAID));

        // set ItemClickListener on GridView items
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGridViewPosition = position;
                tellJoke();
            }
        });

        return root;
    }

    @SuppressWarnings("unchecked")
    public void tellJoke() {
        mProgressBar.setVisibility(View.VISIBLE);

        new JokeAsyncTask(new TaskCompleteListener() {
            @Override
            public void onTaskComplete(ArrayList<String> jokeListReceived) {
                if (jokeListReceived != null) {
                    Intent intent = new Intent(mContext, JokeActivity.class);
                    intent.putExtra(JokeActivity.INTENT_KEY_JOKE, jokeListReceived);
                    intent.putExtra(JokeActivity.INTENT_KEY_JOKE_CATEGORY, JOKE_CATEGORIES_PAID[mGridViewPosition]);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, R.string.error_fetching_joke, Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.GONE);
            }
        }).execute(new Pair<Context, String>(mContext, JOKE_CATEGORIES_PAID[mGridViewPosition]));
    }
}
