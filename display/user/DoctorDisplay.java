package display.user;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import controller.appointment.AppointmentManager;
import controller.appointment.AppointmentOutcomeManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.appointment.AppointmentDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import display.medication.PrescriptionDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class DoctorDisplay {
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

    private static void appointmentAvailabilityDisplay(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Set Availability for Appointments");
        System.out.println("----------------------------------");

        Map<String, List<String>> currentAvailability = doctor.getAppointmentAvailability();
        if (currentAvailability == null) {
            System.out.printf(
                    "You have not set your availability for appointments. Would you like to set it now? [Y/N] ");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("Y")) {
                Map<String, List<String>> newAvailabilities = AppointmentDisplay.setAppointmentAvailability(doctor);
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
            AppointmentDisplay.displayAppointmentAvailability(doctor, currentAvailability);
        }
    }

    private static void displayMyPatients(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        List<Patient> patients = PatientManager.getPatientsOfDoctor(doctor.getModelID());

        PatientDisplay.displayPatients(patients);
        System.out.println();

        EnterToGoBackDisplay.display();
    }

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

        System.out.println("Please enter the ID of the patient you would like to " + actions.toString().toLowerCase()
                + " the medical record of.");

        String patientID = CustScanner.getStrChoice();

        Patient patient = PatientManager.getPatientById(patientID);

        if (patient == null) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        switch (actions) {
            case MedicalRecordsActions.VIEW -> viewPatientMedicalRecord(patient);
            case MedicalRecordsActions.UPDATE -> PatientDisplay.updatePatientMedicalRecord(patient, doctor);
            default -> {
                System.out.println("Invalid action.");
                throw new PageBackException();
            }
        }
    }

    private static void viewPatientMedicalRecord(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        PatientDisplay.displayPatientInfo(patient);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    private static void viewUpcomingAppointments(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        List<Appointment> upcomingAppointments = AppointmentManager.getDoctorAppointments(doctor.getModelID());
        if (upcomingAppointments == null) {
            System.out.println("No upcoming appointments found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }

        AppointmentDisplay.upcomingAppointmentsDisplay(upcomingAppointments);
    }

    private static void displayRecordAppointmentOutcomePrompts(Doctor doctor, String appointmentID, String patientID)
            throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Record Appointment Outcome");
        System.out.println("----------------------------------");

        Patient patient = PatientManager.getPatientById(patientID);

        if (patient == null) {
            System.out.println("Patient not found.");
            EnterToGoBackDisplay.display();
        }

        Appointment appointment = AppointmentManager.getAppointmentByID(patientID, appointmentID);
        if (appointment == null) {
            System.out.println("Appointment not found.");
            EnterToGoBackDisplay.display();
        }

        System.out.println("Please enter the type of service provided.");
        String typeOfService = CustScanner.getStrChoice();

        System.out.println("Please enter the consultation notes.");
        String consultationNotes = CustScanner.getStrChoice();

        String appointmentOutcomeID = UUID.randomUUID().toString();
        Prescription prescription = PrescriptionDisplay.displayAddNewPresciption(patient, doctor);
        try {
            AppointmentOutcomeManager.createNewAppointmentOutcome(new AppointmentOutcome(
                    appointmentOutcomeID, patientID, doctor.getModelID(), typeOfService,
                    consultationNotes,
                    prescription, appointmentID));
        } catch (Exception e) {
            System.out.println("Appointment Outcome not added. Please try again later");
            EnterToGoBackDisplay.display();
        }

        System.out.println("Appointment Outcome added successfully.");
        EnterToGoBackDisplay.display();

    }

    private static void recordAppointmentOutcome(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Record Appointment Outcome");
        System.out.println("----------------------------------");

        List<Appointment> appointments = doctor.getAppointments();

        if (appointments == null) {
            System.out.println("No appointments found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }

        AppointmentDisplay.viewScheduledAppointments(appointments);
        System.out.println("Please enter the ID of the appointment you would like to record the outcome of.");
        String appointmentID = CustScanner.getStrChoice();

        System.out.println("Please enter the ID of the patient for this appointment.");
        String patientID = CustScanner.getStrChoice();

        displayRecordAppointmentOutcomePrompts(doctor, appointmentID, patientID);
    }

}
