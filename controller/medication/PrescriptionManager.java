package controller.medication;

import database.user.PatientDatabase;
import controller.user.UserManager;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import model.diagnosis.Diagnosis;
import model.prescription.*;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.CSVReader;
import java.util.UUID;

import java.util.ArrayList;

public class PrescriptionManager {
    // Get List of Prescriptions obj of a Diagnosis
    public static ArrayList<Prescription> getAllPrescriptionsOfDiagnosis(Diagnosis diagnosis){
            return diagnosis.getPrescriptions();
        }
        // Get List of prescriptionID strings
        public static ArrayList<String> getAllPrescriptionIDs(Diagnosis diagnosis){
                    ArrayList<String> allPrescriptionIDs = new ArrayList<String>();
                    for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
                        allPrescriptionIDs.add(prescription.getPrescriptionID());            
                    }
                    return allPrescriptionIDs;
                }
                // Get List of pending status prescriptionID strings
                public static ArrayList<String> getAllPendingPrescriptionsIDs(Diagnosis diagnosis){
                        ArrayList<String> pendingPrescriptionIDs = new ArrayList<String>();
                        for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
                        if (prescription.getPrescriptionStatus().equals(PrescriptionStatus.PENDING)) {
                            pendingPrescriptionIDs.add(prescription.getPrescriptionID());           
                        }
                    }
                    return pendingPrescriptionIDs;
                }
                public static void printAllPendingPrescriptioinsIDs(Diagnosis diagnosis){
                    for (String i : getAllPendingPrescriptionsIDs(diagnosis)) {
                    System.out.println(i);
                }
            }
            // Getting corresponding index pos of a Prescription obj with its ID
            public static int getPresciptionIndexPos( Diagnosis diagnosis , String prescriptionID){
                    return getAllPrescriptionIDs(diagnosis).indexOf(prescriptionID);
        }
        // Getting a particular prescription with its ID
        public static Prescription getPresciption(Diagnosis diagnosis, String prescriptionID){
                return diagnosis.getPrescriptions().get(getPresciptionIndexPos(diagnosis, prescriptionID));
        }
    
        public ArrayList<Prescription> getAllPendingPrescriptionsOfDiagnosis(Diagnosis diagnosis){
            ArrayList<Prescription> allPendingPrescriptions = new ArrayList<Prescription>();
            for (String id : getAllPendingPrescriptionsIDs(diagnosis)) {
                allPendingPrescriptions.add(getPresciption(diagnosis, id));
            }
            return allPendingPrescriptions;
        }
    
        public static void updatePrescriptionStatus(Diagnosis diagnosis, String prescriptionID, PrescriptionStatus status){
            getPresciption(diagnosis, prescriptionID).setPrescriptionStatus(status);

    }
}
