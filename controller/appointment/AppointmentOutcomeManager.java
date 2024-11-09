package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.medication.DiagnosisManager;
import database.appointment.AppointmentOutcomeDatabase;
import model.appointment.AppointmentOutcome;
import model.diagnosis.Diagnosis;

public class AppointmentOutcomeManager {

    public static void getAppointmentOutcomeByID(String appointmentOutcomeID) {
        try {
            AppointmentOutcomeDatabase.getDB().getByID(appointmentOutcomeID);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not found.");
        }
    }

    public static void createNewAppointmentOutcome(AppointmentOutcome appointmentOutcome) {
        try {
            AppointmentOutcomeDatabase.getDB().add(appointmentOutcome);
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

}
