package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.android.libjokeprovider.JokeModel;
import com.example.android.libjokeviewer.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeAsyncTask.TaskCompleteListener {

    private Context mContext;
    private ProgressBar mProgressBar;
    private JokeModel mJokeModel;
    private String mJoke;

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

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

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


    public void tellJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        new JokeAsyncTask(this).execute(mContext);

        if (mJoke != null && mJoke.length() > 0) {

            Intent intent = new Intent(mContext, JokeActivity.class);
//        JokeRepository jokeRepository = new JokeRepository();
//        JokeModel jokeModel = jokeRepository.getJokesList().get(1);
//        String joke = jokeModel.getJoke();
//        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
//

            intent.putExtra("joke", mJoke);
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "No joke received", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaskComplete(String joke) {
        //JokeModel jokeModel = jokeRepository.getJokesList().get(0);
        //mJoke = jokeModel.getJoke();
        mJoke = joke;
        mProgressBar.setVisibility(View.GONE);
    }
}
