package controller.appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.user.PatientManager;
import database.appointment.AppointmentOutcomeDatabase;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;

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

    public static void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome) {
        try {
            AppointmentOutcomeDatabase.getDB().update(appointmentOutcome);
        } catch (Exception e) {
            System.out.println("Appointment Outcome not updated.");
        }
    }

    private static List<AppointmentOutcome> getAllAppointmentOutcome() {
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
            List<Appointment> appointments = PatientManager.getAppointmentsOfPatient(patientID);
            if (appointments == null) {
                return null;
            }
            for (Appointment apt : appointments) {
                AppointmentOutcome outcome = getAppointmentOutcomeByAppointmentID(apt.getAppointmentID());
                if (outcome != null) {
                    recordList.add(outcome);
                }
            }
            return recordList;
        } catch (Exception e) {
            return null;
        }
    }

}
