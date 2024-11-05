package display.user;

import java.util.Scanner;

import display.ClearDisplay;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;

public class ViewUserProfileDisplay {
    /**
     * Displays the user's profile.
     *
     * @param user the user whose profile is to be displayed.
     */
    public static void viewUserProfile(User user, UserType userType) {
        System.out.println("Welcome to View " + userType + " Profile");
        System.out.println("┌--------------------------------------------------------------------┐");
        System.out.printf("| %-15s | %-30s | %-15s |\n", "Name", "Email", userType + " ID");
        System.out.println("|-----------------|--------------------------------|-----------------|");
        System.out.printf("| %-15s | %-30s | %-15s |\n", user.getName(), user.getEmail(), user.getModelID());
        System.out.println("└--------------------------------------------------------------------┘");
    }

    /**
     * Displays the user's profile.
     *
     * @param user the user whose profile is to be displayed.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    public static void viewUserProfilePage(User user, UserType userType) throws PageBackException {
        ClearDisplay.ClearConsole();
        viewUserProfile(user, userType);
        System.out.println("Press enter to go back.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        throw new PageBackException();
    }
}
