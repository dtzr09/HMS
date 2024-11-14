package display.user;

import java.util.List;

import controller.appointment.AppointmentManager;
import controller.appointment.AppointmentOutcomeManager;
import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import controller.user.AdministratorManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import controller.user.UserManager;
import display.appointment.AppointmentDisplay;
import display.medicalRecords.MedicationDisplay;
import display.password.ChangePasswordDisplay;
import display.session.ClearDisplay;
import display.session.EnterToGoBackDisplay;
import display.session.LogoutDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.appointment.enums.AppointmentOutcomeStatus;
import model.diagnosis.Diagnosis;
import model.diagnosis.DiagnosisRecord;
import model.medication.Medication;
import model.request.ReplenishmentRequest;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
import utils.iocontrol.CustScanner;
import utils.utils.FormatDateTime;

/**
 * The {@code AdministratorDisplay} class provides an interface for
 * administrators
 * in the Hospital Management System. It allows administrators to perform tasks
 * such as viewing and managing hospital staff, viewing and managing medication
 * inventory, handling medication replenishment requests, and viewing and
 * updating
 * their profile.
 */
public class AdministratorDisplay {

    private static String threeColBorder = "+--------------------------------------+----------------------+------------------------------+";
    private static String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+----------------------+";

    /**
     * Displays the main page for the administrator, listing the available options
     * for managing the hospital and viewing their own profile.
     * 
     * @param user the logged-in user, which must be of type {@code Administrator}.
     */
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
            System.out.println("\t3. View doctor appointment details");
            System.out.println("\t4. View medication inventory");
            System.out.println("\t5. Manage medication inventory");
            System.out.println("\t6. View pending medication replenisment request");
            System.out.println("\t7. Manage medication replenishment request");
            System.out.println("\t8. View my profile");
            System.out.println("\t9. Update my profile");
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");
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
                    case 3 -> viewAppointmentDetailsDisplay();
                    case 4 -> MedicationDisplay.viewMedicationInventory();
                    case 5 -> MedicationDisplay.medicationDisplay(administrator);
                    case 6 ->
                        viewPendingMedicationReplenishmentRequest();
                    case 7 -> manageMedicationReplenishmentRequest(administrator);
                    case 8 -> UserProfileDisplay.viewUserProfilePage(administrator, userType);
                    case 9 -> UserProfileDisplay.updateUserProfile(administrator, userType);
                    case 10 -> ChangePasswordDisplay.changePassword(administrator, userType);
                    case 11 -> LogoutDisplay.logout();
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

