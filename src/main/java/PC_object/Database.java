package PC_object;

import java.util.*;

public class Database {
    public static ArrayList<Patient> list_patient = new ArrayList<>();
    private static Patient current_patient;
    public static ArrayList<Doctor> list_doctor = new ArrayList<>();
    private static Doctor current_doctor;

    public static Doctor getCurrent_doctor() {
        return current_doctor;
    }
    public static void setCurrent_doctor(Doctor current_doctor) {
        Database.current_doctor = current_doctor;
    }
    public static ArrayList<Doctor> getList_doctor() {
        return list_doctor;
    }
    public static Patient getCurrentPatient() {
        return current_patient;
    }
    public static void setCurrentPatient(Patient patient) {
        current_patient = patient;
    }
    public static ArrayList<Patient> getList_patient() {
        return list_patient;
    }
}
