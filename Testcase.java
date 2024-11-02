package AccountManager;

import java.util.List;

public class Testcase {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();

        Patient patient = new Patient("patient1", "John Doe", "john@example.com");
        Doctor doctor = new Doctor("doctor1", "Dr. Smith");

        User loggedInPatient = accountManager.login(patient.getId(), "password");
        if (loggedInPatient != null) {
            System.out.println("Logged in as: " + loggedInPatient.getName());

            patient.addMedicalRecord("Flu", "Rest and hydration", "2024-10-01");
            patient.addMedicalRecord("Cold", "Rest and over-the-counter medication", "2024-11-01");

            doctor.viewPatientMedicalRecords(patient);
            doctor.updatePatientRecord(patient, "Allergy", "Antihistamines");

            TimeSlot appointmentSlot = new TimeSlot("2024-11-10", "10:00 AM");
            doctor.scheduleAppointment(patient.getId(), appointmentSlot);

            List<Appointment> appointments = doctor.viewAppointments();
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

            if (!appointments.isEmpty()) {
                doctor.cancelAppointment(appointments.get(0));
            }

            accountManager.logout();
            System.out.println("Logged out.");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }
}
