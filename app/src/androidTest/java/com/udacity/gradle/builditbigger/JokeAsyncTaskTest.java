package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.util.Pair;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by aditibhattacharya on 22/03/2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest extends ApplicationTestCase<Application> implements TaskCompleteListener {


    private CountDownLatch mSignal;
    private String mJokeFetched;
    private static final String JOKE_TYPE = "science";
    private JokeAsyncTask mJokeAsyncTask;


    public JokeAsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // signal to indicate when task is completed
        mSignal = new CountDownLatch(1);
        mJokeAsyncTask = new JokeAsyncTask(this);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testJokeIsFetced() throws Throwable {
        mJokeAsyncTask.execute(new Pair<Context, String>(InstrumentationRegistry.getTargetContext(), JOKE_TYPE));
        mSignal.await(30, TimeUnit.SECONDS);
        assertTrue("Joke fetched is NULL", mJokeFetched != null);

        /*try {

            //new JokeAsyncTask(this).execute(Pair.create(InstrumentationRegistry.getTargetContext(), jokeType));
            mSignal.await(30, TimeUnit.SECONDS);
            //assertTrue("Joke fetched is EMPTY", !mJokeFetched.isEmpty());
            assertTrue("Joke fetched is NULL", mJokeFetched != null);
        } catch (Exception e) {
            fail();
        }*/
    }

    @Override
    public void onTaskComplete(String result) {
        mJokeFetched = result;
        mSignal.countDown();
    }
}