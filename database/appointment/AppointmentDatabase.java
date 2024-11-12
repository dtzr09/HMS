package database.appointment;

import java.util.List;
import java.util.Map;

import database.Database;
import model.appointment.Appointment;

public class AppointmentDatabase extends Database<Appointment> {

    private static final String FILE_PATH = "./data/appointment/appointment.txt";

    AppointmentDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of AppointmentDatabase.
     *
     * @return a new instance of AppointmentDatabase
     */
    public static AppointmentDatabase getDB() {
        return new AppointmentDatabase();
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
            getAll().add(new Appointment(map));
        }
    }

    /**
     * Gets all appointments.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return super.getAll();
    }
}
