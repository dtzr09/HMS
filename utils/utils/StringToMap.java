package utils.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToMap {
    public static Map<String, List<String>> ToMap(String input) {
        Map<String, List<String>> map = new HashMap<>();

        input = input.substring(1, input.length() - 1);

        String[] pairs = input.split(", (?=\\d+=)");

        Pattern pattern = Pattern.compile("(\\d+)=\\[(.*?)]");

        for (String pair : pairs) {
            Matcher matcher = pattern.matcher(pair);

            if (matcher.matches()) {
                String key = matcher.group(1);
                String[] values = matcher.group(2).split(", ");
                List<String> valueList = Arrays.asList(values);

                map.put(key, valueList);
            }
        }

        return map;
    }
}
