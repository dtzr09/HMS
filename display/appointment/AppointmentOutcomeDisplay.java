package display.appointment;

import java.util.List;

import controller.appointment.AppointmentOutcomeManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import model.appointment.AppointmentOutcome;
import model.appointment.AppointmentOutcomeRecord;
import model.user.Patient;
import utils.exceptions.PageBackException;

/**
 * This class is responsible for displaying the appointment outcome records for
 * a patient
 */
public class AppointmentOutcomeDisplay {

    /**
     * Displays the appointment outcome records for a patient, specifically for a
     * pharmacist's view.
     * 
     * This method retrieves and displays the appointment outcome records
     * for the specified patient ID, including information such as type of service,
     * consultation notes, medications, appointment date, and prescription status.
     *
     * @param patientID the unique identifier of the patient whose appointment
     *                  outcome records are to be displayed.
     * @throws PageBackException if there is an attempt to navigate back in the
     *                           display.
     */
    public static void viewAppointmentOutcomeRecordsForPharmacist(String patientID) throws PageBackException {
        ClearDisplay.ClearConsole();
        String sevenColBorder = "+--------------------------------------+--------------------+--------------------+--------------------+--------------------+--------------------+";
        List<AppointmentOutcome> recordList = AppointmentOutcomeManager
                .getPatientsAppointmentOutcomeRecords(patientID);
        if (recordList.isEmpty() || recordList == null) {
            System.out.println();
            System.out.println("No appointment outcome records found for this patient.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }
        Patient patient = PatientManager.getPatientById(patientID);

        System.out.println("Appointment Outcome Records for " + patient.getName());
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(sevenColBorder);
        System.out.printf("| %-36s | %-18s | %-18s | %-18s | %18s | %-18s| %n", "Appointment Outcome ID",
                "Type of Service", "Consultation Notes", "Medications", "Appointment Date",
                "Prescription Status");
        System.out.println(sevenColBorder);
        List<AppointmentOutcomeRecord> records = AppointmentOutcomeManager.getAppointmentOutcomeRecords(recordList,
                patientID);
        for (AppointmentOutcomeRecord record : records) {
            System.out.printf("| %-36s | %-18s | %-18s | %-18s | %18s | %-18s | %n",
                    record.getAppointmentOutcomeID(),
                    record.getTypeOfService(), record.getConsultationNotes(),
                    String.join(",", record.getMedicationsNames()),
                    record.getAppointmentDate(), record.getPrescriptionStatus());
        }
        System.out.println(sevenColBorder);
        System.out.println();
        EnterToGoBackDisplay.display();

    }

    /**
     * Displays the appointment outcome records for a patient, specifically for a
     * patient's view.
     * 
     * This method retrieves and displays the appointment outcome records
     * for the specified patient ID, including information such as type of service,
     * consultation notes and medications.
     * 
     * @param patient the patient whose appointment outcome records are to be
     *                displayed.
     * @throws PageBackException if there is an attempt to navigate back in the
     *                           display.
     */
    public static void viewAppointmentOutcomeRecordsForPatient(Patient patient) throws PageBackException {
        String fourcolBorder = "+--------------------------------------+--------------------------------------+--------------------------------------+--------------------------------------+";
        String patientID = patient.getPatientID();
        List<AppointmentOutcome> recordList = AppointmentOutcomeManager
                .getPatientsAppointmentOutcomeRecords(patientID);

        if (recordList.isEmpty() || recordList == null) {
            System.out.println("No appointment outcome records found.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }

        System.out.println("Appointment Outcome Records for " + patient.getName());
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(fourcolBorder);
        System.out.printf("| %-36s |  %-35s | %-35s | %-35s | %n",
                "Appointment Outcome ID",
                "Type of Service", "Consultation Notes", "Medications");
        System.out.println(fourcolBorder);

        try {
            List<AppointmentOutcomeRecord> records = AppointmentOutcomeManager.getAppointmentOutcomeRecords(recordList,
                    patientID);
            for (AppointmentOutcomeRecord record : records) {
                System.out.printf("| %-36s |  %-35s | %-35s | %-35s | %n",
                        record.getAppointmentOutcomeID(),
                        record.getTypeOfService(), record.getConsultationNotes(),
                        String.join(",", record.getMedicationsNames()));
            }
            System.out.println(fourcolBorder);
            System.out.println();
            EnterToGoBackDisplay.display();
        } catch (Exception e) {
            EnterToGoBackDisplay.display();
        }

    }
}
