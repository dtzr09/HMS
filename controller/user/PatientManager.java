package controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.medication.PrescriptionManager;
import database.user.PatientDatabase;
import model.appointment.Appointment;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;

public class PatientManager {
    public static List<Patient> getAllPatients() {
        return PatientDatabase.getDB().getAllPatients();
    }

    public static List<Patient> getPatientsOfDoctor(String doctorId) {
        List<Patient> patients = new ArrayList<>();
        List<Patient> allPatients = getAllPatients();
        for (Patient patient : allPatients) {
            if (patient.getDoctorID().equals(doctorId))
                patients.add(patient);
        }
        return patients;
    }

    public static Patient getPatientById(String patientId) {
        try {
            return PatientDatabase.getDB().getByID(patientId);
        } catch (ModelNotFoundException e) {
            System.out.println("Patient not found");
            return null;
        }
    }

    public static void addAllergy(Patient patient, String allergy) {
        try {
            ArrayList<String> allergies = patient.getAllergies();
            allergies.add(allergy);
            patient.setAllergies(allergies);
            PatientDatabase.getDB().update(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void addDiagnosis(String diagnosis, String patientID, String doctorID, Prescription prescription) {
        String diagnosisID = UUID.randomUUID().toString();
        Date dateOfDiagnosis = new Date();
        try {
            Diagnosis newDiagnosis = new Diagnosis(diagnosisID, diagnosis, doctorID, prescription, dateOfDiagnosis);
            Patient patient = getPatientById(patientID);
            patient.addDiagnosis(newDiagnosis);
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static Diagnosis getDiagnosisByID(Patient patient, String diagnosisID) {
        try {
            List<Diagnosis> diagnoses = patient.getDiagnosis();
            Diagnosis diagnosis = null;

            for (Diagnosis d : diagnoses) {
                if (d.getDiagnosisID().equals(diagnosisID)) {
                    diagnosis = d;
                    break;
                }
            }

            if (diagnosis == null) {
                throw new ModelNotFoundException();
            }

            return diagnosis;
        } catch (ModelNotFoundException e) {
            System.out.println("Diagnosis not found.");
        }
        return null;
    }

    public static void updateDignosis(String diagnosisID, String patientID, Prescription newPrescription) {
        try {
            Patient patient = getPatientById(patientID);
            List<Diagnosis> diagnoses = patient.getDiagnosis();
            for (Diagnosis diagnosis : diagnoses) {
                if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
                    diagnosis.setPrescription(newPrescription);
                    break;
                }
            }
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void updateDisease(String newDisease, String patientID, String diagnosisID) {
        try {
            Patient patient = getPatientById(patientID);
            List<Diagnosis> diagnoses = patient.getDiagnosis();
            for (Diagnosis diagnosis : diagnoses) {
                if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
                    diagnosis.setDisease(newDisease);
                    break;
                }
            }
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void updatePrescription(Diagnosis diagnosis, Patient patient, Prescription oldPrescription,
            ArrayList<String> MedicationIDs,
            String drugInstructions) {
        try {
            Prescription newPrescription = PrescriptionManager.updatePrescription(oldPrescription, MedicationIDs,
                    drugInstructions);
            updateDignosis(diagnosis.getDiagnosisID(), patient.getPatientID(), newPrescription);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static Appointment getAppointmentByID(String patientID, String appointmentID) {
        try {
            Patient patient = getPatientById(patientID);
            if (patient == null) {
                throw new ModelNotFoundException("Patient not found.");
            }
            List<Appointment> appointments = patient.getAppointments();
            Appointment appointment = null;

            for (Appointment a : appointments) {
                if (a.getAppointmentID().equals(appointmentID)) {
                    appointment = a;
                    break;
                }
            }

            if (appointment == null) {
                throw new ModelNotFoundException("Appointment not found.");
            }

            return appointment;
        } catch (ModelNotFoundException e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }
}
