package model.prescription;

public enum PrescriptionStatus {
    PENDING,     // Prescription is waiting to be processed
    DISPENSED,   // Prescription has been dispensed to the patient
    DECLINED     // Prescription was declined and not dispensed
}