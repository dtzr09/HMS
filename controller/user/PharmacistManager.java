package controller.user;

import controller.medication.PrescriptionManager;
import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import database.appointment.AppointmentOutcomeDatabase;
import model.appointment.AppointmentOutcome;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.PrescriptionStatus;
import model.request.enums.RequestStatus;
import utils.exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class PharmacistManager {

    public static ArrayList <AppointmentOutcome> getAppointmentOutcomeRecords(String patientID){
        ArrayList <AppointmentOutcome> recordList = (ArrayList<AppointmentOutcome>) AppointmentOutcomeDatabase.getDB().findByRules(
            AppointmentOutcome -> AppointmentOutcome.getPatientID().equals(patientID));
        return recordList;
    }

    public static void updatePrescriptionStatus(Diagnosis diagnosis, PrescriptionStatus status) {
        PrescriptionManager.updatePrescriptionStatus(diagnosis.getPrescription(), status);
        System.out.println("Diagnosis [ "+diagnosis.getDiagnosisID()+" ] Prescription status has been updated.");
    }

    public static ArrayList<Medication> getMedicationInventory() {
        return (ArrayList<Medication>) MedicationManager.getMedications();
    }

    public static ArrayList<String> getLowStockIDs() {
        ArrayList<String> idList = new ArrayList<String>();
        for (Medication i : getMedicationInventory()) {
            if (i.getStock() <= i.getLowStockLevelAlert()) {
                idList.add(i.getModelID());
            }
        }
        return idList;
    }

    public static ArrayList<Medication> getLowStockMedicationInventory() {
        ArrayList<Medication> medList = new ArrayList<Medication>();
        for (String id : getLowStockIDs()) {
            try {
                medList.add(MedicationManager.findMedication(id));
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return medList;
    }

    public static void viewInventory(ArrayList<Medication> medications) {
        for (Medication medication : medications) {
            System.out.println("ID: " + medication.getModelID() + "--- Name: " + medication.getName() + "--- Stock: "
                    + medication.getStock());
        }
    }

    public static void viewMedicationInventory() {
        viewInventory(getMedicationInventory());
    }

    public static void viewLowStockMedicationInventory() {
        viewInventory(getLowStockMedicationInventory());
    }

    public static void submitReplenishmentRequest(String medicationID) {
        String replenishmentRequestID = UUID.randomUUID().toString();
        Date dateOfRequest = new Date();
        ReplenishmentRequestManager.addReplenishmentRequest(replenishmentRequestID, RequestStatus.PENDING,
                dateOfRequest, dateOfRequest, medicationID);
    }

}
