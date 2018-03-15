package com.example.android.libjokeprovider;

/**
 * A {@link JokeModel} object that contains details related to a single Joke item
 */

public class JokeModel {

    private int mJokeId;
    private String mJokeCategory;
    private String mJoke;

    /**
     * Default Constructor
     * @param jokeId - unique identifier
     * @param jokeCategory - category of the joke, e.g. work, daily life, etc.
     * @param joke - one-liner joke
     */
    public JokeModel(int jokeId, String jokeCategory, String joke) {
        mJokeId = jokeId;
        mJokeCategory = jokeCategory;
        mJoke = joke;
    }

    /**
     * Getter and Setter methods for class JokeModel
     */
    public int getJokeId() {
        return mJokeId;
    }

    public void setJokeId(int jokeId) {
        mJokeId = jokeId;
    }

    public String getJokeCategory() {
        return mJokeCategory;
    }

    public void setJokeCategory(String jokeCategory) {
        mJokeCategory = jokeCategory;
    }

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String joke) {
        mJoke = joke;
    }
}
