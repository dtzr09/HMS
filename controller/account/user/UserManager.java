package controller.account.user;

import database.user.AdministratorDatabase;
import database.user.DoctorDatabase;
import database.user.PatientDatabase;
import database.user.PharmacistDatabase;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Patient;
import model.user.Pharmacist;
import model.user.User;
import model.user.UserType;
import utils.exceptions.ModelNotFoundException;

public class UserManager {

    private static User findDoctor(String emailID) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByEmail(emailID);
    }

    private static User findPatient(String emailID) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByEmail(emailID);
    }

    private static User findPharmacist(String emailID) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByEmail(emailID);
    }

    private static User findAdministrator(String emailID) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByEmail(emailID);
    }

    public static User findUser(String userID, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctor(userID);
            case PATIENT -> findPatient(userID);
            case PHARMACIST -> findPharmacist(userID);
            case ADMINISTRATOR -> findAdministrator(userID);
        };
    }

    private static void updatePharmacist(Pharmacist pharmacist) throws ModelNotFoundException {
        PharmacistDatabase.getDB().update(pharmacist);
    }

    private static void updateDoctor(Doctor doctor) throws ModelNotFoundException {
        DoctorDatabase.getDB().update(doctor);
    }

    private static void updatePatient(Patient patient) throws ModelNotFoundException {
        PatientDatabase.getDB().update(patient);
    }

    private static void updateAdministrator(Administrator administrator) throws ModelNotFoundException {
        AdministratorDatabase.getDB().update(administrator);
    }

    public static void updateUser(User user) throws ModelNotFoundException {
        if (user instanceof Doctor) {
            updateDoctor((Doctor) user);
        } else if (user instanceof Patient) {
            updatePatient((Patient) user);
        } else if (user instanceof Pharmacist) {
            updatePharmacist((Pharmacist) user);
        } else if (user instanceof Administrator) {
            updateAdministrator((Administrator) user);
        }
    }

}
