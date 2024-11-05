package display;

import java.util.UUID;

import controller.medication.MedicationManager;
import display.user.AdministratorDisplay;
import model.medication.Medication;
import model.user.Administrator;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class MedicationDisplay {

    public static void medicationDisplay(Administrator user) {
        ClearDisplay.ClearConsole();
        System.out.println("============== MANAGE MEDICATION INVENTORY ==============");
        System.out.println();
        System.out.println("1. Add new medication");
        System.out.println("2. Add medication stock");
        System.out.println("3. Delete medication");
        System.out.println("4. Go back");
        System.out.println("==========================================================");
        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

        switch (choice) {
            case 1 -> addNewMedication(user);
            case 2 -> {
                System.out.print("Enter Medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                MedicationManager.updateMedicationStock(medicationID);
            }
            case 3 -> {
                System.out.print("Enter Medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                MedicationManager.deleteMedication(medicationID);
            }
            case 4 -> AdministratorDisplay.administratorDisplay(user);
            default -> {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewMedication(Administrator user) {
        String medicationID = UUID.randomUUID().toString();
        System.out.print("Enter Medication Name: ");
        String medicationName = CustScanner.getStrChoice();
        System.out.print("Enter Medication Current Quantity: ");
        int medicationStock = CustScanner.getIntChoice();
        System.out.print("Enter Low Stock Level Alert: ");
        int lowStockLevelAlert = CustScanner.getIntChoice();

        try {
            MedicationManager
                    .addMedication(new Medication(medicationID, medicationName, medicationStock, lowStockLevelAlert));
        } catch (ModelAlreadyExistsException e) {
            System.out.println("Medication already exists.");
            medicationDisplay(user);
        }

    }
}
