package display.user;

import java.util.List;

import controller.appointment.AppointmentManager;
import controller.appointment.AppointmentOutcomeManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.appointment.AppointmentDisplay;
import display.appointment.AppointmentOutcomeDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import display.medication.DiagnosisDisplay;
import model.appointment.Appointment;
import model.appointment.enums.AppointmentStatus;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class PatientDisplay {
    /**
     * Displays the patient's main page.
     * 
     * @param user
     */
    public static void patientDisplay(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Patient patient) {
            System.out.println("===================================");
            System.out.println("Welcome to Patient Main Page");
            System.out.println("Hello, " + patient.getName() + "!");
            System.out.println();
            System.out.println("\t1. View medical record");
            System.out.println("\t2. View available appointment slots");
            System.out.println("\t3. Schedule an appointment");
            System.out.println("\t4. Reschedule an appointment");
            System.out.println("\t5. Cancel an appointment");
            System.out.println("\t6. View scheduled appointments");
            System.out.println("\t7. View pending appointments");
            System.out.println("\t8. View past appointment outcome records");
            System.out.println("\t9. View my profile");
            System.out.println("\t10. Update my profile");
            System.out.println("\t11. Change my password");
            System.out.println("\t12. Logout");
            System.out.println();
            System.out.println("===================================");
            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();
            UserType userType = UserType.PATIENT;

            try {
                switch (choice) {
                    case 1 -> displayPatientInfo(patient);
                    case 2 -> displayAvailableAppointmentSlots(patient);
                    case 3 -> scheduleAppointment(patient, "schedule", null);
                    case 4 -> rescheduleAppointment(patient);
                    case 5 -> cancelAppointment(patient);
                    case 6 -> viewAppointments(patient, AppointmentStatus.APPROVED);
                    case 7 -> viewAppointments(patient, AppointmentStatus.PENDING);
                    case 8 -> displayPastAppointmentRecords(patient);
                    case 9 -> UserProfileDisplay.viewUserProfilePage(patient, userType);
                    case 10 -> UserProfileDisplay.updateUserProfile(patient, userType);
                    case 11 -> ChangePasswordDisplay.changePassword(patient, userType);
                    case 12 -> LogoutDisplay.logout();
                    default -> System.out.println("Invalid option, please try again.");
                }
            } catch (PageBackException e) {
                patientDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not a Patient.");
        }
    }

    /**
     * Displays the patient's main page.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void rescheduleAppointment(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Reschedule an Appointment");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Your scheduled appointments:");
        AppointmentDisplay.displayAllPatientsAppointment(patient);
        System.out.println();
        List<Appointment> appointments = AppointmentManager.getPatientAppointment(patient.getPatientID());
        if (appointments.isEmpty() || appointments == null || appointments.size() == 0) {
            System.out.println("You do not have any appointments scheduled.");
            EnterToGoBackDisplay.display();
        }

        System.out.printf("Enter the appointment ID you would like to reschedule or press enter to go back. ");
        String appointmentID = CustScanner.getStrChoice();

        if (appointmentID == "") {
            throw new PageBackException();
        } else if (appointmentID == null || !AppointmentManager.doesAppointmentExist(appointmentID)) {
            System.out.println("Invalid appointment ID.");
            EnterToGoBackDisplay.display();
        }

        scheduleAppointment(patient, "reschedule", appointmentID);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Cancels an appointment display.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void cancelAppointment(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Cancel an Appointment");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Your scheduled appointments:");
        AppointmentDisplay.displayPatientsAppointment(patient, AppointmentStatus.APPROVED);
        System.out.println();

        System.out.printf("Enter the appointment ID you would like to cancel.");
        String appointmentID = CustScanner.getStrChoice();

        if (appointmentID == null || appointmentID == "") {
            System.out.println("Invalid appointment ID.");
            EnterToGoBackDisplay.display();
        }

        try {
            AppointmentManager.doesAppointmentExist(appointmentID);
        } catch (Exception e) {
            System.out.println("Appointment does not exist.");
            EnterToGoBackDisplay.display();
        }

        try {
            AppointmentManager.cancelAppointment(patient.getPatientID(), appointmentID);
            System.out.println("Appointment cancelled successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Schedule an appointment display.
     * 
     * @param patient
     * @param action
     * @param appointmentID
     * @throws PageBackException
     */
    private static void scheduleAppointment(Patient patient, String action, String appointmentID)
            throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Schedule an Appointment");
        System.out.println("--------------------------------------------");
        String doctorID = patient.getDoctorID();
        if (doctorID == null) {
            System.out.println("You do not have a doctor assigned to you. Please contact the admin.");
            EnterToGoBackDisplay.display();
            return;
        }
        try {
            AppointmentDisplay.scheduleAppointmentDisplay(patient, doctorID, action, appointmentID);
        } catch (ModelNotFoundException | ModelAlreadyExistsException e) {
            System.out.println("Something went wrong.");
            EnterToGoBackDisplay.display();
        }
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the available appointment slots for the patient.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void displayAvailableAppointmentSlots(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        Doctor doctor = null;
        try {
            String doctorID = patient.getDoctorID();
            doctor = DoctorManager.getDoctorByID(doctorID);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You do not have a doctor assigned to you. Please contact the admin.");
            EnterToGoBackDisplay.display();
        }

        System.out.println("Available Appointment Slots");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("This is your Dr. " + doctor.getName() + "'s available time slots.");
        System.out.println();
        AppointmentDisplay.displayDoctorTimeSlots(doctor);
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the patient's medical record and personal information.
     * 
     * @param patient
     * @throws PageBackException
     */
    public static void displayPatientInfo(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Medical Record of " + patient.getName());
        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.println("Personal Information ----------------------------");
        System.out.println();
        patient.getPersonalInfo().displayPersonalInfo();
        System.out.println();
        System.out.println("Medical Records ----------------------------");
        System.out.println();
        System.out.println("Allergies: " + patient.getAllergies());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println();

        DiagnosisDisplay.displayAllDiagnosisOfPatient(patient);

        System.out.println();
        AppointmentDisplay.displayPatientsAppointment(patient, AppointmentStatus.APPROVED);
        System.out.println();

        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the patient's scheduled appointments.
     * 
     * @param patient
     * @param status
     * @throws PageBackException
     */
    private static void viewAppointments(Patient patient, AppointmentStatus status) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Scheduled Appointments");
        System.out.println();
        AppointmentDisplay.displayPatientsAppointment(patient, status);
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the patient's past appointment records.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void displayPastAppointmentRecords(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        AppointmentOutcomeDisplay.viewAppointmentOutcomeRecordsForPatient(patient);
    }

    /**
     * Displays all patients for pharmacist.
     * 
     * @throws PageBackException
     */
    public static void viewAllPatients() throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+------------------------------+----------------------+";
        List<Patient> patients = PatientManager.getAllPatients();

        System.out.println(fourColBorder);
        System.out.printf("| %-113s |%n", "All Patients");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-28s | %-20s |%n", "ID", "Name", "Email", "No. of outcomes");
        System.out.println(fourColBorder);
        if (patients.isEmpty()) {
            System.out.printf("| %-90s |%n", "No patient found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        } else {
            for (Patient patient : patients) {
                int noOfAppointments = AppointmentOutcomeManager
                        .getNumberOfAppointmentOutcomeByPatientID(patient.getPatientID());
                System.out.printf("| %-36s | %-20s | %-28s | %-20s |%n",
                        patient.getModelID() != null ? patient.getModelID() : "N/A",
                        patient.getName() != null ? patient.getName() : "N/A",
                        patient.getEmail() != null ? patient.getEmail() : "N/A",
                        Integer.toString(noOfAppointments));
            }
        }
        System.out.println(fourColBorder);
    }

    /**
     * Displays the doctor's patients.
     * 
     * @param patients
     */
    public static void displayPatients(List<Patient> patients) {
        String threeColBorder = "+--------------------------------------+----------------------+------------------------------+";
        System.out.println(threeColBorder);
        System.out.printf("| %-90s |%n", "My Patients");
        System.out.println(threeColBorder);
        System.out.printf("| %-36s | %-20s | %-28s |%n", "ID", "Name", "Email");
        System.out.println(threeColBorder);
        if (patients.isEmpty()) {
            System.out.printf("| %-90s |%n", "No patient found.");
        } else {
            for (Patient patient : patients) {
                System.out.printf("| %-36s | %-20s | %-28s |%n",
                        patient.getModelID() != null ? patient.getModelID() : "N/A",
                        patient.getName() != null ? patient.getName() : "N/A",
                        patient.getEmail() != null ? patient.getEmail() : "N/A");
            }
        }
        System.out.println(threeColBorder);
    }

    /**
     * Updates the patient's medical record.
     * 
     * @param patient
     * @param doctor
     * @throws PageBackException
     */
    public static void updatePatientMedicalRecord(Patient patient, Doctor doctor) throws PageBackException {
        displayPatientInfo(patient);
        System.out.println();

        System.out.println("What would you like to update?");
        System.out.println("\t1. Add allergies");
        System.out.println("\t2. Add new diagnosis");
        System.out.println("\t3. Go back");
        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();
        System.out.println();

        switch (choice) {
            case 1 -> addAllergyDisplay(patient);
            case 2 -> DiagnosisDisplay.addDiagnosisDisplay(patient, doctor);
            case 3 -> throw new PageBackException();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Handles the addition of allergies.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void handleAddAllergy(Patient patient) throws PageBackException {
        System.out.printf("Enter the allergy you would like to add. ");
        String allergy = CustScanner.getStrChoice();
        if (allergy == null || allergy == "") {
            System.out.println("Invalid allergy. Please try again.");
            handleAddAllergy(patient);
        }
        try {
            DoctorManager.addPatientAllergies(patient, allergy);
            System.out.println("Allergy added successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

    /**
     * Displays the add allergy display.
     * 
     * @param patient
     * @throws PageBackException
     */
    private static void addAllergyDisplay(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Add Allergy");
        System.out.println("--------------------------------------------");
        System.out.println("Current allergies");
        try {
            List<String> allergies = patient.getAllergies();
            if (allergies.isEmpty() || allergies == null || allergies.size() == 0) {
                System.out.println("No allergies found.");
            } else {
                for (String allergy : allergies) {
                    System.out.println("\t" + allergy);
                }
            }
            System.out.println();

            while (true) {
                handleAddAllergy(patient);
                System.out.println("Allergy added successfully.");

                System.out.println("Would you like to add another allergy? (Y/N)");
                String choice = CustScanner.getStrChoice();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnterToGoBackDisplay.display();
    }

}
