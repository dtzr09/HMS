package display.user;

import java.util.List;
import controller.appointment.AppointmentManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.appointment.AppointmentDisplay;
import display.appointment.CalendarDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import display.medication.DiagnosisDisplay;
import model.appointment.Appointment;
import model.diagnosis.Diagnosis;
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
            System.out.println("\t2. Update personal information");
            System.out.println("\t3. View available appointment slots");
            System.out.println("\t4. Schedule an appointment");
            System.out.println("\t5. Reschedule an appointment");
            System.out.println("\t6. Cancel an appointment");
            System.out.println("\t7. View scheduled appointments");
            System.out.println("\t8. View Past Appointment Outcome Records");
            System.out.println("\t9. View my profile");
            System.out.println("\t10. Update my profile");
            System.out.println("\t11. Change my password");
            System.out.println("\t12. Logout");
            System.out.println("===================================");
            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();
            UserType userType = UserType.PATIENT;

            try {
                switch (choice) {
                    case 1 -> displayPatientInfo(patient);  // View Medical Record
                    case 2 -> updatePersonalInfo(patient);  // Update Personal Information
                    case 3 -> displayAvailableAppointmentSlots();  // View Available Appointment Slots
                    case 4 -> scheduleAppointment(patient);  // Schedule an Appointment
                    case 5 -> rescheduleAppointment(patient);  // Reschedule an Appointment
                    case 6 -> cancelAppointment(patient);  // Cancel an Appointment
                    case 7 -> viewScheduledAppointments(patient);  // View Scheduled Appointments
                    case 8 -> displayPastAppointmentRecords(patient);  // View Past Appointment Outcome Records
                    case 9 -> UserProfileDisplay.viewUserProfilePage(patient, userType);  // View My Profile
                    case 10 -> UserProfileDisplay.updateUserProfile(patient, userType);  // Update My Profile
                    case 11 -> ChangePasswordDisplay.changePassword(patient, userType);  // Change My Password
                    case 12 -> LogoutDisplay.logout();  // Logout
                    default -> System.out.println("Invalid option, please try again.");
                }
            } catch (PageBackException e) {
                patientDisplay(user);  
            }
        } else {
            throw new IllegalArgumentException("User is not a Patient.");
        }
    }

    // Display Medical Record and Detailed Information for a Patient
    private static void displayPatientInfo(Patient patient) {
        String fourColBorder = "+--------------------------------------+----------------------+------------------------------+";

        System.out.println("Medical Record of Patient with ID: " + patient.getModelID());
        System.out.println("--------------------------------------------");
        System.out.println("Personal Information");
        patient.getPersonalInfo().displayPersonalInfo();
        System.out.println("--------------------------------------------");

        System.out.println("Medical Records");
        System.out.println("Allergies: " + patient.getAllergies());
        System.out.println("Blood Type: " + patient.getBloodType());

        System.out.println(fourColBorder);
        System.out.printf("| %-90s |%n", "Diagnosis");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s |%n", "ID", "Doctor", "Diagnosis",
                "Prescriptions", "Date of Diagnosis");

        List<Diagnosis> diagnoses = patient.getDiagnosis();
        for (Diagnosis diagnosis : diagnoses) {
            System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s |%n", diagnosis.getDiagnosisID(),
                    diagnosis.getDoctorID(), diagnosis.getDisease(), diagnosis.getPrescription(),
                    diagnosis.getDateOfDiagnosis());
            System.out.println(fourColBorder);
        }

        System.out.println("\nAppointments:");
        List<Appointment> appointments = patient.getAppointments();
        for (Appointment appointment : appointments) {
            Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
            System.out.printf("| %-36s | %-20s | %-10s | %-20s | %-15s |%n", appointment.getAppointmentID(),
                    appointment.getTimeSlot().getDate(), appointment.getTimeSlot().getTime(),
                    doctor.getName(), appointment.getAppointmentStatus());
        }
        System.out.println(fourColBorder);
    }

    // Update Personal Information of the Patient
    private static void updatePersonalInfo(Patient patient) {
        System.out.print("Enter new email: ");
        String newEmail = CustScanner.getStrChoice();
        System.out.print("Enter new contact number: ");
        String newContact = CustScanner.getStrChoice();

        patient.setEmail(newEmail);
        patient.setContactInfo(newContact);
        PatientManager.updateUser(patient);  // Use PatientManager to save changes
        System.out.println("Personal information updated successfully.");
        EnterToGoBackDisplay.display();
    }

    // Display Available Appointment Slots with Calendar
    private static void displayAvailableAppointmentSlots() {
        ClearDisplay.ClearConsole();
        System.out.println("Available Appointment Slots:");
        
        // Display calendar for slot navigation if needed
        CalendarDisplay.calendarDisplay();
        
        // Display available slots per `AppointmentDisplay`
        AppointmentDisplay.timeSlotDisplay();
        EnterToGoBackDisplay.display();
    }

    // Schedule an Appointment for the Patient
    private static void scheduleAppointment(Patient patient) {
        ClearDisplay.ClearConsole();
        System.out.println("Scheduling an Appointment");

        System.out.print("Enter Doctor ID to schedule with: ");
        String doctorID = CustScanner.getStrChoice();
        Doctor doctor = DoctorManager.getDoctorByID(doctorID);

        if (doctor == null) {
            System.out.println("Invalid Doctor ID. Please try again.");
            EnterToGoBackDisplay.display();
            return;
        }

        AppointmentDisplay.timeSlotDisplay();  // Show available slots for the selected doctor
        System.out.print("Enter time slot number: ");
        int timeSlotNumber = CustScanner.getIntChoice();

        Appointment appointment = AppointmentManager.createAppointment(patient, doctor, timeSlotNumber);
        if (appointment != null) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Failed to schedule appointment.");
        }
        EnterToGoBackDisplay.display();
    }

    // Reschedule an Existing Appointment
    private static void rescheduleAppointment(Patient patient) {
        ClearDisplay.ClearConsole();
        System.out.println("Rescheduling an Appointment");

        viewScheduledAppointments(patient);

        System.out.print("Enter the appointment ID to reschedule: ");
        String appointmentID = CustScanner.getStrChoice();
        Appointment appointment = AppointmentManager.getAppointmentByID(patient.getModelID(), appointmentID);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            EnterToGoBackDisplay.display();
            return;
        }

        AppointmentDisplay.timeSlotDisplay();
        System.out.print("Enter new time slot number: ");
        int newTimeSlotNumber = CustScanner.getIntChoice();

        boolean success = AppointmentManager.rescheduleAppointment(appointment, newTimeSlotNumber);
        System.out.println(success ? "Appointment rescheduled successfully." : "Failed to reschedule appointment.");
        EnterToGoBackDisplay.display();
    }

    // Cancel an Appointment
    private static void cancelAppointment(Patient patient) {
        ClearDisplay.ClearConsole();
        System.out.println("Cancelling an Appointment");

        viewScheduledAppointments(patient);

        System.out.print("Enter the appointment ID to cancel: ");
        String appointmentID = CustScanner.getStrChoice();
        boolean success = AppointmentManager.cancelAppointment(appointmentID);

        System.out.println(success ? "Appointment cancelled successfully." : "Failed to cancel appointment.");
        EnterToGoBackDisplay.display();
    }

    // View All Scheduled Appointments
    private static void viewScheduledAppointments(Patient patient) {
        ClearDisplay.ClearConsole();
        System.out.println("Scheduled Appointments:");
        AppointmentDisplay.viewScheduledAppointments(patient.getAppointments());  // Assumes this displays appointments
        EnterToGoBackDisplay.display();
    }

    // Display Past Appointment Outcome Records
    private static void displayPastAppointmentRecords(Patient patient) {
        ClearDisplay.ClearConsole();
        System.out.println("Past Appointment Outcome Records:");
        AppointmentDisplay.upcomingAppointmentsDisplay(patient.getAppointments());  // Assumes this method displays past appointments
        EnterToGoBackDisplay.display();
    }

    public static void updatePatientMedicalRecord(Patient patient, Doctor doctor) throws PageBackException {
        displayPatientInfo(patient);
        System.out.println();

        System.out.println("What would you like to update?");
        System.out.println("\t1. Add allergies");
        System.out.println("\t2. Add new diagnosis");
        System.out.println("\t3. Update diagnosis");
        System.out.println("\t4. Create new appointment");
        System.out.println("\t5. Update appointment");
        System.out.println("\t6. Delete appointment");
        System.out.println("\t7. Go back");

        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

        switch (choice) {
            case 1 -> addAllergyDisplay(patient);
            case 2 -> DiagnosisDisplay.addDiagnosisDisplay(patient, doctor);
            case 3 -> updateDiagnosis(patient);
            case 4 -> scheduleAppointment(patient);
            case 5 -> rescheduleAppointment(patient);
            case 6 -> cancelAppointment(patient);
            case 7 -> throw new PageBackException();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addAllergyDisplay(Patient patient) throws PageBackException {
        System.out.println("Add Allergy");
        List<String> allergies = patient.getAllergies();
        allergies.forEach(allergy -> System.out.println("\t" + allergy));

        while (true) {
            System.out.print("Enter the allergy to add: ");
            String allergy = CustScanner.getStrChoice();
            PatientManager.addAllergy(patient, allergy);
            System.out.println("Allergy added successfully.");

            System.out.print("Add another allergy? (Y/N): ");
            if (CustScanner.getStrChoice().equalsIgnoreCase("N")) break;
        }
        EnterToGoBackDisplay.display();
    }

    private static void updateDiagnosis(Patient patient) {
        System.out.print("Enter the Diagnosis ID to update: ");
        String diagnosisID = CustScanner.getStrChoice();
        Diagnosis diagnosis = PatientManager.getDiagnosisByID(patient, diagnosisID);

        if (diagnosis == null) {
            System.out.println("Diagnosis not found.");
            return;
        }

        System.out.print("Enter new details for the diagnosis: ");
        String newDetails = CustScanner.getStrChoice();
        diagnosis.setDisease(newDetails);
        PatientManager.updateDiagnosis(diagnosisID, patient.getModelID(), diagnosis);
        System.out.println("Diagnosis updated successfully.");
    }
}
