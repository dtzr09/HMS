package model.medication;

import java.util.Map;

import model.Model;

public class Medication implements Model {
    private String medicationID;
    private String name;
    private int stock;
    private double price;
    private double lowStockLevelAlert;

    public Medication(String name, int stock, double price, double lowStockLevelAlert) {
        this.name = name;
        this.stock = stock;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLowStockLevelAlert() {
        return lowStockLevelAlert;
    }

    public void setLowStockLevelAlert(double lowStockLevelAlert) {
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

}
