package display.appointment;

import java.util.List;

import model.appointment.Appointment;
import model.user.Doctor;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class AppointmentDisplay {
    public static void setAppointmentAvailability() {
    }

    public static void appointmentRequestsDisplay(Doctor doctor) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "Appointment Requests");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");

        try {
            List<Appointment> appointmentRequests = doctor.getAppointmentRequests();
            for (Appointment appointmentReq : appointmentRequests) {
                System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                        appointmentReq.getAppointmentID(), appointmentReq.getTimeSlot().getDate(),
                        appointmentReq.getTimeSlot().getTime(), appointmentReq.getPatientID());
            }
        } catch (Exception e) {
            System.out.println("No appointment requests found.");
        }
        System.out.println(fourColBorder);
        System.out.println();
        System.out.println("Press enter to go back.");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }

    public static void upcomingAppointmentsDisplay(List<Appointment> upcomingAppointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "Upcoming Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");

        for (Appointment upcomingAppointment : upcomingAppointments) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    upcomingAppointment.getAppointmentID(), upcomingAppointment.getTimeSlot().getDate(),
                    upcomingAppointment.getTimeSlot().getTime(), upcomingAppointment.getPatientID());
        }

        System.out.println(fourColBorder);
        System.out.println();
        System.out.println("Press enter to go back.");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }

    public static void viewScheduledAppointments(List<Appointment> appointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "All Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");

        for (Appointment appointment : appointments) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    appointment.getAppointmentID(), appointment.getTimeSlot().getDate(),
                    appointment.getTimeSlot().getTime(), appointment.getPatientID());
        }

        System.out.println(fourColBorder);
        System.out.println();
    }

}
