package controller.user;
import controller.user.UserManager;
import controller.medication.PrescriptionManager;
import controller.medication.MedicationManager;
import controller.request.ReplenishmentRequestManager;
import model.user.User;
import model.user.enums.UserType;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.PrescriptionStatus;
import model.request.enums.RequestStatus;
import model.user.Patient;
import model.user.Pharmacist;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

import database.user.PatientDatabase;
import database.medication.MedicationDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class PharmacistManager {
    public static void updatePrescriptionStatus(Diagnosis diagnosis, String prescriptionID, PrescriptionStatus status){
        PrescriptionManager.updatePrescriptionStatus(diagnosis, prescriptionID, status);
    }

    public static ArrayList<Medication> getMedicationInventory(){
        return (ArrayList<Medication>) MedicationManager.getMedications();
    }
    
    public static ArrayList<String> getLowStockIDs(){
        ArrayList<String> idList = new ArrayList<String>();
        for (Medication i : getMedicationInventory()) {
            if (i.getStock() <= i.getLowStockLevelAlert()) {
                idList.add(i.getModelID());
            }
        }
        return idList;
    }

    public static ArrayList<Medication> getLowStockMedicationInventory(){
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

    public static void viewInventory(ArrayList<Medication> medications){
        for (Medication medication : medications) {
            System.out.println("ID: "+medication.getModelID()+"--- Name: "+medication.getName()+"--- Stock: "+medication.getStock());
        }
    }
    public static void viewMedicationInventory(){
        viewInventory(getMedicationInventory());
    }
    public static void viewLowStockMedicationInventory(){
        viewInventory(getLowStockMedicationInventory());
    }

    public static void submitReplenishmentRequest( String medicationID ){
        String replenishmentRequestID = UUID.randomUUID().toString();
        Date dateOfRequest = new Date();
        ReplenishmentRequestManager.addReplenishmentRequest(replenishmentRequestID, RequestStatus.PENDING, dateOfRequest, dateOfRequest, medicationID);
    }


}
