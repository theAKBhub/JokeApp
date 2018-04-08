package com.example.android.libjokeprovider;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * JokeRepository class populates a list of jokes and has a method to return the list to the android app
 */
public class JokeRepository {

    private static ArrayList<String> sJokeListDaily;
    private static ArrayList<String> sJokeListTechnology;
    private static ArrayList<String> sJokeListWork;
    private static ArrayList<String> sJokeListAnimal;
    private static ArrayList<String> sJokeListHealth;
    private static ArrayList<String> sJokeListSuccess;

    public static final String[] JOKE_CATEGORIES = new String[] {
            "daily", "technology", "work", "animal", "health", "success"
    };

    public JokeRepository() {
        sJokeListDaily = new ArrayList<>(Arrays.asList(JokeData.JOKES_DAILY));
        sJokeListTechnology = new ArrayList<>(Arrays.asList(JokeData.JOKES_TECHNOLOGY));
        sJokeListWork = new ArrayList<>(Arrays.asList(JokeData.JOKES_WORK));
        sJokeListAnimal = new ArrayList<>(Arrays.asList(JokeData.JOKES_ANIMAL));
        sJokeListHealth = new ArrayList<>(Arrays.asList(JokeData.JOKES_HEALTH));
        sJokeListSuccess = new ArrayList<>(Arrays.asList(JokeData.JOKES_SUCCESS));
    }

    public ArrayList<String> getJokeList(String jokeType) {
        if (jokeType.equals(JOKE_CATEGORIES[0])) {
            return sJokeListDaily;
        } else if (jokeType.equals(JOKE_CATEGORIES[1])) {
            return sJokeListTechnology;
        } else if (jokeType.equals(JOKE_CATEGORIES[2])) {
            return sJokeListWork;
        } else if (jokeType.equals(JOKE_CATEGORIES[3])) {
            return sJokeListAnimal;
        } else if (jokeType.equals(JOKE_CATEGORIES[4])) {
            return sJokeListHealth;
        } else if (jokeType.equals(JOKE_CATEGORIES[5])) {
            return sJokeListSuccess;
        } else {
            return sJokeListDaily;
        }
    }
}