    /**
     * Displays the view appointment details screen. Clears the console, lists all
     * doctors with appointments,
     * and prompts the user to enter a doctor's ID to view the respective
     * appointments.
     * If the user inputs "b", the method throws a PageBackException to go back to
     * the previous page.
     * If the doctor ID is empty, a message is displayed, and the user is prompted
     * to try again.
     * 
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void viewAppointmentDetailsDisplay() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== VIEW APPOINTMENT DETAILS ==============");
        System.out.println();
        DoctorDisplay.displayDoctorAppointments();
        System.out.println();
        System.out.printf("Enter doctor ID to view his/her appointments or [b] to go back: ");
        String doctorId = CustScanner.getStrChoice();
        if (doctorId.equalsIgnoreCase("b")) {
            throw new PageBackException();
        }
        if (doctorId.isEmpty() || doctorId == "") {
            System.out.println("Doctor ID cannot be empty.\n");
            EnterToGoBackDisplay.display();
        }
        ClearDisplay.ClearConsole();
        try {
            displayAllAppointmentsForADoctor(doctorId);
        } catch (Exception e) {
            viewAppointmentDetailsDisplay();
        }

        EnterToGoBackDisplay.display();
    }

    /**
     * Displays all appointments for a specified doctor. Clears the console and
     * retrieves the doctor by ID,
     * displaying the doctor's name and their appointments. If no appointments are
     * found, the user is notified.
     * Prompts the user to enter an appointment ID to view further details or "b" to
     * go back.
     * 
     * @param doctorId the ID of the doctor whose appointments are to be displayed.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void displayAllAppointmentsForADoctor(String doctorId) throws PageBackException {
        ClearDisplay.ClearConsole();
        Doctor doctor = DoctorManager.getDoctorByID(doctorId);
        System.out.println("All appointments for Dr. " + doctor.getName());
        List<Appointment> appointments = AppointmentManager.getAllDoctorAppointments(doctorId);
        System.out.println();
        if (appointments.isEmpty() || appointments == null || appointments.size() == 0) {
            System.out.println("No appointments found.\n");
            EnterToGoBackDisplay.display();
        }
        AppointmentDisplay.viewAppointments(appointments);
        System.out.println();
        System.out.printf("Enter appointment ID to view appointment details or [b] to go back: ");
        String appointmentId = CustScanner.getStrChoice();
        if (appointmentId.isEmpty() || appointmentId == "") {
            System.out.println("Appointment ID cannot be empty.\n");
            EnterToGoBackDisplay.display();
        }
        if (appointmentId.equalsIgnoreCase("b")) {
            throw new PageBackException();
        }
        try {
            displayAppointmentDetails(appointmentId);
        } catch (Exception e) {
            displayAllAppointmentsForADoctor(doctorId);
        }
    }

    /**
     * Displays detailed information for a specific appointment by its appointment
     * ID. Clears the console and retrieves
     * the appointment details, including doctor and patient information,
     * appointment status, and outcome.
     * If any part of the information (appointment, doctor, patient, or outcome) is
     * missing or retrieval fails, an
     * error message is displayed, and the method throws a PageBackException to
     * return to the previous page.
     * 
     * For completed appointments, the method displays additional details, including
     * the type of service,
     * consultation notes, diagnosis, and prescribed medications. If the appointment
     * is still pending,
     * it shows that outcome status.
     * 
     * @param appointmentId the ID of the appointment to display.
     * @throws PageBackException if any data retrieval fails or if any required data
     *                           (appointment, doctor, patient,
     *                           or outcome) is not found, prompting the user to go
     *                           back.
     */
    private static void displayAppointmentDetails(String appointmentId) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Appointment details for appointment ID: " + appointmentId);
        Appointment appointment = AppointmentManager.getAppointmentByID(appointmentId);
        if (appointment == null) {
            System.out.println("Something went wrong. Appointment not found.");
            throw new PageBackException();
        }
        Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
        if (doctor == null) {
            System.out.println("Something went wrong. Doctor not found.");
            throw new PageBackException();
        }
        Patient patient = null;
        try {
            patient = PatientManager.getPatientById(appointment.getPatientID());
        } catch (Exception e) {
            System.out.println("Something went wrong. Patient not found.");
            throw new PageBackException();
        }

        if (patient == null) {
            System.out.println("Something went wrong. Patient not found.");
            throw new PageBackException();
        }
        AppointmentOutcome outcome = AppointmentOutcomeManager.getAppointmentOutcomeByAppointmentID(appointmentId);
        if (outcome == null) {
            System.out.println("Something went wrong. Appointment outcome not found.");
            throw new PageBackException();
        }
        DiagnosisRecord diagnosisRecord = null;
        if (outcome.getStatus() == AppointmentOutcomeStatus.COMPLETED) {
            try {
                Diagnosis diagnosis = DiagnosisManager.getDiagnosisByID(patient, outcome.getDiagnosisID());
                diagnosisRecord = DiagnosisManager.getAPatientDiagnosisRecord(diagnosis, doctor);
            } catch (Exception e) {
                System.out.println("Something went wrong. Diagnosis not found.");
                throw new PageBackException();
            }
        }
        System.out.println();
        System.out.println("Appointment ID: " + appointment.getAppointmentID());
        System.out.println("Doctor Name: " + doctor.getName());
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Date of Appointment: " + appointment.getDateOfAppointment());
        System.out
                .println("Time of Appointment: " + AppointmentDisplay.getTimeSlot(appointment.getTimeOfAppointment()));
        System.out.println("Status: " + appointment.getAppointmentStatus());
        System.out.println();
        System.out.println("Appointment Outcome:");
        System.out.println();
        if (outcome.getStatus() == AppointmentOutcomeStatus.COMPLETED) {
            System.out.println("Type of Service: " + outcome.getTypeOfService());
            System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
            System.out.println("Diagnosis: " + diagnosisRecord.getDisease());
            if (diagnosisRecord.getMedicationNames().isEmpty() || diagnosisRecord.getMedicationNames().size() == 0) {
                System.out.println("Prescribed Medications: NA");
            } else {
                System.out.println("Prescribed Medications: " + String.join(",", diagnosisRecord.getMedicationNames()));
            }

        } else {
            System.out.println("Still pending.");
        }
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the table header for hospital staff details.
     */
    private static void displayTableHeader() {
        System.out.println(threeColBorder);
        System.out.printf("| %-36s | %-20s | %-28s |%n", "ID", "Name", "Email");
        System.out.println(threeColBorder);
    }

