package PC_object;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private String start, end;
    private Patient patient;
    private String status, symptoms, duration;

    public Schedule(String start, String end) {
        this.start = start;
        this.end = end;
        this.status = "Available";
        this.duration = "30 minutes";
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
