package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import database.appointment.AppointmentOutcomeDatabase;
import display.EnterToGoBackDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.appointment.AppointmentOutcomeRecord;
import model.appointment.enums.AppointmentOutcomeStatus;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import utils.exceptions.PageBackException;

public class AppointmentOutcomeManager {

    /**
     * Create new appointment outcome
     * 
     * @param appointmentOutcome
     */
    public static void createNewAppointmentOutcome(AppointmentOutcome appointmentOutcome) {
        try {
            AppointmentOutcomeDatabase.getDB().add(appointmentOutcome);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not added.");
        }
    }

    /**
     * Update appointment outcome
     * 
     * @param appointmentOutcome
     * @param diagnosis
     * @param typeOfService
     * @param diagnosisID
     * @param consultationNotes
     */
    public static void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome, Diagnosis diagnosis,
            String typeOfService, String diagnosisID, String consultationNotes) {
        try {
            appointmentOutcome.setTypeOfService(typeOfService);
            appointmentOutcome.setDiagnosisID(diagnosisID);
            appointmentOutcome.setConsultationNotes(consultationNotes);
            appointmentOutcome.setStatus(AppointmentOutcomeStatus.COMPLETED);
            AppointmentOutcomeDatabase.getDB().update(appointmentOutcome);
            DiagnosisManager.addDiagnosis(diagnosis);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Appointment Outcome not updated.");
        }
    }

    /**
     * Get all appointment outcomes
     * 
     * @return List<AppointmentOutcome>
     */
    public static List<AppointmentOutcome> getAllAppointmentOutcome() {
        return AppointmentOutcomeDatabase.getDB().getAllAppointmentOutcomes();
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
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get appointment outcome by patient ID
     * 
     * @param patientID
     * @return List<AppointmentOutcome>
     */
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

    /**
     * Get appointment outcome records
     * 
     * @param recordList
     * @param patientID
     * @return List<AppointmentOutcomeRecord>
     * @throws PageBackException
     */
    public static List<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(List<AppointmentOutcome> recordList,
            String patientID) throws PageBackException {
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
                EnterToGoBackDisplay.display();
            }
        }
        return records;
    }

    /**
     * Get appointment outcome by doctor ID
     * 
     * @param doctorID
     * @return List<AppointmentOutcome>
     */
    public static ArrayList<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String doctorID) {
        ArrayList<AppointmentOutcome> doctorAppointmentOutcome = new ArrayList<>();
        List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcome();
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getDoctorID().equals(doctorID)) {
                doctorAppointmentOutcome.add(outcome);
            }
        }
        return doctorAppointmentOutcome;
    }

    /**
     * Get number of appointment outcome by patient ID
     * 
     * @param patientID
     * @return int
     */
    public static int getNumberOfAppointmentOutcomeByPatientID(String patientID) {
        List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcome();
        int count = 0;
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getPatientID().equals(patientID)
                    && outcome.getStatus().equals(AppointmentOutcomeStatus.COMPLETED)) {
                count++;
            }
        }
        return count;
    }
}
