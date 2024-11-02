package AccountManager;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private String email;
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.medicalRecords = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updatePersonalInfo(String newEmail) {
        this.email = newEmail;
        System.out.println("Email updated to: " + newEmail);
    }

    public void addMedicalRecord(String diagnosis, String treatment, String date) {
        medicalRecords.add(new MedicalRecord(diagnosis, treatment, date));
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}

