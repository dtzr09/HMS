package display.user;

import java.util.List;

import controller.user.DoctorManager;
import display.ClearDisplay;
import display.medication.DiagnosisDisplay;
import model.appointment.Appointment;
import model.diagnosis.Diagnosis;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class PatientDisplay {
    public static void display(User user) {
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
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");

            System.out.println("===================================");
        }

        int choice = 0;
        while (choice < 1 || choice > 12) {
            System.out.println();
            System.out.print("What would you like to do? ");
            choice = CustScanner.getIntChoice();
        }

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

    public static void displayPatientInfo(Patient patient) {
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
        System.out.printf("| %-90s |%n", " " + "Diagnosis");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Doctor", "Diagnosis",
                "Prescriptions", "Date of Diagnosis");
        try {
            List<Diagnosis> diagnoses = patient.getDiagnosis();
            for (Diagnosis diagnosis : diagnoses) {
                System.out.printf("| %-36s | %-25s | %-15s | %-10s | %-20s | %n", diagnosis
                        .getDiagnosisID(),
                        diagnosis.getDoctorID(),
                        diagnosis.getDisease(),
                        diagnosis.getPrescription(),
                        diagnosis.getDateOfDiagnosis());
                System.out.println(fourColBorder);
            }
        } catch (Exception e) {
            System.out.println("No diagnosis found.");
        }
        System.out.println();

        System.out.println(fourColBorder);
        System.out.printf("| %-90s |%n", " " + "Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Date", "Time", "Doctor Name",
                "Status");
        try {
            List<Appointment> appointments = patient.getAppointments();
            for (Appointment appointment : appointments) {
                Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
                System.out.printf("| %-36s | %-25s | %-15s | %-10s | %-20s | %n", appointment
                        .getAppointmentID(),
                        appointment.getTimeSlot().getDate(),
                        appointment.getTimeSlot().getTime(),
                        doctor.getName(),
                        appointment.getAppointmentStatus());
            }
        } catch (Exception e) {
            System.out.println("No appointment found.");
        }
        System.out.println(fourColBorder);
        System.out.println();
        System.out.println("--------------------------------------------");
    }

    public static void updatePatientMedicalRecord(Patient patient, Doctor doctor) throws PageBackException {
        PatientDisplay.displayPatientInfo(patient);
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

        System.out.println("Press Enter to go back.");
        if (CustScanner.getStrChoice().equals(""))
            throw new PageBackException();
    }
}
