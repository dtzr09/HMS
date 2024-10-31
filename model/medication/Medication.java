package model.medication;

import java.util.Map;

import model.Model;

public class Medication implements Model {
    private String medicationID;
    private String name;
    private int stock;
    private int lowStockLevelAlert;

    public Medication(String medicationID, String name, int stock, int lowStockLevelAlert) {
        this.medicationID = medicationID;
        this.name = name;
        this.stock = stock;
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

    /**
     * Converts the map to a Medication object
     *
     * @param map the map
     */
    public Medication(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * gets the user ID of the medication
     *
     * @return the ID of the medication
     */
    public String getModelID() {
        return this.medicationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getLowStockLevelAlert() {
        return lowStockLevelAlert;
    }

    public void setLowStockLevelAlert(int lowStockLevelAlert) {
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

}
