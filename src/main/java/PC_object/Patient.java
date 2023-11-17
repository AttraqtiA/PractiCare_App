package PC_object;

import com.example.practicare.Controller.Patient_Feature.RecordController;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.*;

public class Patient {
    private String email, password, fullname, address, gender, marital_status;
    private Long phone_number, relative_phone_number;
    private LocalDate date_of_birth;
    private Pet pet;
    private static ArrayList<RecordClass> list_record = new ArrayList<>();

    public Patient(String email, String password, String fullname, String address, String gender, String marital_status, Long phone_number, Long relative_phone_number, LocalDate date_of_birth, Pet cleo) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.marital_status = marital_status;
        this.phone_number = phone_number;
        this.relative_phone_number = relative_phone_number;
        this.date_of_birth = date_of_birth;
        this.pet = cleo;
    }

    //no Pet
    public Patient(String email, String password, String fullname, String address, String gender, String marital_status, Long phone_number, Long relative_phone_number, LocalDate date_of_birth) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.marital_status = marital_status;
        this.phone_number = phone_number;
        this.relative_phone_number = relative_phone_number;
        this.date_of_birth = date_of_birth;
    }

    public ArrayList<RecordClass> getList_record() {
        return list_record;
    }
    public static void setList_record(ArrayList<RecordClass> list_record) {
        Patient.list_record = list_record;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMarital_status() {
        return marital_status;
    }
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public Long getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }
    public Long getRelative_phone_number() {
        return relative_phone_number;
    }
    public void setRelative_phone_number(Long relative_phone_number) {
        this.relative_phone_number = relative_phone_number;
    }
    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet cleo) {
        this.pet = cleo;
    }


}
