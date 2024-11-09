package utils.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ConvertToArrayList {
    public static ArrayList<String> convertToArrayList(String input) {
        String cleanedInput = input.replaceAll("[\\[\\]]", "");
        String[] items = cleanedInput.split(",\\s*");
        return new ArrayList<>(Arrays.asList(items));
    }
}
