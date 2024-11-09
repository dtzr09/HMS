package display.user;

import java.util.List;
import controller.user.DoctorManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.appointment.AppointmentDisplay;
import display.appointment.AppointmentOutcomeDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import display.medication.DiagnosisDisplay;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class PatientDisplay {
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
            System.out.println("\t7. View Past Appointment Outcome Records");
            System.out.println("\t8. View my profile");
            System.out.println("\t9. Update my profile");
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");
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
                    case 3 -> scheduleAppointment(patient);
                    // case 5 -> rescheduleAppointment(patient);
                    // case 6 -> cancelAppointment(patient);
                    // case 7 -> viewScheduledAppointments(patient);
                    case 7 -> displayPastAppointmentRecords(patient);
                    case 8 -> UserProfileDisplay.viewUserProfilePage(patient, userType);
                    case 9 -> UserProfileDisplay.updateUserProfile(patient, userType);
                    case 10 -> ChangePasswordDisplay.changePassword(patient, userType);
                    case 11 -> LogoutDisplay.logout();
                    default -> System.out.println("Invalid option, please try again.");
                }
            } catch (PageBackException e) {
                patientDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not a Patient.");
        }
    }

    private static void scheduleAppointment(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Schedule an Appointment");
        System.out.println("--------------------------------------------");
        String doctorID = patient.getDoctorID();
        if (doctorID == null) {
            System.out.println("You do not have a doctor assigned to you. Please contact the admin.");
            EnterToGoBackDisplay.display();
            return;
        }
        AppointmentDisplay.scheduleAppointmentDisplay(patient, doctorID);
        EnterToGoBackDisplay.display();
    }

    private static void displayAvailableAppointmentSlots(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        Doctor doctor = null;
        try {
            String doctorID = patient.getDoctorID();
            System.out.println(doctorID);
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
        AppointmentDisplay.displayPatientsAppointment(patient);
        System.out.println();

        EnterToGoBackDisplay.display();
    }

    // // Schedule an Appointment for the Patient
    // private static void scheduleAppointment(Patient patient) {
    // ClearDisplay.ClearConsole();
    // System.out.println("Scheduling an Appointment");

    // System.out.print("Enter Doctor ID to schedule with: ");
    // String doctorID = CustScanner.getStrChoice();
    // Doctor doctor = DoctorManager.getDoctorByID(doctorID);

    // if (doctor == null) {
    // System.out.println("Invalid Doctor ID. Please try again.");
    // EnterToGoBackDisplay.display();
    // return;
    // }

    // AppointmentDisplay.timeSlotDisplay(); // Show available slots for the
    // selected doctor
    // System.out.print("Enter time slot number: ");
    // int timeSlotNumber = CustScanner.getIntChoice();

    // Appointment appointment = AppointmentManager.createAppointment(patient,
    // doctor, timeSlotNumber);
    // if (appointment != null) {
    // System.out.println("Appointment scheduled successfully.");
    // } else {
    // System.out.println("Failed to schedule appointment.");
    // }
    // EnterToGoBackDisplay.display();
    // }

    // // Reschedule an Existing Appointment
    // private static void rescheduleAppointment(Patient patient) {
    // ClearDisplay.ClearConsole();
    // System.out.println("Rescheduling an Appointment");

    // viewScheduledAppointments(patient);

    // System.out.print("Enter the appointment ID to reschedule: ");
    // String appointmentID = CustScanner.getStrChoice();
    // Appointment appointment =
    // AppointmentManager.getAppointmentByID(patient.getModelID(), appointmentID);

    // if (appointment == null) {
    // System.out.println("Appointment not found.");
    // EnterToGoBackDisplay.display();
    // return;
    // }

    // AppointmentDisplay.timeSlotDisplay();
    // System.out.print("Enter new time slot number: ");
    // int newTimeSlotNumber = CustScanner.getIntChoice();

    // boolean success = AppointmentManager.rescheduleAppointment(appointment,
    // newTimeSlotNumber);
    // System.out.println(success ? "Appointment rescheduled successfully." :
    // "Failed to reschedule appointment.");
    // EnterToGoBackDisplay.display();
    // }

    // // Cancel an Appointment
    // private static void cancelAppointment(Patient patient) {
    // ClearDisplay.ClearConsole();
    // System.out.println("Cancelling an Appointment");

    // viewScheduledAppointments(patient);

    // System.out.print("Enter the appointment ID to cancel: ");
    // String appointmentID = CustScanner.getStrChoice();
    // boolean success = AppointmentManager.cancelAppointment(appointmentID);

    // System.out.println(success ? "Appointment cancelled successfully." : "Failed
    // to cancel appointment.");
    // EnterToGoBackDisplay.display();
    // }

    // // View All Scheduled Appointments
    // private static void viewScheduledAppointments(Patient patient) {
    // ClearDisplay.ClearConsole();
    // System.out.println("Scheduled Appointments:");
    // AppointmentDisplay.viewScheduledAppointments(patient.getAppointments()); //
    // Assumes this displays appointments
    // EnterToGoBackDisplay.display();
    // }

    private static void displayPastAppointmentRecords(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Past Appointment Outcome Records:");
        AppointmentOutcomeDisplay.viewAppointmentOutcomeRecordsForPatient(patient.getEmail());
    }

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

    public static void updatePatientMedicalRecord(Patient patient, Doctor doctor) throws PageBackException {
        displayPatientInfo(patient);
        System.out.println();

        System.out.println("What would you like to update?");
        System.out.println("\t1. Add allergies");
        System.out.println("\t2. Add new diagnosis");
        System.out.println("\t3. Go back");

        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

        switch (choice) {
            case 1 -> addAllergyDisplay(patient);
            case 2 -> DiagnosisDisplay.addDiagnosisDisplay(patient, doctor);
            case 3 -> throw new PageBackException();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private static void handleAddAllergy(Patient patient) throws PageBackException {
        System.out.println("Enter the allergy you would like to add.");
        String allergy = CustScanner.getStrChoice();
        try {
            DoctorManager.addPatientAllergies(patient, allergy);
            System.out.println("Allergy added successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

    private static void addAllergyDisplay(Patient patient) throws PageBackException {
        System.out.println("Add Allergy");
        System.out.println("--------------------------------------------");
        System.out.println("Current allergies");
        List<String> allergies = patient.getAllergies();
        for (String allergy : allergies) {
            System.out.println("\t" + allergy);
        }
        while (true) {
            handleAddAllergy(patient);
            System.out.println("Allergy added successfully.");

            System.out.println("Would you like to add another allergy? (Y/N)");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }
        EnterToGoBackDisplay.display();
    }

}
