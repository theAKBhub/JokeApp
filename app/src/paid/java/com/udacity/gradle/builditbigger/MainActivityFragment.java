package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.android.libjokeviewer.JokeActivity;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private Context mContext;
    private ProgressBar mProgressBar;


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


        mProgressBar = root.findViewById(R.id.progress_indicator);

        Button buttonTellJoke = root.findViewById(R.id.button_tell_joke);
        buttonTellJoke.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
            public void onTaskComplete(String jokeReceived) {
                if (jokeReceived != null) {
                    Intent intent = new Intent(mContext, JokeActivity.class);
                    intent.putExtra("joke", jokeReceived);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "No joke received", Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.GONE);
            }
        }).execute(new Pair<Context, String>(mContext, "daily"));
    }
}
