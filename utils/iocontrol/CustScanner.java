package utils.iocontrol;

import java.util.Scanner;

public class CustScanner {
    public static int getIntChoice() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid integer.");
            return getIntChoice();
        }
    }

    public static String getStrChoice() {
        try {
            return new Scanner(System.in).nextLine();
        } catch (Exception e) {
            System.out.println("Please enter a valid string.");
            return getStrChoice();
        }
    }

    public static String getPassword() {
        if (System.console() != null)
            return new String(System.console().readPassword());
        return new Scanner(System.in).nextLine();
    }
}
