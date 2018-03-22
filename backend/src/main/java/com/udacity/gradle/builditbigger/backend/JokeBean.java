package com.udacity.gradle.builditbigger.backend;

/**
 * This is the object model for Joke data being sent through endpoints
 */

public class JokeBean {

    private String mJokeData;

    public String getData() {
        return mJokeData;
    }

    public void setData(String data) {
        mJokeData = data;
    }
}