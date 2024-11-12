package model.medication;

import java.util.Map;

import model.Model;

public class Medication implements Model {
    private String medicationID;
    private String name;
    private int stock;
    private int lowStockLevelAlert;

    /**
     * Constructs a Medication with all specified fields.
     *
     * @param medicationID       the unique identifier for the medication.
     * @param name               the name of the medication.
     * @param stock              the current stock level of the medication.
     * @param lowStockLevelAlert the stock level at which an alert should be
     *                           triggered.
     */
    public Medication(String medicationID, String name, int stock, int lowStockLevelAlert) {
        this.medicationID = medicationID;
        this.name = name;
        this.stock = stock;
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

    /**
     * Constructs a Medication by converting a map of attributes.
     *
     * @param map a map of medication attributes.
     */
    public Medication(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique identifier for the medication.
     *
     * @return the ID of the medication.
     */
    public String getModelID() {
        return this.medicationID;
    }

    /**
     * Retrieves the name of the medication.
     *
     * @return the name of the medication.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medication.
     *
     * @param name the name to set for the medication.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the current stock level of the medication.
     *
     * @return the stock level of the medication.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock level of the medication.
     *
     * @param stock the stock level to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retrieves the stock level alert threshold for the medication.
     *
     * @return the stock level at which an alert should be triggered.
     */
    public int getLowStockLevelAlert() {
        return lowStockLevelAlert;
    }

    /**
     * Sets the stock level alert threshold for the medication.
     *
     * @param lowStockLevelAlert the stock level alert threshold to set.
     */
    public void setLowStockLevelAlert(int lowStockLevelAlert) {
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

}
