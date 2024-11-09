package database.medicalRecords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import database.Database;
import model.prescription.Prescription;
import model.prescription.PrescriptionStatus;
import utils.utils.ConvertToArrayList;

public class PrescriptionDatabase extends Database<Prescription> {
    private static final String FILE_PATH = "./data/user/prescription.txt";

    PrescriptionDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static PrescriptionDatabase getDB() {
        return new PrescriptionDatabase();
    }

    /**
     * Gets the file path of the Database.
     *
     * @return the file path of the Database
     */
    @Override
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Sets the list of mappable objects in the Database.
     *
     * @param listOfMappableObjects the list of mappable objects to set
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            String prescriptionID = map.get("prescriptionID");
            String patientID = map.get("patientID");
            String pharmacistID = map.get("pharmacistID");
            String doctorID = map.get("doctorID");
            String dateOfPrescription = map.get("dateOfPrescription");
            String drugInstructions = map.get("drugInstructions");
            String prescriptionStatus = map.get("prescriptionStatus");
            String medicationIDsStr = map.get("medicationIDs");
            ArrayList<String> medicationIDs = ConvertToArrayList.convertToArrayList(medicationIDsStr);

            PrescriptionStatus status = PrescriptionStatus.fromString(prescriptionStatus);
            Prescription prescription = new Prescription(prescriptionID, patientID, pharmacistID, doctorID,
                    medicationIDs, null, drugInstructions, status);

            getAll().add(prescription);
        }
    }

    public List<Prescription> getAllPrescriptions() {
        return super.getAll();
    }
}
