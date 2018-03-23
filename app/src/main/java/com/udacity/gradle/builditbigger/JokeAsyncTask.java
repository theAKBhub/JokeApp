package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.support.v4.util.Pair;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi.Builder;
import java.io.IOException;

/**
 * AsyncTask class to make request to the Cloud Endpoints backend API and retrieve the jokes.
 * Purpose is to perform the background operations without interrupting main thread and publish
 * results on UI.
 */

public class JokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private static JokeApi sJokeApiService = null;

    private Context mContext;
    private TaskCompleteListener mTaskCompleteListener;


    public interface TaskCompleteListener {
        void onTaskComplete(String result);
    }

    public JokeAsyncTask(TaskCompleteListener listener) {
        mTaskCompleteListener = listener;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (sJokeApiService == null) {

            JokeApi.Builder builder = new Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            sJokeApiService = builder.build();
        }

        mContext = params[0].first;
        String jokeType = params[0].second;
        Log.d(TAG, "Joke type >>>> " + jokeType);

        try {
            return sJokeApiService.getJoke(jokeType).execute().getData();
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (joke != null) {
            Log.d(TAG, "Joke >>>> " + joke);
            mTaskCompleteListener.onTaskComplete(joke);
        } else {
            Log.d(TAG, "Joke >>>> Joke not received");
        }
    }

}