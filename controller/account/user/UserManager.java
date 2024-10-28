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

    private static User findDoctor(String email) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByEmail(email);
    }

    private static User findPatient(String email) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByEmail(email);
    }

    private static User findPharmacist(String email) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByEmail(email);
    }

    private static User findAdministrator(String email) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByEmail(email);
    }

    public static User findUser(String email, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctor(email);
            case PATIENT -> findPatient(email);
            case PHARMACIST -> findPharmacist(email);
            case ADMINISTRATOR -> findAdministrator(email);
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
