package display.user;

import java.util.HashMap;
import java.util.Map;

import controller.user.UserManager;
import display.session.ClearDisplay;
import display.session.EnterToGoBackDisplay;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;
import utils.utils.FormatDateTime;

/**
 * The {@code UserProfileDisplay} class provides functionalities for displaying
 * and updating a user's profile in the Hospital Management System. This class
 * allows users to view their profile details and update specific fields as
 * needed.
 */
public class UserProfileDisplay {

        /**
         * Displays the main profile fields for a user, such as ID, name, email, gender,
         * age, date of birth, phone number, and date of registration.
         * 
         * @param user the user whose profile fields are to be displayed.
         */
        public static void profileFields(User user) {
                System.out.printf("User ID: %s\n", user.getModelID());
                System.out.printf("Name: %s\n", user.getName());
                System.out.printf("Email: %s\n", user.getEmail());
                System.out.printf("Gender: %s\n", user.getGender().toString());
                System.out.printf("Age: %d\n", user.getAge());
                System.out.printf("Date of Birth: %s\n", user.getDateOfBirth());
                System.out.printf("Phone Number: %s\n", user.getPhoneNumber());
                System.out.printf("Date Of Registration: %s\n",
                                FormatDateTime.formatDateTimeToString(user.getDateOfRegistration()));
        }

        /**
         * Displays a brief view of the user's profile, welcoming the user and showing
         * their profile information.
         *
         * @param user     the user whose profile is to be displayed.
         * @param userType the type of user (e.g., Doctor, Patient) whose profile is
         *                 being displayed.
         */
        public static void viewUserProfile(User user, UserType userType) {
                System.out.println("Welcome to your profile, " + user.getName() + " !");
                System.out.println("-------------------------------------------------");
                profileFields(user);
        }

        /**
         * Displays the user's profile page with an option to go back to the previous
         * page.
         * 
         * @param user     the user whose profile page is to be displayed.
         * @param userType the type of user (e.g., Doctor, Patient) whose profile is
         *                 being displayed.
         * @throws PageBackException if the user chooses to go back to the previous
         *                           page.
         */
        public static void viewUserProfilePage(User user, UserType userType) throws PageBackException {
                ClearDisplay.ClearConsole();
                viewUserProfile(user, userType);
                System.out.println();
                EnterToGoBackDisplay.display();
        }

        /**
         * Displays the menu for updating the user's profile information. Allows the
         * user
         * to select specific fields such as name, email, phone number, age, or date of
         * birth
         * to update and save the changes.
         * 
         * @param user     the user whose profile is to be updated.
         * @param userType the type of user (e.g., Doctor, Patient) whose profile is
         *                 being updated.
         * @throws PageBackException if the user chooses to go back to the previous
         *                           page.
         */
        public static void updateUserProfile(User user, UserType userType) throws PageBackException {
                ClearDisplay.ClearConsole();
                System.out.println("Your profile");
                System.out.println("--------------------------------");
                profileFields(user);
                System.out.println();
                System.out.println("\t1. Name");
                System.out.println("\t2. Email");
                System.out.println("\t3. Phone Number");
                System.out.println("\t4. Age");
                System.out.println("\t5. Date of Birth");
                System.out.println("\t6. Go back");
                System.out.println();
                Map<String, String> updatedFields = new HashMap<>();

                while (true) {
                        System.out.printf("What field would you like to update? ");

                        int choice = CustScanner.getIntChoice();
                        String inputField = "";
                        switch (choice) {
                                case 1 -> inputField = "Name";
                                case 2 -> inputField = "Email";
                                case 3 -> inputField = "Phone Number";
                                case 4 -> inputField = "Age";
                                case 5 -> inputField = "Date of Birth";
                                case 6 ->
                                        throw new PageBackException();
                                default -> {
                                        System.out.println("Invalid choice. Please try again.");
                                        throw new PageBackException();
                                }
                        }

                        if (choice == 5) {
                                System.out.printf("Enter new date of birth (DD/MM/YYYY): ", inputField);
                        } else {
                                System.out.printf("Enter new %s: ", inputField);
                        }
                        String newValue = CustScanner.getStrChoice();

                        updatedFields.put(inputField, newValue);

                        System.out.println();
                        System.out.printf("Do you want to update another field? (y/n) ");
                        if (CustScanner.getStrChoice().equalsIgnoreCase("n")) {
                                break;
                        }
                }

                System.out.println("Updated fields:");
                updatedFields.forEach((key, value) -> System.out.printf("%s: %s\n", key, value));
                System.out.println();
                System.out.printf("Do you want to save these changes? (y/n) ");
                if (CustScanner.getStrChoice().equalsIgnoreCase("y")) {
                        try {
                                UserManager.updateUserProfile(user, userType, updatedFields);
                        } catch (Exception e) {
                                System.out.println("Error updating profile: " + e.getMessage());
                        }

                }
                EnterToGoBackDisplay.display();
        }
}
