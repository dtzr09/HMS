package database.medicalRecords;

import java.util.List;
import java.util.Map;

import database.Database;
import model.diagnosis.Diagnosis;

public class DiagnosisDatabase extends Database<Diagnosis> {
    private static final String FILE_PATH = "./data/user/diagnosis.txt";

    DiagnosisDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static DiagnosisDatabase getDB() {
        return new DiagnosisDatabase();
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
            String patientID = map.get("patientID");
            String diagnosisID = map.get("diagnosisID");
            String disease = map.get("disease");
            String doctorID = map.get("doctorID");
            String prescriptionID = map.get("prescriptionID");
            String dateOfDiagnosis = map.get("dateOfDiagnosis");

            Diagnosis diagnosis = new Diagnosis(diagnosisID, disease, doctorID, prescriptionID, null,
                    patientID);
            getAll().add(diagnosis);
        }
    }

    public List<Diagnosis> getAllDiagnosis() {
        return super.getAll();
    }
}
