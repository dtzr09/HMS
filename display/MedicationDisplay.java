package display;

import java.util.List;
import java.util.UUID;

import controller.medication.MedicationManager;
import display.user.AdministratorDisplay;
import model.medication.Medication;
import model.user.Administrator;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class MedicationDisplay {

    public static void medicationDisplay(Administrator administrator) {
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
            case 1 -> addNewMedication(administrator);
            case 2 -> {
                displayMedicationInventory();
                System.out.print("Enter Medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                MedicationManager.updateMedicationStock(medicationID);
            }
            case 3 -> {
                displayMedicationInventory();
                System.out.print("Enter Medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                MedicationManager.deleteMedication(medicationID);
            }
            case 4 -> AdministratorDisplay.administratorDisplay(administrator);
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

    public static void viewMedicationInventory() throws PageBackException {
        ClearDisplay.ClearConsole();
        displayMedicationInventory();
        System.out.println();
        System.out.println("Press enter to go back.");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }

    private static void displayMedicationInventory() {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";
        System.out.println(fourColBorder);
        System.out.printf("| %-97s |%n", " " + "MEDICATION INVENTORY");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n", "ID", "Name", "Quantity", "Low Stock Level Alert");
        System.out.println(fourColBorder);
        List<Medication> medications = MedicationManager.getMedications();
        for (Medication medication : medications) {
            System.out.printf("| %-36s | %-20s | %-10s | %-10s |%n",
                    medication.getModelID(), medication.getName(),
                    medication.getStock(), medication.getLowStockLevelAlert());
        }
        System.out.println(fourColBorder);
    }
}
