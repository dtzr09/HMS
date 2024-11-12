package utils.iocontrol;

import java.util.Scanner;

/**
 * * Utility class for handling user input in a console-based application.
 */
public class CustScanner {
    /**
     * Prompts the user to enter an integer. If invalid input is provided,
     * it recursively prompts the user until a valid integer is entered.
     *
     * @return the integer entered by the user
     */
    public static int getIntChoice() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid integer.");
            return getIntChoice();
        }
    }

    /**
     * Prompts the user to enter a string. If invalid input is provided,
     * it recursively prompts the user until a valid string is entered.
     *
     * @return the string entered by the user
     */
    public static String getStrChoice() {
        try {
            return new Scanner(System.in).nextLine();
        } catch (Exception e) {
            System.out.println("Please enter a valid string.");
            return getStrChoice();
        }
    }

    /**
     * Prompts the user to enter a password securely. If a console is available,
     * it masks the input; otherwise, it falls back to standard input.
     *
     * @return the password entered by the user
     */
    public static String getPassword() {
        if (System.console() != null)
            return new String(System.console().readPassword());
        return new Scanner(System.in).nextLine();
    }
}
