package utils.iocontrol;

import java.util.*;

/**
 * The StringAndMapConvertor class provides methods to convert a String
 * representation of a map to a Map object,
 * and vice versa.
 */
public class StringAndMapConvertor {

    /**
     * The character to be used to separate the key and value of a map entry.
     */
    public static final String DELIMITER_STRING = "\u001B\u001B\u001B";

    /**
     * The character to be used to separate features of an object.
     */
    public static final String SEPARATOR_STRING = "\u001A\u001A\u001A";

    /**
     * Converts a String representation of a map to a Map object.
     *
     * @param string The String representation of the map.
     * @return A Map object containing the key-value pairs from the String
     *         representation.
     * @throws IllegalArgumentException if the input string contains invalid
     *                                  key-value pairs.
     */
    public static Map<String, String> stringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = string.split(SEPARATOR_STRING);
        for (String pair : pairs) {
            String[] keyValue = pair.split(DELIMITER_STRING);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException(
                        "Invalid key-value pair: " + pair + " in string: " + Arrays.toString(keyValue));
            }
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    /**
     * Converts a Map object to a String representation of the map.
     *
     * @param map The Map object to convert.
     * @return A String representation of the map.
     */
    public static String mapToString(Map<String, String> map) {
        List<String> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            pairs.add(entry.getKey() + DELIMITER_STRING + entry.getValue());
        }
        return String.join(SEPARATOR_STRING, pairs);
    }
}
