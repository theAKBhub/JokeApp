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
import java.util.ArrayList;

/**
 * AsyncTask class to make request to the Cloud Endpoints backend API and retrieve the jokes.
 * Purpose is to perform the background operations without interrupting main thread and publish
 * results on UI.
 */

public class JokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, ArrayList<String>> {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private static final String LOCALHOST_IP_ADDRESS = "http://10.0.2.2:8080/_ah/api/";
    private static JokeApi sJokeApiService = null;
    private TaskCompleteListener mTaskCompleteListener;
    private Context mContext;


    public interface TaskCompleteListener {

        void onTaskComplete(ArrayList<String> result);
    }

    public JokeAsyncTask(TaskCompleteListener listener) {
        mTaskCompleteListener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(Pair<Context, String>... params) {
        String jokeType;

        if (sJokeApiService == null) {
            JokeApi.Builder builder = new Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl(LOCALHOST_IP_ADDRESS)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            sJokeApiService = builder.build();
        }

        // get the parameters
        mContext = params[0].first;
        jokeType = params[0].second;

        // fetch list of jokes from backend using API
        try {
            return (new ArrayList<>(sJokeApiService.getJoke(jokeType).execute().getList()));
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> jokeList) {
        if (jokeList != null) {
            mTaskCompleteListener.onTaskComplete(jokeList);
        } else {
            Log.d(TAG, mContext.getString(R.string.error_fetching_joke));
        }
    }
}