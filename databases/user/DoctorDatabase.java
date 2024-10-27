package databases.user;

import model.user.Doctor;

import java.util.List;
import java.util.Map;

import databases.Database;

public class DoctorDatabase extends Database<Doctor> {

    private static final String FILE_PATH = "./data/user/doctor.txt";

    DoctorDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of DoctorDatabase.
     *
     * @return a new instance of DoctorDatabase
     */
    public static DoctorDatabase getInstance() {
        return new DoctorDatabase();
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
            getAll().add(new Doctor(map));
        }
    }

}
