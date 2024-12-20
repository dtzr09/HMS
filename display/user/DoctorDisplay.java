package display.user;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import controller.appointment.AppointmentManager;
import controller.appointment.AppointmentOutcomeManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import controller.user.UserManager;
import display.appointment.AppointmentDisplay;
import display.medicalRecords.PrescriptionDisplay;
import display.medicalRecords.enums.MedicalRecordsActions;
import display.password.ChangePasswordDisplay;
import display.session.ClearDisplay;
import display.session.EnterToGoBackDisplay;
import display.session.LogoutDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.diagnosis.Diagnosis;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

/**
 * The {@code DoctorDisplay} class provides an interface for doctors in the
 * Hospital Management System. It allows doctors to manage their patients,
 * view and update patient medical records, set appointment availability,
 * manage appointment requests, and record appointment outcomes.
 */
public class DoctorDisplay {
    /**
     * Displays the main page for the doctor, listing the available options
     * for managing patients, appointments, and profile settings.
     * 
     * @param user the logged-in user, which must be of type {@code Doctor}.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    public static void doctorDisplay(User user) throws PageBackException {
        ClearDisplay.ClearConsole();
        if (user instanceof Doctor doctor) {
            System.out.println("===================================");
            System.out.println("Welcome to Doctor Main Page");
            System.out.println("Hello, " + doctor.getName() + "!");
            System.out.println();
            System.out.println("\t1. View my patients");
            System.out.println("\t2. View patient medical record");
            System.out.println("\t3. Update patient medical record");
            System.out.println("\t4. Set availability for appointments");
            System.out.println("\t5. Manage Appointment Requests");
            System.out.println("\t6. View Upcoming Appointments");
            System.out.println("\t7. Record Appointment Outcome ");
            System.out.println("\t8. View my profile");
            System.out.println("\t9. Update my profile");
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");
            System.out.println();
            System.out.println("===================================");

            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();
            UserType userType = UserType.DOCTOR;

            try {
                switch (choice) {
                    case 1 -> displayMyPatients(doctor);
                    case 2 -> displayPatientsMenu(doctor, MedicalRecordsActions.VIEW);
                    case 3 -> displayPatientsMenu(doctor, MedicalRecordsActions.UPDATE);
                    case 4 -> appointmentAvailabilityDisplay(doctor);
                    case 5 -> AppointmentDisplay.appointmentRequestsDisplay(doctor);
                    case 6 -> viewUpcomingAppointments(doctor);
                    case 7 -> recordAppointmentOutcome(doctor);
                    case 8 -> UserProfileDisplay.viewUserProfilePage(doctor, userType);
                    case 9 -> UserProfileDisplay.updateUserProfile(doctor, userType);
                    case 10 -> ChangePasswordDisplay.changePassword(doctor, userType);
                    case 11 -> LogoutDisplay.logout();
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        doctorDisplay(user);
                    }
                }
            } catch (PageBackException e) {
                doctorDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not an doctor.");
        }

    }

    /**
     * Displays the interface for setting the doctor's availability for
     * appointments.
     * 
     * @param doctor the logged-in doctor.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void appointmentAvailabilityDisplay(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Set Availability for Appointments");
        System.out.println("----------------------------------");

        Map<String, List<String>> currentAvailability = doctor.getAppointmentAvailability();
        if (currentAvailability == null || currentAvailability.isEmpty()) {
            System.out.printf(
                    "You have not set your availability for appointments. Would you like to set it now? [Y/N] ");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("Y")) {
                Map<String, List<String>> newAvailabilities = AppointmentDisplay
                        .setAppointmentAvailabilityDisplay(doctor);
                try {
                    DoctorManager.setAppointmentAvailability(doctor, newAvailabilities);
                    System.out.println();
                    System.out.println("You have successfully set your availability for appointments.");
                    System.out.println();
                } catch (Exception e) {
                    System.out.println("Error setting availability. Please try again later.");
                    System.out.println();
                    EnterToGoBackDisplay.display();
                }
            }

            EnterToGoBackDisplay.display();
        } else {
            AppointmentDisplay.displayAppointmentAvailability(doctor,
                    currentAvailability);
        }
    }

    /**
     * Displays the list of patients assigned to the doctor.
     * 
     * @param doctor the logged-in doctor.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void displayMyPatients(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        List<Patient> patients = PatientManager.getPatientsOfDoctor(doctor.getModelID());

        PatientDisplay.displayPatients(patients);
        System.out.println();

        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the menu to view or update the medical records of the patients.
     * 
     * @param doctor  the logged-in doctor.
     * @param actions the type of action (VIEW or UPDATE) to perform on the medical
     *                records.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void displayPatientsMenu(Doctor doctor, MedicalRecordsActions actions) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("These are your current patients.");
        System.out.println();

        List<Patient> patients = PatientManager.getPatientsOfDoctor(doctor.getModelID());

        PatientDisplay.displayPatients(patients);
        System.out.println();

        if (patients.isEmpty()) {
            EnterToGoBackDisplay.display();
        }

        System.out
                .printf("Please enter the ID of the patient you would like to " + actions.toString().toLowerCase()
                        + " the medical record of. ");

        String patientID = CustScanner.getStrChoice();
        Patient patient = null;
        try {
            patient = PatientManager.getPatientById(patientID);
        } catch (Exception e) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        if (patient == null) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        switch (actions) {
            case MedicalRecordsActions.VIEW -> {
                viewPatientMedicalRecord(patient);
                EnterToGoBackDisplay.display();
            }
            case MedicalRecordsActions.UPDATE -> {
                PatientDisplay.updatePatientMedicalRecord(patient, doctor);
                EnterToGoBackDisplay.display();
            }
            default -> {
                System.out.println("Invalid action.");
                throw new PageBackException();
            }
        }

    }

    /**
     * Displays the medical record of a specific patient.
     * 
     * @param patient the patient whose medical record is to be viewed.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void viewPatientMedicalRecord(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        PatientDisplay.displayPatientInfo(patient);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the upcoming appointments for the doctor.
     * 
     * @param doctor the logged-in doctor.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void viewUpcomingAppointments(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        List<Appointment> upcomingAppointments = AppointmentManager.getScheduledDoctorAppointments(doctor.getModelID());
        if (upcomingAppointments == null || upcomingAppointments.isEmpty() || upcomingAppointments.size() == 0) {
            System.out.println("No upcoming appointments found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }
        AppointmentDisplay.upcomingAppointmentsDisplay(upcomingAppointments);

    }

    /**
     * Prompts the doctor to record the outcome of an appointment, including
     * type of service, consultation notes, and prescription details.
     * 
     * @param doctor        the logged-in doctor.
     * @param appointmentID the ID of the appointment to record the outcome for.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void displayRecordAppointmentOutcomePrompts(Doctor doctor, String appointmentID)
            throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Record Appointment Outcome");
        System.out.println("----------------------------------");
        System.out.println();

        Appointment appointment = AppointmentManager.getAppointmentByID(appointmentID);
        if (appointment == null) {
            System.out.println("Appointment not found.");
            EnterToGoBackDisplay.display();
        }
        Patient patient = null;
        try {
            patient = PatientManager.getPatientById(appointment.getPatientID());
        } catch (Exception e) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        if (patient == null) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        AppointmentOutcome appointmentOutcome = AppointmentOutcomeManager
                .getAppointmentOutcomeByAppointmentID(appointmentID);
        if (appointmentOutcome == null) {
            System.out.println("Appointment Outcome not found");
            EnterToGoBackDisplay.display();
        }

        System.out.printf("Please enter the type of service provided. ");
        String typeOfService = CustScanner.getStrChoice();
        if (typeOfService.isBlank() || typeOfService == "") {
            System.out.println("Type of service cannot be empty.");
            EnterToGoBackDisplay.display();
        }

        System.out.printf("Please enter the consultation notes. ");
        String consultationNotes = CustScanner.getStrChoice();
        if (consultationNotes.isBlank() || consultationNotes == "") {
            System.out.println("Consultation notes cannot be empty.");
            EnterToGoBackDisplay.display();
        }

        System.out.printf("Please enter the disease. ");
        String disease = CustScanner.getStrChoice();
        if (disease.isBlank() || disease == "") {
            System.out.println("Disease cannot be empty.");
            EnterToGoBackDisplay.display();
        }

        System.out.println();

        String prescriptionID = UUID.randomUUID().toString();
        try {
            PrescriptionDisplay.displayAddNewPresciption(patient, doctor, prescriptionID);
            String diagnosisID = UUID.randomUUID().toString();
            Diagnosis diagnosis = new Diagnosis(
                    diagnosisID, disease, doctor.getModelID(), prescriptionID, appointment.getDateOfAppointment(),
                    patient.getPatientID());
            AppointmentOutcomeManager.updateAppointmentOutcome(appointmentOutcome, diagnosis, typeOfService,
                    diagnosisID,
                    consultationNotes);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not added. Please try again later");
            EnterToGoBackDisplay.display();
        }

        System.out.println();
        System.out.println("Appointment Outcome added successfully.");
        System.out.println();
        EnterToGoBackDisplay.display();

    }

    /**
     * Displays the menu to record the outcome of pending appointments for the
     * doctor.
     * 
     * @param doctor the logged-in doctor.
     * @throws PageBackException if the user chooses to go back to the previous
     *                           page.
     */
    private static void recordAppointmentOutcome(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Record Appointment Outcome");
        System.out.println("----------------------------------");

        List<Appointment> appointments = AppointmentManager.getScheduledDoctorAppointments(doctor.getModelID());

        if (appointments == null || appointments.isEmpty() || appointments.size() == 0) {
            System.out.println("No appointments found.");
            EnterToGoBackDisplay.display();
        }

        List<Appointment> pendingAppointments = null;
        pendingAppointments = AppointmentManager.getAppointmentWithIncompleteOutcome(appointments,
                doctor.getModelID());
        if (pendingAppointments == null || pendingAppointments.isEmpty() || pendingAppointments.size() == 0) {
            System.out.println("No pending appointments found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }

        AppointmentDisplay.viewAppointments(pendingAppointments);
        System.out.printf("Please enter the ID of the appointment you would like to record the outcome of. ");
        String appointmentID = CustScanner.getStrChoice();
        System.out.println();

        displayRecordAppointmentOutcomePrompts(doctor, appointmentID);
    }

    /**
     * Displays a table of all doctors with their details, including ID, name,
     * email, and the number of appointments.
     */
    public static void displayDoctorAppointments() {
        String fourColBorder = "+--------------------------------------+----------------------+------------------------------+------------------------+";
        List<Doctor> doctors = UserManager.getDoctors();
        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        System.out.println(fourColBorder);
        System.out.printf("| %-115s |%n", "Doctors");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-28s | %-22s |%n", "ID", "Name", "Email", "No. of Appointments");
        System.out.println(fourColBorder);
        for (Doctor doctor : doctors) {
            int noOfAppointments = AppointmentManager.getAllDoctorAppointments(doctor.getModelID()).size();
            System.out.printf("| %-36s | %-20s | %-28s | %-22s |%n", doctor.getModelID(), doctor.getName(),
                    doctor.getEmail(), noOfAppointments);
        }
        System.out.println(fourColBorder);
    }
}