    /**
     * Displays a table of users with the specified title. It lists users' IDs,
     * names,
     * and emails.
     * 
     * @param title the title of the table (e.g., "DOCTORS", "PHARMACISTS").
     * @param users the list of users to be displayed.
     * @param <T>   a subclass of {@code User}.
     */
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

    /**
     * Displays all hospital staff by category (Doctors, Pharmacists, and
     * Administrators).
     * 
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void viewHospitalStaffs() throws PageBackException {
        ClearDisplay.ClearConsole();

        displayUserTable("DOCTORS", UserManager.getDoctors());
        displayUserTable("PHARMACISTS", UserManager.getPharmacists());
        displayUserTable("ADMINISTRATORS", UserManager.getAdministrators());

        EnterToGoBackDisplay.display();
    }

    /**
     * Displays hospital staff of a specific user type.
     * 
     * @param userType the type of user to display (DOCTOR, PHARMACIST,
     *                 ADMINISTRATOR).
     */
    private static void viewHospitalStaffsByUsertype(UserType userType) {
        ClearDisplay.ClearConsole();
        switch (userType) {
            case DOCTOR -> displayUserTable("DOCTORS", UserManager.getDoctors());
            case PHARMACIST -> displayUserTable("PHARMACISTS", UserManager.getPharmacists());
            case ADMINISTRATOR -> displayUserTable("ADMINISTRATORS", UserManager.getAdministrators());
            default -> System.out.println("Invalid user type.");
        }
    }

    /**
     * Displays the menu for managing hospital staff, providing options to add or
     * remove staff members.
     * 
     * @param administrator the logged-in administrator.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
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

    /**
     * Displays the menu for adding a new hospital staff member, prompting the
     * administrator to enter details such as name, email, gender, and age.
     * 
     * @param administrator the logged-in administrator.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
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

    /**
     * Displays the menu for removing a hospital staff member by selecting the user
     * type
     * and entering the staff's ID.
     * 
     * @param administrator the logged-in administrator.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
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

    /**
     * Displays a table of pending medication replenishment requests.
     */
    private static void viewPendingRequests() {
        String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+----------------------+";
        try {
            System.out.println(fiveColBorder);
            System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-20s | %n", "ID", "Name", "Current Quantity",
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
                Medication medication = MedicationManager.getMedicationsById(request.getMedicationID());
                if (medication == null) {
                    System.out.println("Something went wrong. No medication found.");
                    throw new PageBackException();
                } else {
                    System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-20s | %n", request
                            .getRequestID(),
                            medication.getName(),
                            +medication.getStock(),
                            request.getStatus(),
                            FormatDateTime.formatDateTimeToString(request.getDateOfRequest()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No pending requests found.");
        }

        System.out.println(fiveColBorder);
    }

    /**
     * Displays the pending medication replenishment requests.
     * 
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    public static void viewPendingMedicationReplenishmentRequest() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println(fiveColBorder);
        System.out.printf("| %-128s |%n", " " + "PENDING REPLENISHMENT REQUEST ");
        System.out.println(fiveColBorder);
        viewPendingRequests();
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the menu for managing medication replenishment requests, allowing
     * the administrator to approve or decline requests.
     * 
     * @param user the logged-in administrator.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
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
        System.out.println();
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
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the approval process for a medication replenishment request,
     * allowing
     * the administrator to enter the request ID and approve it.
     */
    private static void approveMedicationReplenishmentRequestDisplay() {
        ClearDisplay.ClearConsole();
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

    /**
     * Displays the decline process for a medication replenishment request, allowing
     * the administrator to enter the request ID and decline it.
     */
    private static void declineMedicationReplenishmentRequestDisplay() {
        ClearDisplay.ClearConsole();
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
