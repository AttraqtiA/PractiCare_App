package PC_object;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

public class CheckUpRecord extends RecordClass{
    private Doctor doctor;
    private String symptoms;
    private String start, end;
    private String schedule_date;
    public CheckUpRecord (LocalDate transaction_date, Doctor doctor, String symptoms, LocalDate local_schedule_date, String start, String end){
        // Doctor name, symptoms
        super(transaction_date);
        this.category = "Check-Up";
        this.doctor = doctor;
        this.symptoms = symptoms;
        this.schedule_date = local_schedule_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.start = start;
        this.end = end;
        this.details = doctor.getFullname() + " \nOn : " + schedule_date + "\n" + this.start + " - " + this.end + " \n" + this.symptoms;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
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

    public String getScheduledate() {
        return schedule_date;
    }

    public void setScheduledate(String scheduledate) {
        this.schedule_date = scheduledate;
    }
}
