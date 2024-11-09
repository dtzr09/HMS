package controller.user;

import controller.medication.PrescriptionManager;
import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import database.appointment.AppointmentOutcomeDatabase;
import database.medicalRecords.MedicationDatabase;
import display.ClearDisplay;
import model.appointment.AppointmentOutcome;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.PrescriptionStatus;
import model.request.enums.RequestStatus;
import utils.exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PharmacistManager {

    public static ArrayList<AppointmentOutcome> getAppointmentOutcomeRecords(String patientID) {
        ArrayList<AppointmentOutcome> recordList = (ArrayList<AppointmentOutcome>) AppointmentOutcomeDatabase.getDB()
                .findByRules(AppointmentOutcome -> AppointmentOutcome.getPatientID().equals(patientID));
        return recordList;
    }

    public static void updatePrescriptionStatus(Diagnosis diagnosis,
            PrescriptionStatus status) {

        PrescriptionManager.updatePrescriptionStatus(diagnosis.getPrescriptionID(),
                status);
        System.out.println("Diagnosis " + diagnosis.getDiagnosisID() + " Prescription status has been updated.");
    }

    public static ArrayList<Medication> getMedicationInventory() {
        return (ArrayList<Medication>) MedicationDatabase.getDB().getAllMedications();
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
                medList.add(MedicationManager.getMedicationsById(id));
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return medList;
    }

    public static void viewInventory(ArrayList<Medication> medications, String title) {
        String threeColBorder = "+--------------------------------------+----------------------+------------+";

        ClearDisplay.ClearConsole();
        System.out.printf("%-20s%n", title);
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(threeColBorder);
        System.out.printf("| %-36s | %-20s | %-10s | %n", "ID", "Name", "Stock");
        System.out.println(threeColBorder);
        if (medications.isEmpty()) {
            System.out.println("| No medications found in inventory.       ");
            System.out.println(threeColBorder);
            System.out.println();
            return;
        }
        for (Medication medication : medications) {
            System.out.printf("| %-36s | %-20s | %-10s | %n", medication.getModelID(), medication.getName(),
                    medication.getStock());
        }
        System.out.println(threeColBorder);
        System.out.println();
    }

    public static void viewMedicationInventory() {
        viewInventory(getMedicationInventory(), "Medication Inventory");
    }

    public static void viewLowStockMedicationInventory() {
        viewInventory(getLowStockMedicationInventory(), "Low Stock Medication Inventory");
    }

    public static void submitReplenishmentRequest(String medicationID) {
        String replenishmentRequestID = UUID.randomUUID().toString();
        Date dateOfRequest = new Date();
        try {
            ReplenishmentRequestManager.addReplenishmentRequest(replenishmentRequestID, RequestStatus.PENDING,
                    dateOfRequest, dateOfRequest, medicationID);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

}
