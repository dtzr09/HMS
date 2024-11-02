package AccountManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Enums for User Roles and Appointment Status
enum UserRole {
    ADMIN, DOCTOR, PATIENT, PHARMACIST
}

enum Status {
    SCHEDULED, COMPLETED, CANCELLED
}

// Class to represent a TimeSlot
class TimeSlot {
    private String date;
    private String time;

    public TimeSlot(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date + " at " + time;
    }
}

// Class to represent a Medical Record
class MedicalRecord {
    private String diagnosis;
    private String treatment;
    private String date;

    public MedicalRecord(String diagnosis, String treatment, String date) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
    }
}

// Class to represent a User
class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private UserRole userRole;

    public User(UserRole userRole, String id, String password, String name, String email) {
        this.userRole = userRole;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Class to manage accounts
class AccountManager {
    private String defaultPassword = "password";
    private User loggedInUser;

    public User login(String id, String password) {
        // In a real application, this should check a user database
        // Here, we create a dummy user for demonstration
        User user = new User(UserRole.PATIENT, id, defaultPassword, "John Doe", "john@example.com");

        if (user.getId().equals(id) && user.getPassword().equals(password)) {
            loggedInUser = user;
            return loggedInUser;
        }
        return null; // Login failed
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean changePassword(String oldPW, String newPW) {
        if (loggedInUser != null && loggedInUser.getPassword().equals(oldPW)) {
            loggedInUser = new User(loggedInUser.getUserRole(), loggedInUser.getId(), newPW, loggedInUser.getName(), loggedInUser.getEmail());
            return true; // Password changed successfully
        }
        return false; // Password change failed
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

// Class to represent a Patient
class Patient {
    private String id;
    private String name;
    private String email;
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.medicalRecords = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updatePersonalInfo(String newEmail) {
        this.email = newEmail;
        System.out.println("Email updated to: " + newEmail);
    }

    public void addMedicalRecord(String diagnosis, String treatment, String date) {
        medicalRecords.add(new MedicalRecord(diagnosis, treatment, date));
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}

// Class to manage Appointments
class Appointment {
    private String doctorID;
    private String patientID;
    private TimeSlot timeSlot;
    private Status status;

    public Appointment(String doctorID, String patientID, TimeSlot timeSlot) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.timeSlot = timeSlot;
        this.status = Status.SCHEDULED;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment with Doctor ID: " + doctorID + " on " + timeSlot + " Status: " + status;
    }
}

// Class to manage appointments
class AppointmentManager {
    private List<Appointment> appointments = new ArrayList<>();

    public Appointment scheduleAppointment(String doctorID, String patientID, TimeSlot timeSlot) {
        Appointment appointment = new Appointment(doctorID, patientID, timeSlot);
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentsForPatient(String patientID) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientID)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus(Status.CANCELLED);
        System.out.println("Appointment cancelled: " + appointment);
    }

    public void completeAppointment(Appointment appointment) {
        appointment.setStatus(Status.COMPLETED);
        System.out.println("Appointment completed: " + appointment);
    }
}

// Class for Doctor functionalities
class Doctor {
    private String id;
    private String name;
    AppointmentManager appointmentManager;

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
}

// Class for Pharmacist functionalities
class Pharmacist {
    public void updatePrescriptionStatus(String prescriptionID, String status) {
        System.out.println("Updating prescription status for ID: " + prescriptionID + " to " + status);
    }

    public void viewMedicationInventory() {
        System.out.println("Viewing medication inventory...");
    }
}

// Class for Administrator functionalities
class Administrator {
    public void manageHospitalStaff(String userID, String action) {
        System.out.println("Managing hospital staff with user ID: " + userID + " action: " + action);
    }

    public void viewMedicationInventory() {
        System.out.println("Viewing medication inventory...");
    }
}

// Main class to run the application
public class AccountManager2 {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();

        // Create sample Patients and Doctors
        Patient patient = new Patient("patient1", "John Doe", "john@example.com");
        Doctor doctor = new Doctor("doctor1", "Dr. Smith");

        // Simulate patient login
        User loggedInPatient = accountManager.login(patient.getId(), "password");
        if (loggedInPatient != null) {
            System.out.println("Logged in as: " + loggedInPatient.getName());

            // Patient adds medical records
            patient.addMedicalRecord("Flu", "Rest and hydration", "2024-10-01");
            patient.addMedicalRecord("Cold", "Rest and over-the-counter medication", "2024-11-01");

            // Doctor views patient's medical records
            doctor.viewPatientMedicalRecords(patient);

            // Doctor updates patient's medical record
            doctor.updatePatientRecord(patient, "Allergy", "Antihistamines");

            // Doctor schedules an appointment for the patient
            TimeSlot appointmentSlot = new TimeSlot("2024-11-10", "10:00 AM");
            doctor.scheduleAppointment(patient.getId(), appointmentSlot);

            // View scheduled appointments
            List<Appointment> appointments = doctor.viewAppointments();
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

            // Patient cancels an appointment if exists
            if (!appointments.isEmpty()) {
                doctor.appointmentManager.cancelAppointment(appointments.get(0));
            }

            // Logging out
            accountManager.logout();
            System.out.println("Logged out.");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }
}




