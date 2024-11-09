package controller.medication;

import model.medication.Medication;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.CSVReader;
import java.util.UUID;

import database.medicalRecords.MedicationDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedicationManager {

    public static Medication getMedicationsById(String medicationID) throws ModelNotFoundException {
        return MedicationDatabase.getDB().getByID(medicationID);
    }

    public static void addMedication(Medication medication) throws ModelAlreadyExistsException {
        MedicationDatabase.getDB().add(medication);
    }

    public static void updateMedication(Medication medication) throws ModelNotFoundException {
        MedicationDatabase.getDB().update(medication);
    }

    public static boolean isRepositoryEmpty() {
        return MedicationDatabase.getDB().isEmpty();
    }

    public static void loadMedication() {
        List<List<String>> medications = CSVReader.read("./resources/Medicine_List.csv", true);
        for (List<String> medication : medications) {
            try {
                String medicationID = UUID.randomUUID().toString();
                String medicationName = medication.get(1);
                int medicationStock = Integer.parseInt(medication.get(2));
                int lowStockLevelAlert = Integer.parseInt(medication.get(3));
                addMedication(new Medication(medicationID, medicationName, medicationStock, lowStockLevelAlert));
            } catch (ModelAlreadyExistsException e) {
                System.out.println("Medication already exists.");
            }
        }
    }

    public static List<Medication> getMedications() {
        return MedicationDatabase.getDB().getAllMedications();
    }

    public static void updateMedicationStock(String medicationID) {
        try {
            Medication medication = getMedicationsById(medicationID);
            medication.setStock(medication.getStock() + 10);
            updateMedication(medication);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    public static void reduceMedicationStock(String medicationID) {
        try {
            Medication medication = getMedicationsById(medicationID);
            medication.setStock(medication.getStock() - 1);
            updateMedication(medication);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    public static void deleteMedication(String medicationID) {
        try {
            MedicationDatabase.getDB().remove(medicationID);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    public static ArrayList<Medication> getMedicationsByIDs(ArrayList<String> medicationIDs)
            throws ModelNotFoundException {
        ArrayList<Medication> medications = new ArrayList<>();
        for (String medicationID : medicationIDs) {
            medications.add(getMedicationsById(medicationID));
        }
        return medications;
    }
}
