package controller.appointment;

import database.appointment.AppointmentOutcomeDatabase;
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

}
