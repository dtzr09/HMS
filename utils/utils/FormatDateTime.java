package utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateTime {

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
