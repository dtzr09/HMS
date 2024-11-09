package utils.iocontrol;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.utils.EmptyID;

/**
 * Interface for objects that can be mapped to/from a Map.
 */
public interface Mappable {

    /**
     * Converts the object to a map
     *
     * @return the map
     */
    default Map<String, String> convertToMap() {
        Map<String, String> map = new HashMap<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value instanceof Mappable) {
                    Map<String, String> nestedMap = ((Mappable) value).convertToMap();
                    for (Map.Entry<String, String> entry : nestedMap.entrySet()) {
                        try {
                            map.put(field.getName() + "_" + entry.getKey(), entry.getValue());
                        } catch (NullPointerException e) {
                            map.put(field.getName(), EmptyID.EMPTY_ID);
                        }
                    }
                } else {
                    try {
                        map.put(field.getName(), field.get(this).toString());
                    } catch (NullPointerException e) {
                        map.put(field.getName(), EmptyID.EMPTY_ID);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    default void convertToObject(Map<String, String> map) {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.getType().isEnum()) {
                    @SuppressWarnings("unchecked")
                    Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), map.get(field.getName()));
                    field.set(this, enumValue);
                } else if (field.getType().equals(Integer.TYPE) || field.getType().equals(Integer.class)) {
                    if (EmptyID.isEmptyID(map.get(field.getName()))) {
                        field.set(this, 0);
                    } else {
                        int intValue = Integer.parseInt(map.get(field.getName()));
                        field.set(this, intValue);
                    }
                } else if (field.getType().equals(List.class)) {
                    String listString = map.get(field.getName());
                    if (listString != null) {
                        // Create a new list to populate
                        List<Object> list = new ArrayList<>();

                        // Determine the type of elements in the list (if known)
                        ParameterizedType listType = (ParameterizedType) field.getGenericType();
                        Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];

                        // Split the string and parse elements based on the list type
                        String[] items = listString.split(",");
                        for (String item : items) {
                            if (listClass.equals(String.class)) {
                                list.add(item);
                            } else if (listClass.equals(Integer.class)) {
                                list.add(Integer.parseInt(item.trim()));
                            }
                        }

                        field.set(this, list);
                    } else {
                        field.set(this, new ArrayList<>());
                    }
                } else if (field.getType().equals(Date.class)) {
                    String dateString = map.get(field.getName());
                    if (dateString != null) {
                        Date dateValue = null;
                        try {
                            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                            dateValue = DATE_FORMAT.parse(dateString);
                        } catch (Exception e) {
                            dateValue = null;
                        }
                        field.set(this, dateValue);
                    } else {
                        field.set(this, null); // Set to null if the date is not available
                    }
                } else {
                    field.set(this, map.get(field.getName()));
                }
            } catch (IllegalAccessException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
