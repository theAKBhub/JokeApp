package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.android.libjokeviewer.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private Context mContext;
    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;


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

        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



        mProgressBar = root.findViewById(R.id.progress_indicator);

        Button buttonTellJoke = root.findViewById(R.id.button_tell_joke);
        buttonTellJoke.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.e(TAG, "There was an error with loading Interstitial Ad.");
                }
                tellJoke();
            }
        });


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Code to be executed when when the interstitial ad is closed by user using the Close button
                        //tellJoke();
                requestInterstitialAd();
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

    private void requestInterstitialAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
