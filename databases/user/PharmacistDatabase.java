package databases.user;

import java.util.List;
import java.util.Map;

import databases.Database;
import model.user.Pharmacist;

public class PharmacistDatabase extends Database<Pharmacist> {
    private static final String FILE_PATH = "./data/user/pharmacist.txt";

    PharmacistDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of PharmacistDatabase.
     *
     * @return a new instance of PharmacistDatabase
     */
    public static PharmacistDatabase getInstance() {
        return new PharmacistDatabase();
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
            getAll().add(new Pharmacist(map));
        }
    }
}
