package display.medication;

import java.util.List;
import java.util.UUID;

import controller.medication.MedicationManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import display.user.AdministratorDisplay;
import model.medication.Medication;
import model.user.Administrator;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class MedicationDisplay {

    public static void medicationDisplay(Administrator administrator) throws PageBackException {
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
        System.out.println();

        switch (choice) {
            case 1 -> addNewMedication();
            case 2 -> addMedicationStock();
            case 3 -> removeMedication();
            case 4 -> AdministratorDisplay.administratorDisplay(administrator);
            default -> {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void removeMedication() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== REMOVE MEDICATION STOCK ==============");
        System.out.println();
        displayMedicationInventory();
        System.out.print("Enter Medication ID: ");
        String medicationID = CustScanner.getStrChoice();

        Medication selectedMed = null;
        try {
            selectedMed = MedicationManager.getMedicationsById(medicationID);
        } catch (Exception e) {
            System.out.println("Medication not found.");
            throw new PageBackException();
        }

        MedicationManager.deleteMedication(medicationID);
        System.out.println();
        System.out.println(
                "Medication " + selectedMed.getName() + " has been removed from the inventory.");
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    private static void addMedicationStock() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("============== ADD MEDICATION STOCK ==============");
        System.out.println();

        displayMedicationInventory();

        System.out.println();
        System.out.print("Enter Medication ID: ");
        String medicationID = CustScanner.getStrChoice();
        Medication selectedMed = null;
        try {
            selectedMed = MedicationManager.getMedicationsById(medicationID);
        } catch (Exception e) {
            System.out.println("Medication not found.");
            throw new PageBackException();
        }

        MedicationManager.updateMedicationStock(medicationID);
        System.out.println();
        System.out.println(
                "Medication stock updated! The current stock for the medication " + selectedMed.getName() + " is now "
                        + selectedMed.getStock());
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    private static void addNewMedication() throws PageBackException {
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
            throw new PageBackException();
        }

        System.out.println();
        System.out.println("Medication " + medicationName + " has been added to the inventory.");
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    public static void viewMedicationInventory() throws PageBackException {
        ClearDisplay.ClearConsole();
        displayMedicationInventory();
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    public static void displayMedicationInventory() {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";
        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "MEDICATION INVENTORY");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Name", "Quantity", "Low Stock Level Alert");
        System.out.println(fourColBorder);
        List<Medication> medications = MedicationManager.getMedications();
        for (Medication medication : medications) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    medication.getModelID(), medication.getName(),
                    medication.getStock(), medication.getLowStockLevelAlert());
        }
        System.out.println(fourColBorder);
    }
}
