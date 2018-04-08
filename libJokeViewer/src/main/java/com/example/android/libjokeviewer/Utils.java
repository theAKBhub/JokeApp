package com.example.android.libjokeviewer;

/**
 * Utility class contains common methods
 */
public class Utils {

    /**
     * This is a private constructor and only meant to hold static variables and methods,
     * which can be accessed directly from the class name Utils
     */
    private Utils() {
    }

    /**
     * Utility method to check if a string is empty or not
     * @param stringToCheck
     * @return TRUE (if empty string) / FALSE
     */
    public static boolean isEmptyString(String stringToCheck) {
        return (stringToCheck == null || stringToCheck.trim().length() == 0);
    }

    /**
     * Utility method to convert first letter of a string to Uppercase
     * @param word
     * @return word with first letter uppercase
     */
    public static String convertStringToFirstCapital (String word) {
        return word.toUpperCase().charAt(0) + word.substring(1).toLowerCase();
    }
}
