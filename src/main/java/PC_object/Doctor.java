package PC_object;

import java.time.LocalDate;
import java.util.*;

public class Doctor {
    private static ArrayList<CheckUpDay> list_date = new ArrayList<>();
    private String email;
    private String password;
    private String fullname;
    private String specialist;
    private String address;
    private String gender;
    private Long age, phone_number, work_exp;
    private String stringwork_exp;

    private int booking_count;

    public Doctor (String email, String password, String fullname, String address, String gender, Long age, Long phone_number, String specialist, Long work_exp) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.phone_number = phone_number;
        this.specialist = specialist;
        this.work_exp = work_exp;

        this.booking_count = 0;
    }
    public void setWork_exp(long work_exp) {
        this.work_exp = work_exp;
        if (this.work_exp > 1) {
            this.stringwork_exp = this.work_exp + "years";
        } else {
            this.stringwork_exp = this.work_exp + "year";
        }
    }

    public void addBooking_count() {
        this.booking_count = this.booking_count + 1;
    }

    public void resetBooking() {
        this.booking_count = 0;
    }

    public void addCheckUpDate(LocalDate date) {
        list_date.add(new CheckUpDay(date));
    }
    public ArrayList<CheckUpDay> getList_date() {
        return list_date;
    }
    public Long getWork_exp(){
        return work_exp;
    }
    public String getSpecialist(){
        return specialist;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getFullname(){
        return fullname;
    }
    public String getAddress(){
        return address;
    }
    public String getGender(){
        return gender;
    }
    public Long getAge() {
        return age;
    }
    public Long getPhone_number(){
        return phone_number;
    }
    public void setList_date(ArrayList<CheckUpDay> list_date) {
        this.list_date = list_date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getStringwork_exp() {
        return stringwork_exp;
    }
    public void setStringwork_exp(String stringwork_exp) {
        this.stringwork_exp = stringwork_exp;
    }
    public int getBooking_count() {
        return booking_count;
    }
}