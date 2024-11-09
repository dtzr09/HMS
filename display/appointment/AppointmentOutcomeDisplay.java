package display.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.appointment.AppointmentOutcomeManager;
import controller.user.PharmacistManager;
import database.user.PatientDatabase;
import display.EnterToGoBackDisplay;
import model.appointment.AppointmentOutcome;
import model.medication.Medication;
import model.prescription.PrescriptionStatus;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;

public class AppointmentOutcomeDisplay {

    public static void viewAppointmentOutcomeRecordsForPharmacist(String email) throws PageBackException {
        String eightColBorder = "+--------------------------------------+--------------------------------------+--------------------------------------+--------------------------------------+--------------------------------------+----------------------+----------------------+----------------------+";
        try {
            Patient patient = PatientDatabase.getDB().getByEmail(email);
            String patientID = patient.getPatientID();
            ArrayList<AppointmentOutcome> recordList = PharmacistManager.getAppointmentOutcomeRecords(patientID);

            if (recordList.isEmpty() || recordList == null) {
                System.out.println("No appointment outcome records found for this patient.");
                System.out.println();
                EnterToGoBackDisplay.display();
            }

            List<AppointmentOutcome> outcomes = AppointmentOutcomeManager
                    .getPatientsAppointmentOutcomeRecords(patientID);

            System.out.println("Appointment Outcome Records for " + patient.getName());
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println(eightColBorder);
            System.out.printf("| %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %n",
                    "Appointment Outcome ID",
                    "Patient ID",
                    "Doctor ID", "Type of Service", "Consultation Notes", "Medications", "Appointment ID",
                    "Prescription Status");
            System.out.println(eightColBorder);
            for (AppointmentOutcome outcome : outcomes) {
                ArrayList<Medication> medication = outcome.getPrescription().getMedication();
                PrescriptionStatus status = outcome.getPrescription().getPrescriptionStatus();
                System.out.printf("| %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %-36s |%n",
                        outcome.getAppointmentOutcomeID(),
                        outcome.getPatientID(),
                        outcome.getDoctorID(),
                        outcome.getTypeOfService(),
                        outcome.getConsultationNotes(),
                        String.join(", ", medication.toString()),
                        outcome.getAppointmentID(),
                        status);
            }
            System.out.println(eightColBorder);
            System.out.println();
            EnterToGoBackDisplay.display();
        } catch (ModelNotFoundException e) {
            System.out.println("Email not found!");
            EnterToGoBackDisplay.display();
        }

    }

    public static void viewAppointmentOutcomeRecordsForPatient(String email) throws PageBackException {
        String sixColBorder = "+--------------------------------------+--------------------------------------+--------------------------------------+----------------------+----------------------+----------------------+";
        try {
            Patient patient = PatientDatabase.getDB().getByEmail(email);
            String patientID = patient.getPatientID();
            ArrayList<AppointmentOutcome> recordList = PharmacistManager.getAppointmentOutcomeRecords(patientID);

            if (recordList.isEmpty() || recordList == null) {
                System.out.println("No appointment outcome records found.");
                System.out.println();
                EnterToGoBackDisplay.display();
            }

            List<AppointmentOutcome> outcomes = AppointmentOutcomeManager
                    .getPatientsAppointmentOutcomeRecords(patientID);

            System.out.println("Appointment Outcome Records for " + patient.getName());
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println(sixColBorder);
            System.out.printf("| %-36s | %-36s | %-36s | %-36s | %-36s | %-36s | %n",
                    "Appointment Outcome ID",
                    "Doctor ID", "Type of Service", "Consultation Notes", "Medications", "Appointment ID",
                    "Prescription");
            System.out.println(sixColBorder);
            for (AppointmentOutcome outcome : outcomes) {
                ArrayList<Medication> medication = outcome.getPrescription().getMedication();
                System.out.printf("| %-36s | %-36s | %-36s | %-36s | %-36s | %-36s |%n",
                        outcome.getAppointmentOutcomeID(),
                        outcome.getPatientID(),
                        outcome.getDoctorID(),
                        outcome.getTypeOfService(),
                        outcome.getConsultationNotes(),
                        String.join(", ", medication.toString()),
                        outcome.getAppointmentID());
            }
            System.out.println(sixColBorder);
            System.out.println();
            EnterToGoBackDisplay.display();
        } catch (Exception e) {
            System.out.println("Email not found!");
            EnterToGoBackDisplay.display();
        }

    }
}
