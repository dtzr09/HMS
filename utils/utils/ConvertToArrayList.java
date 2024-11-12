package utils.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * * Utility class for converting a formatted string to an ArrayList of strings.
 */
public class ConvertToArrayList {
    /**
     * Converts a formatted string to an ArrayList of strings.
     *
     * @param input the input string containing elements separated by commas
     * @return an ArrayList containing the individual elements from the input
     *         string,
     *         or an empty ArrayList if the input is null or represents an empty
     *         list
     */
    public static ArrayList<String> convertToArrayList(String input) {
        if (input == null || input.equals("[null]") || input.equals("null") || input.equals("[]")) {
            return new ArrayList<>();
        }

        String cleanedInput = input.replaceAll("[\\[\\]]", "");
        String[] items = cleanedInput.split(",\\s*");

        return new ArrayList<>(Arrays.asList(items));
    }
}
