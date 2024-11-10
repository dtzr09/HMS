package display.medication;

import java.util.List;
import java.util.UUID;

import controller.medication.DiagnosisManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import model.diagnosis.Diagnosis;
import model.diagnosis.DiagnosisRecord;
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
        System.out.println();

        String prescriptionID = UUID.randomUUID().toString();
        PrescriptionDisplay.displayAddNewPresciption(patient, doctor, prescriptionID);
        try {
            DiagnosisManager.createNewDiagnosis(diagnosis, patient.getPatientID(), patient.getDoctorID(),
                    prescriptionID);
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
                "Medication", "Date of Diagnosis");
        System.out.println(fourColBorder);
        try {
            List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patient.getPatientID());
            if (diagnoses.size() == 0 || diagnoses == null) {
                System.out.printf("| %115s | %n", "No diagnosis found. ");
            } else {
                List<DiagnosisRecord> diagnosisRecords = DiagnosisManager
                        .getDiagnosisOutcomeRecordList(diagnoses, doctor);

                for (DiagnosisRecord record : diagnosisRecords) {
                    System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n", record.getDiagnosisID(),
                            record.getDisease(), record.getDoctorName(), String.join(",", record.getMedicationNames()),
                            record.getDateOfDiagnosis());
                }

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
        try {
            Diagnosis diagnosis = DiagnosisManager.getDiagnosisByPatientIDAndDiagnosisID(patient.getPatientID(),
                    diagnosisId);
            if (diagnosis == null) {
                System.out.println("Diagnosis not found.");
                throw new PageBackException();
            }
            DiagnosisRecord record = DiagnosisManager.getAPatientDiagnosisRecord(diagnosis, doctor);
            System.out.println("Diagnosis of " + patient.getName());
            System.out.println("--------------------------------------------");
            System.out.println("Diagnosis ID: " + record.getDiagnosisID());
            System.out.println("Diagnosis: " + record.getDisease());
            System.out.println("Doctor: " + record.getDoctorName());
            System.out.println("Medications: " + String.join(",", record.getMedicationNames()));
            System.out.println("Date of Diagnosis: " + record.getDateOfDiagnosis());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Diagnosis not found.");
            throw new PageBackException();
        }

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

    public static void displayAllDiagnosisOfPatient(Patient patient) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+-------------------+";
        System.out.println(fourColBorder);
        System.out.printf("| %-120s | %n", " " + "Diagnosis history of " + patient.getName());
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-15s |%n", "ID", "Diagnosis", "Doctor Name",
                "Medications", "Date of Diagnosis");
        System.out.println(fourColBorder);
        try {
            List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patient.getPatientID());
            if (diagnoses.size() == 0 || diagnoses == null) {
                System.out.printf("| %115s | %n", "No diagnosis found. ");
            }
            for (Diagnosis diagnosis : diagnoses) {
                String doctorID = diagnosis.getDoctorID();
                Doctor doctor = null;
                if (doctorID != null) {
                    doctor = DoctorManager.getDoctorByID(doctorID);
                }

                DiagnosisRecord record = DiagnosisManager.getAPatientDiagnosisRecord(diagnosis, doctor);
                System.out.printf("| %-36s | %-20s | %-15s | %-20s | %-17s |%n",
                        record.getDiagnosisID(), record.getDisease(),
                        record.getDoctorName(), String.join(",", record.getMedicationNames()),
                        record.getDateOfDiagnosis());
            }
            System.out.println(fourColBorder);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("| No diagnosis found.");
            System.out.println(fourColBorder);
            EnterToGoBackDisplay.display();
        }
    }

}
