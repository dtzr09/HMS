package display.user;

import java.util.List;

import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import controller.user.AdministratorManager;
import controller.user.UserManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import display.medication.MedicationDisplay;
import model.medication.Medication;
import model.request.ReplenishmentRequest;
import model.user.Administrator;
import model.user.User;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
import utils.iocontrol.CustScanner;
import utils.utils.FormatDateTime;

public class AdministratorDisplay {

    private static String threeColBorder = "+--------------------------------------+----------------------+------------------------------+";
    private static String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+-----------------+";

    public static void administratorDisplay(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Administrator administrator) {
            System.out.println("===================================");
            System.out.println("Welcome to Administrator Main Page");
            System.out.println();
            System.out.println("Hello, " + administrator.getName() + "!");
            System.out.println();
            System.out.println("\t1. View hospital staffs");
            System.out.println("\t2. Manage hospital staffs");
            System.out.println("\t3. View medication inventory");
            System.out.println("\t4. Manage medication inventory");
            System.out.println("\t5. View pending medication replenisment request");
            System.out.println("\t6. Manage medication replenishment request");
            System.out.println("\t7. View my profile");
            System.out.println("\t8. Update my profile");
            System.out.println("\t9. Change my password");
            System.out.println("\t10. Logout");
            System.out.println();
            System.out.println("===================================");

            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();
            UserType userType = UserType.ADMINISTRATOR;

            try {
                switch (choice) {
                    case 1 -> viewHospitalStaffs();
                    case 2 -> manageHospitalStaffs(administrator);
                    case 3 -> MedicationDisplay.viewMedicationInventory();
                    case 4 -> MedicationDisplay.medicationDisplay(administrator);
                    case 5 ->
                        viewPendingMedicationReplenishmentRequest();
                    case 6 -> manageMedicationReplenishmentRequest(administrator);
                    case 7 -> UserProfileDisplay.viewUserProfilePage(administrator, userType);
                    case 8 -> UserProfileDisplay.updateUserProfile(administrator, userType);
                    case 9 -> ChangePasswordDisplay.changePassword(administrator, userType);
                    case 10 -> LogoutDisplay.logout();
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        administratorDisplay(user);
                    }
                }
            } catch (PageBackException e) {
                administratorDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not an administrator.");
        }
    }

    private static void displayTableHeader() {
        System.out.println(threeColBorder);
        System.out.printf("| %-36s | %-20s | %-28s |%n", "ID", "Name", "Email");
        System.out.println(threeColBorder);
    }

    private static <T extends User> void displayUserTable(String title, List<T> users) {
        System.out.println(threeColBorder);
        System.out.printf("| %-90s |%n", " " + title);
        displayTableHeader();

        if (users.isEmpty()) {
            System.out.printf("| %-90s |%n", "No " + title.toLowerCase() + " found.");
        } else {
            for (T user : users) {
                System.out.printf("| %-36s | %-20s | %-28s |%n",
                        user.getModelID() != null ? user.getModelID() : "N/A",
                        user.getName() != null ? user.getName() : "N/A",
                        user.getEmail() != null ? user.getEmail() : "N/A");
            }
        }
        System.out.println(threeColBorder);
        System.out.println();
    }

    private static void viewHospitalStaffs() throws PageBackException {
        ClearDisplay.ClearConsole();

        displayUserTable("DOCTORS", UserManager.getDoctors());
        displayUserTable("PHARMACISTS", UserManager.getPharmacists());
        displayUserTable("ADMINISTRATORS", UserManager.getAdministrators());

        EnterToGoBackDisplay.display();
    }

    private static void viewHospitalStaffsByUsertype(UserType userType) {
        ClearDisplay.ClearConsole();
        switch (userType) {
            case DOCTOR -> displayUserTable("DOCTORS", UserManager.getDoctors());
            case PHARMACIST -> displayUserTable("PHARMACISTS", UserManager.getPharmacists());
            case ADMINISTRATOR -> displayUserTable("ADMINISTRATORS", UserManager.getAdministrators());
            default -> System.out.println("Invalid user type.");
        }
    }

    private static void manageHospitalStaffs(Administrator administrator) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== MANAGE HOSPITAL STAFFS ==============");
        System.out.println();
        System.out.println("\t1. Onboard hospital staff");
        System.out.println("\t2. Remove hospital staff");
        System.out.println("\t3. Back");
        System.out.println("====================================================");
        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();
        switch (choice) {
            case 1 -> addHospitalStaff(administrator);
            case 2 -> removeHospitalStaff(administrator);
            case 3 -> throw new PageBackException();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                manageHospitalStaffs(administrator);
            }
        }
    }

    private static void addHospitalStaff(Administrator administrator) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== ONBOARD NEW HOSPITAL STAFF ==============");
        System.out.println();
        System.out.println("\t1. Onboard new doctor");
        System.out.println("\t2. Onboard new pharmacist");
        System.out.println("\t3. Onboard new administrator");
        System.out.println("\t4. Back");
        System.out.println("================================================");
        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

        System.out.print("Enter name: ");
        String name = CustScanner.getStrChoice();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter your Name: ");
            name = CustScanner.getStrChoice();
        }

        System.out.print("Enter email: ");
        String email = CustScanner.getStrChoice();

        while (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            email = CustScanner.getStrChoice();
        }

        System.out.print("What is your gender? [M/F]: ");
        String genderInput = CustScanner.getStrChoice();
        while (genderInput.isEmpty() || (!genderInput.equalsIgnoreCase("M") && !genderInput.equalsIgnoreCase("F"))) {
            System.out.println("Invalid choice. Please try again.");
            System.out.print("What is your gender? [M/F]: ");
            genderInput = CustScanner.getStrChoice();
        }
        Gender gender = null;
        if (genderInput.equalsIgnoreCase("M")) {
            gender = Gender.MALE;
        } else if (genderInput.equalsIgnoreCase("F")) {
            gender = Gender.FEMALE;
        }

        System.out.print("Enter your age: ");
        int age = CustScanner.getIntChoice();

        UserType userType = null;
        switch (choice) {
            case 1 -> userType = UserType.DOCTOR;
            case 2 -> userType = UserType.PHARMACIST;
            case 3 -> userType = UserType.ADMINISTRATOR;
            case 4 -> manageHospitalStaffs(administrator);
            default -> {
                System.out.println("Invalid choice. Please try again.");
                addHospitalStaff(administrator);
            }
        }

        try {
            AdministratorManager.addNewHospitalStaff(email, name, gender, age, userType);
            System.out.println("Hospital staff successfully onboarded.");
        } catch (ModelNotFoundException e) {
            System.out.println("Error onboarding hospital staff.");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        EnterToGoBackDisplay.display();
    }

    private static void removeHospitalStaff(Administrator administrator) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== REMOVE HOSPITAL STAFF ==============");
        System.out.println();
        System.out.println("\t1. Remove doctor");
        System.out.println("\t2. Remove pharmacist");
        System.out.println("\t3. Remove administrator");
        System.out.println("\t4. Back");
        System.out.println("================================================");
        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();
        UserType userType = null;

        switch (choice) {
            case 1 -> userType = UserType.DOCTOR;
            case 2 -> userType = UserType.PHARMACIST;
            case 3 -> userType = UserType.ADMINISTRATOR;
            case 4 -> throw new PageBackException();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                removeHospitalStaff(administrator);
            }
        }

        viewHospitalStaffsByUsertype(userType);

        System.out.print("Enter staff ID: ");
        String staffId = CustScanner.getStrChoice();

        try {
            AdministratorManager.removeNewHospitalStaff(staffId, userType);
            System.out.println("Hospital staff successfully removed.");
        } catch (ModelNotFoundException e) {
            System.out.println("Error removing hospital staff.");
        } catch (UserCannotBeFoundException e) {
            System.out.println(e.getMessage());
        }

        EnterToGoBackDisplay.display();

    }

    private static void viewPendingRequests() {
        System.out.println(fiveColBorder);
        try {
            System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Name", "Current Quantity",
                    "Status", "Date of Request");
            System.out.println(fiveColBorder);
            List<ReplenishmentRequest> pendingRequest = ReplenishmentRequestManager
                    .viewPendingMedicationReplenishmentRequest();
            if (pendingRequest.isEmpty() || pendingRequest == null) {
                System.out.printf("%-36s", "No pending requests found.");
                System.out.println(fiveColBorder);
                System.out.println();
                EnterToGoBackDisplay.display();
            }
            for (int i = 0; i < pendingRequest.size(); i++) {
                ReplenishmentRequest request = pendingRequest
                        .get(i);
                Medication medication = MedicationManager.findMedication(request.getMedicationID());
                if (medication == null) {
                    System.out.println("Something went wrong. No medication found.");
                    throw new PageBackException();
                } else {
                    System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", request
                            .getRequestID(),
                            medication.getName(),
                            +medication.getStock(),
                            request.getStatus(),
                            FormatDateTime.formatDateSimple(request.getDateOfRequest()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No pending requests found.");
        }

        System.out.println(fiveColBorder);
    }

    public static void viewPendingMedicationReplenishmentRequest() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println(fiveColBorder);
        System.out.printf("| %-123s |%n", " " + "PENDING REPLENISHMENT REQUEST ");
        System.out.println(fiveColBorder);
        viewPendingRequests();
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    public static void manageMedicationReplenishmentRequest(Administrator user) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== MANAGE REPLENISHMENT REQUEST ==============");

        if (!ReplenishmentRequestManager.isThereAnyPendingRequests()) {
            System.out.println("No pending requests found.");
            EnterToGoBackDisplay.display();
        }

        System.out.println();
        System.out.println("\t1. Approve medication replenishment request");
        System.out.println("\t2. Decline medication replenishment request");
        System.out.println("\t3. Back");
        System.out.println("=========================================================");

        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

        switch (choice) {
            case 1 -> approveMedicationReplenishmentRequestDisplay();
            case 2 -> declineMedicationReplenishmentRequestDisplay();
            case 3 -> throw new PageBackException();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                manageMedicationReplenishmentRequest(user);
            }
        }
        viewPendingRequests();
        EnterToGoBackDisplay.display();

    }

    private static void approveMedicationReplenishmentRequestDisplay() {
        System.out.println("============== APPROVE REPLENISHMENT REQUEST ==============");
        System.out.println();
        viewPendingRequests();
        System.out.println();
        System.out.print("Enter request ID: ");
        String requestId = CustScanner.getStrChoice();
        if (ReplenishmentRequestManager.approveMedicationReplenishmentRequest(requestId)) {
            System.out.println("Request approved.");
        } else {
            System.out.println("Request not found.");
        }
    }

    private static void declineMedicationReplenishmentRequestDisplay() {
        System.out.println("============== APPROVE REPLENISHMENT REQUEST ==============");
        System.out.println();
        viewPendingRequests();
        System.out.println();
        System.out.print("Enter request ID: ");
        String requestId = CustScanner.getStrChoice();
        if (ReplenishmentRequestManager.approveMedicationReplenishmentRequest(requestId)) {
            System.out.println("Request approved.");
        } else {
            System.out.println("Request not found.");
        }
    }

}
