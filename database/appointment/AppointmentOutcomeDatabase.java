package database.appointment;

import java.util.List;
import java.util.Map;

import database.Database;
import model.appointment.AppointmentOutcome;
import model.appointment.enums.AppointmentOutcomeStatus;

public class AppointmentOutcomeDatabase extends Database<AppointmentOutcome> {

    private static final String FILE_PATH = "./data/appointment/appointmentOutcome.txt";

    AppointmentOutcomeDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of AppointmentOutcomeDatabase.
     *
     * @return a new instance of AppointmentOutcomeDatabase
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
            String appointmentOutcomeID = map.get("appointmentOutcomeID");
            String patientID = map.get("patientID");
            String doctorID = map.get("doctorID");
            String typeOfService = map.get("typeOfService");
            String consultationNotes = map.get("consultationNotes");
            String diagnosisID = map.get("diagnosisID");
            String appointmentID = map.get("appointmentID");
            String statusStr = map.get("status");

            AppointmentOutcomeStatus status = AppointmentOutcomeStatus.valueOf(statusStr);
            AppointmentOutcome outcome = new AppointmentOutcome(appointmentOutcomeID, patientID, doctorID,
                    typeOfService,
                    consultationNotes, diagnosisID, appointmentID, status);

            getAll().add(outcome);
        }
    }

    /**
     * Gets all appointment outcomes.
     *
     * @return a list of all appointment outcomes
     */
    public List<AppointmentOutcome> getAllAppointmentOutcomes() {
        return super.getAll();
    }

}
