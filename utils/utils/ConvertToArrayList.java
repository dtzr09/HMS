package utils.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ConvertToArrayList {
    public static ArrayList<String> convertToArrayList(String input) {
        if (input == null || input.equals("[null]") || input.equals("null") || input.equals("[]")) {
            return new ArrayList<>();
        }

        String cleanedInput = input.replaceAll("[\\[\\]]", "");
        String[] items = cleanedInput.split(",\\s*");

        return new ArrayList<>(Arrays.asList(items));
    }
}
