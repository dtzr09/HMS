package AccountManager;

import controller.Status;

public class Appointment {
    private String appointmentID; 
    private String doctorID; 
    private String patientID; 
    private TimeSlot timeSlot; 
    private Status status; 
    private String outcome; 

    public Appointment(String appointmentID, String doctorID, String patientID, TimeSlot timeSlot) {
        this.appointmentID = appointmentID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.timeSlot = timeSlot;
        this.status = Status.SCHEDULED;
        this.outcome = "";
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Status getStatus() {
        return status;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Doctor ID: " + doctorID +
               ", Patient ID: " + patientID + ", Time: " + timeSlot + 
               ", Status: " + status + ", Outcome: " + outcome;
    }
}
