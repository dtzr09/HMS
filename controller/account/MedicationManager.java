package controller.account;

import database.MedicationDatabase;
import model.medication.Medication;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.CSVReader;
import java.util.UUID;

import java.util.List;

public class MedicationManager {
    public Medication findMedication(String medicationID) throws ModelNotFoundException {
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
                String medicationName = medication.get(0);
                int medicationStock = Integer.parseInt(medication.get(1));
                int lowStockLevelAlert = Integer.parseInt(medication.get(2));

                // TODO: add price to Medication constructor
                addMedication(new Medication(medicationID, medicationName, medicationStock, 0.0, lowStockLevelAlert));
            } catch (ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }
}
