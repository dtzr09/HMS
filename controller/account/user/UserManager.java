package controller.account.user;

import java.util.UUID;

import database.user.AdministratorDatabase;
import database.user.DoctorDatabase;
import database.user.PatientDatabase;
import database.user.PharmacistDatabase;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Patient;
import model.user.PersonalInfo;
import model.user.Pharmacist;
import model.user.User;
import model.user.UserType;
import utils.exceptions.ModelAlreadyExistsException;
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

    private static void createDoctor(Doctor doctor) throws ModelAlreadyExistsException {
        DoctorDatabase.getDB().add(doctor);
    }

    private static void createPatient(Patient patient) throws ModelAlreadyExistsException {
        PatientDatabase.getDB().add(patient);
    }

    private static void createPharmacist(Pharmacist pharmacist) throws ModelAlreadyExistsException {
        PharmacistDatabase.getDB().add(pharmacist);
    }

    private static void createAdministrator(Administrator administrator) throws ModelAlreadyExistsException {
        AdministratorDatabase.getDB().add(administrator);
    }

    public static void createUser(String email, String name, UserType userType, String password)
            throws ModelAlreadyExistsException {
        PersonalInfo personalInfo = new PersonalInfo(name, email);
        String userID = UUID.randomUUID().toString();
        User user = switch (userType) {
            case DOCTOR -> new Doctor(userID, password, personalInfo);
            case PATIENT -> new Patient(userID, personalInfo, password);
            case PHARMACIST -> new Pharmacist(userID, personalInfo, password);
            case ADMINISTRATOR -> new Administrator(userID, personalInfo, password);
        };
        if (user instanceof Doctor doctor) {
            createDoctor(doctor);
        } else if (user instanceof Patient patient) {
            createPatient(patient);
        } else if (user instanceof Pharmacist pharmacist) {
            createPharmacist(pharmacist);
        } else if (user instanceof Administrator administrator) {
            createAdministrator(administrator);
        }
    }

}
