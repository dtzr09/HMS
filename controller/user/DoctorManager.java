package controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.appointment.AppointmentStatus;
import database.user.DoctorDatabase;
import model.appointment.Appointment;
import model.user.Doctor;
import model.user.Patient;

public class DoctorManager {
    public static Doctor getDoctorByID(String doctorID) {
        try {
            return DoctorDatabase.getDB().getByID(doctorID);
        } catch (Exception e) {
            System.out.println("Doctor not found.");
        }
        return null;
    };

    public static void addPatientAllergies(Patient patient, String newAllergy) {
        try {
            PatientManager.addAllergy(patient, newAllergy);
        } catch (Exception e) {
            System.out.println("No allergies found.");
        }
    }

    public static List<Appointment> getUpcomingAppointments(Doctor doctor) {
        ArrayList<Appointment> upcomingAppointments = new ArrayList<Appointment>();
        try {
            List<Appointment> appointments = doctor.getAppointments();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentStatus().equals(AppointmentStatus.SCHEDULED))
                    upcomingAppointments.add(appointment);
            }
            return upcomingAppointments;

        } catch (Exception e) {
            return null;
        }
    }

    public static void setAppointmentAvailability(Doctor doctor, Map<String, List<String>> appointmentAvailability) {
        doctor.setAppointmentAvailability(appointmentAvailability);
        try {
            UserManager.updateUser(doctor);
        } catch (Exception e) {
            System.out.println("Error updating appointment availability.");
        }
    }

}
