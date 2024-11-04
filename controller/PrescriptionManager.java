package controller;

import database.user.PatientDatabase;
import model.diagnosis.Diagnosis;
import model.prescription.*;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.CSVReader;
import java.util.UUID;

import java.util.ArrayList;

public class PrescriptionManager {
    // Get List of Prescriptions obj of a Diagnosis
    public ArrayList<Prescription> getAllPrescriptionsOfDiagnosis(Diagnosis diagnosis){
        return diagnosis.getPrescriptions();
    }
    // Get List of prescriptionID strings
    public ArrayList<String> getAllPrescriptionIDs(Diagnosis diagnosis){
        ArrayList<String> allPrescriptionIDs = new ArrayList<String>();
        for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
            allPrescriptionIDs.add(prescription.getPrescriptionID());            
        }
        return allPrescriptionIDs;
    }
    // Get List of pending status prescriptionID strings
    public ArrayList<String> getAllPendingPrescriptionsIDs(Diagnosis diagnosis){
        ArrayList<String> pendingPrescriptionIDs = new ArrayList<String>();
        for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
            if (prescription.getPrescriptionStatus() == PrescriptionStatus.PENDING) {
                pendingPrescriptionIDs.add(prescription.getPrescriptionID());           
            }
        }
        return pendingPrescriptionIDs;
    }
    // Getting corresponding index pos of a Prescription obj with its ID
    public int getPresciptionIndexPos( Diagnosis diagnosis , String prescriptionID){
        return getAllPrescriptionIDs(diagnosis).indexOf(prescriptionID);
    }
    // Getting a particular prescription with its ID
    public Prescription getPresciption(Diagnosis diagnosis, String prescriptionID){
        return getAllPrescriptionsOfDiagnosis(diagnosis).get(getPresciptionIndexPos(diagnosis, prescriptionID));
    }

    public ArrayList<Prescription> getAllPendingPrescriptionsOfDiagnosis(Diagnosis diagnosis){
        ArrayList<Prescription> allPendingPrescriptions = new ArrayList<Prescription>();
        for (String id : getAllPendingPrescriptionsIDs(diagnosis)) {
            allPendingPrescriptions.add(getPresciption(diagnosis, id));
        }
        return allPendingPrescriptions;
    }

    public void updatePrescriptionStatus(Diagnosis diagnosis, String prescriptionID, PrescriptionStatus status){
        getPresciption(diagnosis, prescriptionID).setPrescriptionStatus(status);
    }
}
