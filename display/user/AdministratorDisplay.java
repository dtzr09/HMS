package display.user;

import java.util.List;

import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import controller.user.AdministratorManager;
import controller.user.UserManager;
import display.ClearDisplay;
import display.MedicationDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import model.medication.Medication;
import model.request.ReplenishmentRequest;
import model.user.Administrator;
import model.user.User;
import model.user.Doctor;
import model.user.Pharmacist;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
import utils.iocontrol.CustScanner;

public class AdministratorDisplay {

    public static void administratorDisplay(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Administrator administrator) {
            System.out.println("===================================");
            System.out.println("Welcome to Administrator Main Page");
            System.out.println("Hello, " + administrator.getName() + "!");
            System.out.println();
            System.out.println("\t1. View hospital staffs");
            System.out.println("\t2. Manage hospital staffs");
            System.out.println("\t3. View medication inventory");
            System.out.println("\t4. Manage medication inventory");
            System.out.println("\t5. View pending medication replenisment request");
            System.out.println("\t6. Approve medication replenishment request");
            System.out.println("\t7. View my profile");
            System.out.println("\t8. Change my password");
            System.out.println("\t9. Logout");
            System.out.println("===================================");

            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();

            try {
                switch (choice) {
                    case 1 -> viewHospitalStaffs();
                    case 2 -> manageHospitalStaffs(administrator);
                    case 3 -> viewMedicationInventory(administrator);
                    case 4 -> MedicationDisplay.medicationDisplay(administrator);
                    case 5 ->
                        viewPendingMedicationReplenishmentRequest();
                    case 6 -> approveMedicationReplenishmentRequest(administrator);
                    case 7 -> ViewUserProfileDisplay.viewUserProfilePage(administrator, UserType.ADMINISTRATOR);
                    case 8 -> ChangePasswordDisplay.changePassword(administrator, UserType.ADMINISTRATOR);
                    case 9 -> LogoutDisplay.logout();
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

    private static void viewHospitalStaffs() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== DOCTORS ==============");
        System.out.println();
        System.out.println("ID\tName\tEmail");
        System.out.println("=====================================");
        List<Doctor> doctors = UserManager.getDoctors();
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getModelID() + "\t" + doctor.getEmail() + "\t" + doctor.getEmail());
        }
        System.out.println();

        System.out.println("============== PHARMACISTS ==============");
        System.out.println();
        System.out.println("ID\tName\tEmail");
        System.out.println("=========================================");
        List<Pharmacist> pharmacists = UserManager.getPharmacists();
        for (Pharmacist pharmacist : pharmacists) {
            System.out.println(pharmacist.getModelID() + "\t" + pharmacist.getEmail() + "\t" + pharmacist.getEmail());
        }
        System.out.println();

        System.out.println("============== ADMINISTRATORS ==============");
        System.out.println();
        System.out.println("ID\tName\tEmail");
        System.out.println("============================================");
        List<Administrator> administrators = UserManager.getAdministrators();
        for (Administrator administrator : administrators) {
            System.out.println(
                    administrator.getModelID() + "\t" + administrator.getEmail() + "\t" + administrator.getEmail());
        }

        System.out.println("Press enter to go back.");
        CustScanner.getStrChoice();
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
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

        System.out.print("Enter email: ");
        String email = CustScanner.getStrChoice();

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
            AdministratorManager.addNewHospitalStaff(email, name, userType);
            System.out.println("Hospital staff successfully onboarded.");
        } catch (ModelNotFoundException e) {
            System.out.println("Error onboarding hospital staff.");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Press enter to go back.");
        CustScanner.getStrChoice();
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
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

        System.out.print("Enter staff ID: ");
        String staffId = CustScanner.getStrChoice();

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

        try {
            AdministratorManager.removeNewHospitalStaff(staffId, userType);
            System.out.println("Hospital staff successfully removed.");
        } catch (ModelNotFoundException e) {
            System.out.println("Error removing hospital staff.");
        } catch (UserCannotBeFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void viewMedicationInventory(Administrator administrator) {
        ClearDisplay.ClearConsole();
        System.out.println("============== MEDICATION INVENTORY ==============");
        System.out.println();
        System.out.println("ID\tName\tQuantity\tLow Stock Level Alert");
        System.out.println("=================================================");
        List<Medication> medications = MedicationManager.getMedications();
        for (Medication medication : medications) {
            System.out.println(medication.getModelID() + "\t" + medication.getName() +
                    "\t"
                    + medication.getStock() + "\t" + medication.getLowStockLevelAlert());
        }
    }

    public static void viewPendingMedicationReplenishmentRequest() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== PENDING REPLENISHMENT REQUEST ==============");
        System.out.println();
        System.out.println("Request ID\tMedication Name\tCurrent Quantity\tStatus\tDate of Request");
        System.out.println("==========================================================");
        ReplenishmentRequestManager.viewPendingMedicationReplenishmentRequest();
        for (int i = 0; i < ReplenishmentRequestManager.viewPendingMedicationReplenishmentRequest().size(); i++) {
            ReplenishmentRequest request = ReplenishmentRequestManager.viewPendingMedicationReplenishmentRequest()
                    .get(i);
            Medication medication = null;
            try {
                medication = MedicationManager.findMedication(request.getMedicationID());
            } catch (ModelNotFoundException e) {
                System.out.println("Medication not found.");
                return;
            }

            if (medication == null) {
                System.out.println("Medication not found.");
                return;
            } else {
                System.out.println(request
                        .getRequestID()
                        + "\t"
                        + medication.getName()
                        + "\t"
                        + medication.getStock()
                        + "\t"
                        + request.getStatus()
                        + "\t"
                        + request.getDateOfRequest());
            }
        }

        System.out.println("Press enter to go back.");
        CustScanner.getStrChoice();
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }

    public static void approveMedicationReplenishmentRequest(Administrator user) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== APPROVE OF REPLENISHMENT REQUEST ==============");
        System.out.println();
        System.out.print("Enter request ID: ");
        String requestId = CustScanner.getStrChoice();
        if (ReplenishmentRequestManager.approveMedicationReplenishmentRequest(requestId)) {
            System.out.println("Request approved.");
        } else {
            System.out.println("Request not found.");
        }
        System.out.println("Press enter to go back.");
        CustScanner.getStrChoice();
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }

}
