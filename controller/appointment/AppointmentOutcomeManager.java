package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import database.appointment.AppointmentOutcomeDatabase;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.appointment.AppointmentOutcomeRecord;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;

public class AppointmentOutcomeManager {

    public static void getAppointmentOutcomeByID(String appointmentOutcomeID) {
        try {
            AppointmentOutcomeDatabase.getDB().getByID(appointmentOutcomeID);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not found.");
        }
    }

    public static void createNewAppointmentOutcome(AppointmentOutcome appointmentOutcome, Diagnosis diagnosis) {
        try {
            AppointmentOutcomeDatabase.getDB().add(appointmentOutcome);
            DiagnosisManager.addDiagnosis(diagnosis);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not added.");
        }
    }

    public static void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome, Diagnosis diagnosis) {
        try {
            AppointmentOutcomeDatabase.getDB().update(appointmentOutcome);
            DiagnosisManager.addDiagnosis(diagnosis);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not updated.");
        }
    }

    public static List<AppointmentOutcome> getAllAppointmentOutcome() {
        try {
            return AppointmentOutcomeDatabase.getDB().getAllAppointmentOutcomes();
        } catch (Exception e) {
            return null;
        }
    }

    public static AppointmentOutcome getAppointmentOutcomeByAppointmentID(String appointmentID) {
        try {
            AppointmentOutcome outcome = null;
            List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcome();
            for (AppointmentOutcome apt : appointmentOutcomes) {
                outcome = apt.getAppointmentID().equals(appointmentID) ? apt : null;
            }
            return outcome;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<AppointmentOutcome> getPatientsAppointmentOutcomeRecords(String patientID) {
        ArrayList<AppointmentOutcome> recordList = new ArrayList<>();

        try {
            List<AppointmentOutcome> outcomes = getAllAppointmentOutcome();
            for (AppointmentOutcome outcome : outcomes) {
                if (outcome.getPatientID().equals(patientID)) {
                    recordList.add(outcome);
                }
            }
            return recordList;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(List<AppointmentOutcome> recordList,
            String patientID) {
        ArrayList<AppointmentOutcomeRecord> records = new ArrayList<>();
        for (AppointmentOutcome outcome : recordList) {
            try {
                Appointment appointment = AppointmentManager.getAppointmentByID(outcome.getAppointmentID());
                Diagnosis diagnosis = DiagnosisManager.getDiagnosisByPatientIDAndDiagnosisID(patientID,
                        outcome.getDiagnosisID());
                Prescription prescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
                List<String> medicationNames = MedicationManager
                        .getListOfMedicationNamesByIDs(prescription.getMedicationIDs());
                AppointmentOutcomeRecord record = new AppointmentOutcomeRecord(outcome.getAppointmentOutcomeID(),
                        outcome.getTypeOfService(), outcome.getConsultationNotes(), medicationNames,
                        appointment.getDateOfAppointment(), prescription.getPrescriptionStatus());
                records.add(record);
            } catch (Exception e) {
                System.out.println("Error getting appointment outcome records.");
            }
        }
        return records;
    }

}
