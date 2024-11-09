package display.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.appointment.AppointmentManager;
import controller.appointment.AppointmentOutcomeManager;
import controller.medication.DiagnosisManager;
import controller.medication.PrescriptionManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.Prescription;
import model.user.Patient;
import utils.exceptions.PageBackException;

public class AppointmentOutcomeDisplay {

    public static void viewAppointmentOutcomeRecordsForPharmacist(String patientID) throws PageBackException {
        ClearDisplay.ClearConsole();
        String sevenColBorder = "+--------------------------------------+--------------------+--------------------+--------------------+--------------------+--------------------+";
        List<AppointmentOutcome> recordList = AppointmentOutcomeManager.getPatientsAppointmentOutcomeRecords(patientID);
        if (recordList.isEmpty() || recordList == null) {
            System.out.println();
            System.out.println("No appointment outcome records found for this patient.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }
        Patient patient = PatientManager.getPatientById(patientID);

        System.out.println("Appointment Outcome Records for " + patient.getName());
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(sevenColBorder);
        System.out.printf("| %-36s | %-20s | %-20s | %-20s | %20s | %-20s | %n", "Appointment Outcome ID",
                "Type of Service", "Consultation Notes", "Medications", "Appointment Date",
                "Prescription Status");
        System.out.println(sevenColBorder);
        for (AppointmentOutcome outcome : recordList) {
            try {
                Appointment appointment = AppointmentManager.getAppointmentByID(outcome.getAppointmentID());
                Diagnosis diagnosis = DiagnosisManager.getDiagnosisByPatientIDAndDiagnosisID(patientID,
                        outcome.getDiagnosisID());
                Prescription prescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
                ArrayList<Medication> medication = prescription.getMedication();
                System.out.printf("| %-36s | %-20s | %-20s | %-20s | %20s | %-20s |%n",
                        outcome.getAppointmentOutcomeID(),
                        outcome.getTypeOfService(), outcome.getConsultationNotes(),
                        String.join(", ", medication.toString()),
                        appointment.getDateOfAppointment(), prescription.getPrescriptionStatus());
            } catch (Exception e) {
                System.out.println("Error geting appointment outcome records.");
            }

        }

        System.out.println(sevenColBorder);
        System.out.println();
        EnterToGoBackDisplay.display();

    }

    public static void viewAppointmentOutcomeRecordsForPatient(Patient patient) throws PageBackException {
        String fourcolBorder = "+--------------------------------------+--------------------------------------+--------------------------------------+--------------------------------------+";
        try {
            String patientID = patient.getPatientID();
            List<AppointmentOutcome> recordList = AppointmentOutcomeManager
                    .getPatientsAppointmentOutcomeRecords(patientID);

            if (recordList.isEmpty() || recordList == null) {
                System.out.println("No appointment outcome records found.");
                System.out.println();
                EnterToGoBackDisplay.display();
            }

            List<AppointmentOutcome> outcomes = AppointmentOutcomeManager
                    .getPatientsAppointmentOutcomeRecords(patientID);

            System.out.println("Appointment Outcome Records for " + patient.getName());
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println(fourcolBorder);
            System.out.printf("| %-36s |  %-35s | %-35s | %-35s | %n",
                    "Appointment Outcome ID",
                    "Type of Service", "Consultation Notes", "Medications");
            System.out.println(fourcolBorder);
            for (AppointmentOutcome outcome : outcomes) {
                try {
                    Diagnosis diagnosis = DiagnosisManager.getDiagnosisByPatientIDAndDiagnosisID(patientID,
                            outcome.getDiagnosisID());
                    Prescription prescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
                    ArrayList<Medication> medication = prescription.getMedication();
                    System.out.printf("| %-36s | %-36s | %-36s | %-36s | %-36s | %-36s |%n",
                            outcome.getAppointmentOutcomeID(),
                            outcome.getPatientID(),
                            outcome.getDoctorID(),
                            outcome.getTypeOfService(),
                            outcome.getConsultationNotes(),
                            String.join(", ", medication.toString()),
                            outcome.getAppointmentID());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("| Error getting appointment outcome records.");
                    break;
                }
            }
            System.out.println(fourcolBorder);
            System.out.println();
            EnterToGoBackDisplay.display();
        } catch (Exception e) {
            System.out.println("| No appointment outcome records found.");
            System.out.println(fourcolBorder);
            EnterToGoBackDisplay.display();
        }
    }
}
