package AccountManager;

import java.util.List;

public class Doctor {
    private String id;
    private String name;
    private AppointmentManager appointmentManager;

    public Doctor(String id, String name) {
        this.id = id;
        this.name = name;
        this.appointmentManager = new AppointmentManager();
    }

    public void viewPatientMedicalRecords(Patient patient) {
        System.out.println("Viewing medical records for: " + patient.getName());
        List<MedicalRecord> records = patient.getMedicalRecords();
        if (records.isEmpty()) {
            System.out.println("No medical records found.");
        } else {
            for (MedicalRecord record : records) {
                System.out.println(record);
            }
        }
    }

    public void updatePatientRecord(Patient patient, String diagnosis, String treatment) {
        String currentDate = java.time.LocalDate.now().toString();
        patient.addMedicalRecord(diagnosis, treatment, currentDate);
        System.out.println("Updated records for: " + patient.getName());
    }

    public void scheduleAppointment(String patientID, TimeSlot timeSlot) {
        appointmentManager.scheduleAppointment(id, patientID, timeSlot);
        System.out.println("Appointment scheduled for Patient ID: " + patientID);
    }

    public List<Appointment> viewAppointments() {
        return appointmentManager.getAppointmentsForPatient(id);
    }
    
    public void cancelAppointment(Appointment appointment2) {
        List<Appointment> appointments = appointmentManager.getAppointmentsForPatient(id);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointment2)) {
                appointmentManager.cancelAppointment(appointment);
                System.out.println("Cancelled appointment: " + appointment);
                return; // Exit after canceling the appointment
            }
        }
        System.out.println("Appointment with ID: " + appointment2 + " not found for this doctor.");
    }
}
