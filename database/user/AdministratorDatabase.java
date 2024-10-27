package database.user;

import java.util.List;
import java.util.Map;

import database.Database;
import model.user.Administrator;

public class AdministratorDatabase extends Database<Administrator> {

    private static final String FILE_PATH = "./data/user/administrator.txt";

    AdministratorDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of AdministratorDatabase.
     *
     * @return a new instance of AdministratorDatabase
     */
    public static AdministratorDatabase getInstance() {
        return new AdministratorDatabase();
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
            getAll().add(new Administrator(map));
        }
    }
}
