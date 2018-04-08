package com.udacity.gradle.builditbigger.backend;

import java.util.ArrayList;

/**
 * This is the object model for Joke data being sent through endpoints
 */

public class JokeBean {

    private ArrayList<String> mJokeList;

    /**
     * Getter method for Jokes List
     * @return ArrayList of jokes
     */
    public ArrayList<String> getList() {
        return mJokeList;
    }

    /**
     * Setter method for Jokes List
     * @param jokeList
     */
    public void setList(ArrayList<String> jokeList) {
        mJokeList = jokeList;
    }
}