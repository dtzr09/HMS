package controller.user;

import java.util.List;
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
import model.user.enums.UserType;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;

public class UserManager {

    private static User findDoctorByEmail(String email) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByEmail(email);
    }

    private static User findPatientByEmail(String email) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByEmail(email);
    }

    private static User findPhamarcistByEmail(String email) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByEmail(email);
    }

    private static User findAdministratorByEmail(String email) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByEmail(email);
    }

    public static User findUser(String email, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctorByEmail(email);
            case PATIENT -> findPatientByEmail(email);
            case PHARMACIST -> findPhamarcistByEmail(email);
            case ADMINISTRATOR -> findAdministratorByEmail(email);
        };
    }

    private static User findDoctorById(String doctorID) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByID(doctorID);
    }

    private static User findPatientById(String patientID) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByID(patientID);
    }

    private static User findPharmacistById(String pharmacistID) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByID(pharmacistID);
    }

    private static User findAdministratorById(String administratorID) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByID(administratorID);
    }

    public static User findUserById(String userID, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctorById(userID);
            case PATIENT -> findPatientById(userID);
            case PHARMACIST -> findPharmacistById(userID);
            case ADMINISTRATOR -> findAdministratorById(userID);
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
        if (user instanceof Doctor doctor) {
            updateDoctor(doctor);
        } else if (user instanceof Patient patient) {
            updatePatient(patient);
        } else if (user instanceof Pharmacist pharmacist) {
            updatePharmacist(pharmacist);
        } else if (user instanceof Administrator administrator) {
            updateAdministrator(administrator);
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

    public static User createUser(String email, String name, UserType userType, String password)
            throws ModelNotFoundException, ModelAlreadyExistsException, UserAlreadyExistsException {

        if (findUser(email, userType) != null) {
            throw new UserAlreadyExistsException(userType, email);
        }

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
        return user;

    }

    public static List<Doctor> getDoctors() {
        return DoctorDatabase.getDB().getAllDoctors();
    }

    public static List<Administrator> getAdministrators() {
        return AdministratorDatabase.getDB().getAllAdministrators();
    }

    public static List<Pharmacist> getPharmacists() {
        return PharmacistDatabase.getDB().getAllPharmacist();
    }

    private static void removeDoctor(String doctorID) throws ModelNotFoundException {
        DoctorDatabase.getDB().remove(doctorID);
    }

    private static void removePharmacist(String pharmacistID) throws ModelNotFoundException {
        PharmacistDatabase.getDB().remove(pharmacistID);
    }

    private static void removeAdministrator(String administratorID) throws ModelNotFoundException {
        AdministratorDatabase.getDB().remove(administratorID);
    }

    private static void removePatient(String patientID) throws ModelNotFoundException {
        PatientDatabase.getDB().remove(patientID);
    }

    public static void removeUserByID(String staffID, UserType userType)
            throws ModelNotFoundException, UserCannotBeFoundException {
        if (findUserById(staffID, userType) == null) {
            throw new UserCannotBeFoundException(staffID, userType);
        }
        switch (userType) {
            case DOCTOR -> removeDoctor(staffID);
            case PATIENT -> removePatient(staffID);
            case PHARMACIST -> removePharmacist(staffID);
            case ADMINISTRATOR -> removeAdministrator(staffID);
        }
    }

}