package database.appointment;

import java.util.List;
import java.util.Map;

import database.Database;
import model.appointment.AppointmentOutcome;

public class AppointmentOutcomeDatabase extends Database<AppointmentOutcome> {

    private static final String FILE_PATH = "./data/user/appointmentOutcome.txt";

    AppointmentOutcomeDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static AppointmentOutcomeDatabase getDB() {
        return new AppointmentOutcomeDatabase();
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
            getAll().add(new AppointmentOutcome(map));
        }
    }

    public List<AppointmentOutcome> getAllAppointmentOutcomes() {
        return super.getAll();
    }

}
