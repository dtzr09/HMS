package database.medicalRecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.Database;
import model.prescription.Prescription;
import model.prescription.enums.PrescriptionStatus;
import utils.utils.ConvertToArrayList;

/**
 * Manages the storage and retrieval of Prescription entities in the hospital
 * management system.
 * Extends the Database class with a specific type of Prescription, enabling
 * operations such as loading, saving, and retrieving prescription data.
 */
public class PrescriptionDatabase extends Database<Prescription> {
    private static final String FILE_PATH = "./data/medical/prescription.txt";

    /**
     * Initializes a new PrescriptionDatabase instance and loads the data from the
     * specified file.
     */
    PrescriptionDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of PrescriptionDatabase.
     *
     * @return a new instance of PrescriptionDatabase
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
            // String dateOfPrescription = map.get("dateOfPrescription");
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

    /**
     * Gets all prescriptions.
     *
     * @return a list of all prescriptions
     */
    public List<Prescription> getAllPrescriptions() {
        return super.getAll();
    }
}
