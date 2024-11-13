package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import database.appointment.AppointmentOutcomeDatabase;
import display.session.EnterToGoBackDisplay;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.appointment.AppointmentOutcomeRecord;
import model.appointment.enums.AppointmentOutcomeStatus;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import utils.exceptions.PageBackException;
/**
 * The AppointmentOutcomeManager class provides utility methods for managing Appointment Outcomes such as recording and retrieving.
 */

public class AppointmentOutcomeManager {

    /**
     * Creates a new appointment outcome and adds it to the database.
     * 
     * This method takes an `AppointmentOutcome` object, attempts to add it to the `AppointmentOutcomeDatabase`, 
     * and handles any exceptions that may arise during the process. If the outcome is successfully added, it 
     * remains stored in the database for future reference. If an error occurs during the operation, an error 
     * message is displayed.
     * 
     * @param appointmentOutcome The `AppointmentOutcome` object to be added to the database.
     */
    public static void createNewAppointmentOutcome(AppointmentOutcome appointmentOutcome) {
        try {
            AppointmentOutcomeDatabase.getDB().add(appointmentOutcome);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not added.");
        }
    }

    /**
     * Updates the details of an existing appointment outcome and adds a diagnosis.
     * 
     * This method updates the given `AppointmentOutcome` object by setting the type of service, 
     * diagnosis ID, and consultation notes. The status of the appointment outcome is then updated 
     * to "COMPLETED". The updated outcome is stored in the `AppointmentOutcomeDatabase`, and the 
     * provided diagnosis is added to the `DiagnosisManager`.
     * 
     * If any error occurs during the update process, the exception is caught, and an error message 
     * is printed to the console.
     * 
     * @param appointmentOutcome The `AppointmentOutcome` object to be updated.
     * @param diagnosis The `Diagnosis` object to be added to the system.
     * @param typeOfService The type of medical service provided during the appointment.
     * @param diagnosisID The ID of the diagnosis associated with the appointment.
     * @param consultationNotes Notes regarding the consultation or findings during the appointment.
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
     * Retrieves all appointment outcomes from the database.
     * 
     * This method fetches and returns a list of all `AppointmentOutcome` objects stored in the 
     * `AppointmentOutcomeDatabase`. The list contains the details of all outcomes recorded for 
     * appointments.
     * 
     * @return A list of all `AppointmentOutcome` objects in the database.
     */
    public static List<AppointmentOutcome> getAllAppointmentOutcome() {
        return AppointmentOutcomeDatabase.getDB().getAllAppointmentOutcomes();
    }
    /**
     * Retrieves the appointment outcome for a given appointment ID.
     * 
     * This method searches through all appointment outcomes in the database to find the outcome 
     * associated with the provided appointment ID. If the outcome is found, it is returned; 
     * otherwise, the method returns null.
     * 
     * @param appointmentID The ID of the appointment whose outcome is to be retrieved.
     * @return The `AppointmentOutcome` object associated with the given appointment ID, or null 
     *         if no such outcome is found.
     */
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
     * Retrieves all appointment outcome records for a given patient.
     * 
     * This method filters and returns a list of all `AppointmentOutcome` objects that are associated 
     * with the specified patient ID. It searches through all appointment outcomes and adds those 
     * matching the given patient ID to the result list.
     * 
     * @param patientID The ID of the patient whose appointment outcomes are to be retrieved.
     * @return A list of `AppointmentOutcome` objects associated with the given patient ID, 
     *         or null if an error occurs during the process.
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
     * Retrieves a list of appointment outcome records for a given list of appointment outcomes and patient ID.
     * 
     * This method processes a list of `AppointmentOutcome` objects, fetches the corresponding appointment, diagnosis,
     * and prescription details, and compiles this information into a list of `AppointmentOutcomeRecord` objects. Each record
     * contains detailed information about the appointment outcome, including type of service, consultation notes, 
     * medication names, and prescription status.
     * 
     * @param recordList The list of `AppointmentOutcome` objects that represent the outcomes of various appointments.
     * @param patientID The ID of the patient whose appointment outcomes are being processed.
     * @return A list of `AppointmentOutcomeRecord` objects, each representing the details of an appointment outcome.
     * @throws PageBackException If there is an error in fetching related data or processing any of the outcomes.
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
     * Retrieves a list of appointment outcomes for a specific doctor.
     * 
     * This method filters the list of all appointment outcomes and returns only those that are associated with the given doctor ID.
     * The outcomes returned represent the results of appointments attended by the specified doctor.
     * 
     * @param doctorID The ID of the doctor whose appointment outcomes are to be retrieved.
     * @return A list of `AppointmentOutcome` objects that belong to the specified doctor.
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
     * Retrieves the number of completed appointment outcomes for a specific patient.
     * 
     * This method iterates through all appointment outcomes and counts how many are associated
     * with the given patient ID and have a status of "COMPLETED".
     * 
     * @param patientID The ID of the patient whose completed appointment outcomes are to be counted.
     * @return The number of completed appointment outcomes for the specified patient.
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
