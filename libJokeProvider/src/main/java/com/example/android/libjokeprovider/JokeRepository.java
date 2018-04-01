package com.example.android.libjokeprovider;

import java.util.ArrayList;
import java.util.Random;

/**
 * JokeRepository class populates a list of jokes and has a method to return the list to the android app
 */
public class JokeRepository {

    private static ArrayList<String> sJokeListDaily;
    private static ArrayList<String> sJokeListScience;

    public JokeRepository() {
        sJokeListDaily = new ArrayList<>();
        sJokeListScience = new ArrayList<>();

        sJokeListDaily = new ArrayList<>();
        sJokeListScience = new ArrayList<>();

        sJokeListDaily.add("My Dad told me to invest my money in bonds. So I bought 100 copies of Goldfinger.");
        sJokeListDaily.add("Money can't buy you happiness? Well, check this out, I bought myself a Happy Meal.");
        sJokeListDaily.add("What do Alexander the Great and Winnie the Pooh have in common? Same middle name.");
        sJokeListDaily.add("How do you make a fire with two sticks? Make sure one of them is a match!");
        sJokeListDaily.add("What starts with E, ends with E, and has only one letter in it? Envelope.");
        sJokeListDaily.add("What happens once in a minute and twice in a moment but never in a decade? The letter M.");
        sJokeListDaily.add("What is the color of the wind? Blew.");

        sJokeListScience.add("I needed a password eight characters long so I picked Snow White and the Seven Dwarves.");
        sJokeListScience.add("The first computer dates back to Adam and Eve. It was an Apple with limited memory, just one byte. And then everything crashed.");
        sJokeListScience.add("To the mathematicians who thought of the idea of zero, thanks for nothing!");
    }

    public String getDailyJoke() {
        return sJokeListDaily.get(new Random().nextInt(sJokeListDaily.size()-1));
    }

    public String getJokeFromRepo(String jokeType) {
        switch (jokeType) {
            case "daily":
                return sJokeListDaily.get(new Random().nextInt(sJokeListDaily.size()-1));
            case "technology":
                return sJokeListScience.get(new Random().nextInt(sJokeListScience.size()-1));
            default:
                return sJokeListDaily.get(new Random().nextInt(sJokeListDaily.size()-1));
        }
    }

}
