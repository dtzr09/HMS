package display.medication;

import java.util.List;

import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class DiagnosisDisplay {

    public static void addDiagnosisDisplay(Patient patient, Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Add Diagnosis");
        System.out.println("--------------------------------------------");
        System.out.printf("Enter the diagnosis of the patient: ");
        String diagnosis = CustScanner.getStrChoice();

        Prescription prescription = PrescriptionDisplay.displayAddNewPresciption(patient, doctor);

        try {
            PatientManager.addDiagnosis(diagnosis, patient.getPatientID(), patient.getDoctorID(), prescription);
            System.out.println("Diagnosis added successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

    private static void displayAllDiagnosisOfPatient(Patient patient, Doctor doctor) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+-----------------+";
        System.out.printf("| %-100s |%n", " " + "Diagnosis history of " + patient.getName());
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n", "ID", "Diagnosis", "Doctor Name",
                "Prescription", "Date of Diagnosis");
        System.out.println(fourColBorder);
        try {
            List<Diagnosis> diagnoses = patient.getDiagnosis();
            for (Diagnosis diagnosis : diagnoses) {
                System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n",
                        diagnosis.getDiagnosisID(), diagnosis.getDisease(),
                        doctor.getName(), diagnosis.getPrescription(), diagnosis.getDateOfDiagnosis());
            }
        } catch (Exception e) {
            System.out.println("No diagnosis found.");
            throw new PageBackException();
        }
    }

    private static void updateDiagnosis(Patient patient, Doctor doctor, String diagnosisId) throws PageBackException {
        System.out.println("Enter the new diagnosis.");
        String diagnosis = CustScanner.getStrChoice();
        try {
            PatientManager.updateDisease(diagnosis, patient.getPatientID(), diagnosisId);
            System.out.println("Diagnosis updated successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

    private static void updateDiagnosisMenu(Patient patient, Doctor doctor, String diagnosisId)
            throws PageBackException {
        displaySingleDiagnosis(patient, doctor, diagnosisId);
        System.out.println();
        System.out.println("\t1. Update diagnosis");
        System.out.println("\t2. Update prescription");
        System.out.println("\t3. Go back");

        System.out.println("What would you like to do?");
        int choice = CustScanner.getIntChoice();

        switch (choice) {
            case 1 -> updateDiagnosis(patient, doctor, diagnosisId);
            case 2 -> PrescriptionDisplay.updatePrescriptionDisplay(patient, doctor, diagnosisId);
            case 3 -> {
                throw new PageBackException();
            }
            default -> {
                System.out.println("Invalid choice. Please try again.");
                throw new PageBackException();
            }
        }
    }

    private static void displaySingleDiagnosis(Patient patient, Doctor doctor, String diagnosisId)
            throws PageBackException {
        Diagnosis diagnosis = null;
        try {
            diagnosis = PatientManager.getDiagnosisByID(patient, diagnosisId);
        } catch (Exception e) {
            System.out.println("Diagnosis not found.");
            throw new PageBackException();
        }
        System.out.println("Diagnosis of " + patient.getName());
        System.out.println("--------------------------------------------");
        System.out.println("Diagnosis ID: " + diagnosis.getDiagnosisID());
        System.out.println("Diagnosis: " + diagnosis.getDisease());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Prescription: " + diagnosis.getPrescription());
        System.out.println("Date of Diagnosis: " + diagnosis.getDateOfDiagnosis());
        System.out.println();
    }

    public static void updateDiagnosisDisplay(Patient patient, Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Update Diagnosis");
        System.out.println("--------------------------------------------");

        displayAllDiagnosisOfPatient(patient, doctor);

        System.out.println("Enter the diagnosis id you would like to update.");
        String diagnosisId = CustScanner.getStrChoice();
        updateDiagnosisMenu(patient, doctor, diagnosisId);
    }

    public static void displayAllDiagnosisOfPatient(Patient patient) {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+-----------------+";
        System.out.printf("| %-100s |%n", " " + "Diagnosis history of " + patient.getName());
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n", "ID", "Diagnosis", "Doctor Name",
                "Prescription", "Date of Diagnosis");
        System.out.println(fourColBorder);
        try {
            List<Diagnosis> diagnoses = patient.getDiagnosis();

            for (Diagnosis diagnosis : diagnoses) {
                String doctorID = diagnosis.getDoctorID();
                Doctor doctor = null;
                if (doctorID != null) {
                    doctor = DoctorManager.getDoctorByID(doctorID);
                }
                System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n",
                        diagnosis.getDiagnosisID(), diagnosis.getDisease(),
                        doctor.getName(), diagnosis.getPrescription(), diagnosis.getDateOfDiagnosis());
            }
        } catch (Exception e) {
            System.out.println("No diagnosis found.");
        }
    }

}
