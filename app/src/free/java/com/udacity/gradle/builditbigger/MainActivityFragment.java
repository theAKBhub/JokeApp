package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.android.libjokeviewer.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;
import java.util.ArrayList;


/**
 * Fragment for free flavour
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private Context mContext;
    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;
    private int mGridViewPosition;

    public static final String[] JOKE_CATEGORIES_FREE = new String[] {
            "daily", "technology", "work", "animal"
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

        // display banner ad
        displayBannerAd(root);

        // display interstitial ad
        displayInterstitialAd();

        // get reference to progress bar
        mProgressBar = root.findViewById(R.id.progress_indicator);

        // set up GridView to display joke categories
        GridView gridView = root.findViewById(R.id.gridview_joke_categories);
        gridView.setAdapter(new CategoryAdapter(mContext, JOKE_CATEGORIES_FREE));

        // set ItemClickListener on GridView items
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGridViewPosition = position;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.e(TAG, getString(R.string.error_interstitial_ad));
                }
            }
        });

        // set listener on Interstitial ad
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Code to be executed when when the interstitial ad is closed by user using the Close button
                requestInterstitialAd();
                tellJoke();
            }
        });

        return root;
    }

    /**
     * Method to create a banner ad request
     */
    public void displayBannerAd(View view) {
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    /**
     * Method to create an Interstitial ad just after a joke category is selected
     * and just before the joke is displayed
     */
    public void displayInterstitialAd() {
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
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
                    intent.putExtra(JokeActivity.INTENT_KEY_JOKE_CATEGORY, JOKE_CATEGORIES_FREE[mGridViewPosition]);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, R.string.error_fetching_joke, Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.GONE);
            }
        }).execute(new Pair<Context, String>(mContext, JOKE_CATEGORIES_FREE[mGridViewPosition]));
    }

    /**
     * Method to request new Interstitial Ad
     */
    private void requestInterstitialAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
