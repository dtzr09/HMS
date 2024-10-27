package repository;

import model.medication.Medication;
import java.util.List;
import java.util.Map;

public class MedicationRepository extends Repository<Medication> {

    private static final String FILE_PATH = "./data/user/medication.txt";

    MedicationRepository() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationRepository.
     *
     * @return a new instance of MedicationRepository
     */
    public static MedicationRepository getInstance() {
        return new MedicationRepository();
    }

    /**
     * Gets the file path of the repository.
     *
     * @return the file path of the repository
     */
    @Override
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Sets the list of mappable objects in the repository.
     *
     * @param listOfMappableObjects the list of mappable objects to set
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Medication(map));
        }
    }

}
