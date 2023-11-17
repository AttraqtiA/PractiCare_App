package com.example.practicare.Controller.Doctor_Feature;

import PC_object.Database;
import PC_object.Doctor;
import com.example.practicare.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.*;

public class AccountDoc {
    private static String email, password, fullname, address, gender, specialist;
    // static khusus untuk ngehandle pindah method dari sign in 1 ke sign in 2 supaya tidak tereset
    private Long age, phone_number, work_exp;
    @FXML
    private Button signup_menu, signin_menu;
    @FXML
    private TextField address_doc, age_doc, fullname_doc, phone_doc, workexp_doc, email_signin_doc, email_signup_doc;
    @FXML
    private PasswordField password_signup_doc, password_signin_doc;
    @FXML
    private Button back_to_signupdoctor, signup_doc, back_to_doctor, signin_doc, next_to_signupdoctor01, back_role;
    @FXML
    private ChoiceBox<String> gender_doc, speciality_doc;


    @FXML
    void initialize() {
        if (speciality_doc != null) {
            ObservableList<String> specialistList = FXCollections.observableArrayList(
                    "General Practitioner",
                    "Pediatric",
                    "Dentist",
                    "Veterinarian",
                    "Nutritionist",
                    "Ophthalmologist",
                    "Neurologist",
                    "ENT Specialist",
                    "Internist",
                    "Radiologist",
                    "Anesthetic"
            );

            speciality_doc.setItems(specialistList);
        }
        if (gender_doc != null) {
            ObservableList<String> genderlist = FXCollections.observableArrayList(
                    "Male", "Female"
            );
            gender_doc.setItems(genderlist);
        }
    }

    @FXML
    void SignUp_Menu(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_doctor");
    }
    @FXML
    void SignIn_Menu(ActionEvent event) throws IOException {
        if (Database.getList_doctor().isEmpty()) {
            AlertSignIn();
            return;
        } else {
            HelloApplication.SwitchScene("sign-in_doctor");
        }
    }
    @FXML
    void BacktoChooseRole(ActionEvent event) throws IOException{
        HelloApplication.SwitchScene("choose_role");
    }
    @FXML
    void Next_To_SignUpDoctor01(ActionEvent event) throws IOException {
        email = email_signup_doc.getText();
        password = password_signup_doc.getText();

        if (Database.getList_doctor().size() > 0) {
            for (int a = 0; a < Database.getList_doctor().size(); a++) {
                if (Objects.equals(email_signup_doc.getText(), Database.getList_doctor().get(a).getEmail())) {
                    AlertTemplate("Error", "Email already registered!", "Please type in another email or Sign In instead!");
                    return;
                }
            }
        }

        if (Objects.equals(email_signup_doc.getText(), "") || Objects.equals(password_signup_doc.getText(), "")) {
            Alert();
            return;
        }
        if (!email_signup_doc.getText().contains("@") || email_signup_doc.getText().length() <= 5) {
            Alert();
            return;
        }
        if (password_signup_doc.getText().length() < 8 || !isPasswordStrong(password_signup_doc.getText())) {
            AlertPassword();
            return;
        }

        HelloApplication.SwitchScene("sign-up_doctor01");
    }

    @FXML
    void SignUp_Doc(ActionEvent event) throws IOException {
        fullname = fullname_doc.getText();
        address = address_doc.getText();
        gender = (String) gender_doc.getValue();
        specialist = (String) speciality_doc.getValue();

        try {
            phone_number = Long.parseLong(phone_doc.getText());
            age = Long.parseLong(age_doc.getText());
            work_exp = Long.parseLong(workexp_doc.getText());

            if (Objects.equals(fullname_doc.getText(), "") || Objects.equals(address_doc.getText(), "") || (String) gender_doc.getValue() == null || (String) speciality_doc.getValue() == null) {
                Alert();
                return;
            }
            if (Double.parseDouble(workexp_doc.getText()) < 1) {
                AlertWorkExp();
                return;
            }
        } catch (NumberFormatException e) {
            Alert();
            return;
        }
        Doctor temp_doctor = new Doctor(email, password, fullname, address, gender, age, phone_number, specialist, work_exp);
        Database.getList_doctor().add(temp_doctor);

        Database.setCurrent_doctor(Database.getList_doctor().get(Database.getList_doctor().size() - 1));
        //ganti ke string
        Database.getCurrent_doctor().setWork_exp(work_exp);

        HelloApplication.SwitchScene("patient_management_doctor");
    }

    @FXML
    void Back_To_Doctor(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("doctor");
    }

    @FXML
    void SignIn_Doc(ActionEvent event) throws IOException {

        if (Objects.equals(email_signin_doc.getText(), "") || Objects.equals(password_signin_doc.getText(), "") || !email_signin_doc.getText().contains("@")) {
            Alert();
            return;
        } else {
            boolean userFound = false;

            for (Doctor doctor : Database.getList_doctor()) {
                if (Objects.equals(email_signin_doc.getText(), doctor.getEmail())) {
                    if (Objects.equals(password_signin_doc.getText(), doctor.getPassword())) {
                        Database.setCurrent_doctor(doctor);
                        userFound = true;
                        HelloApplication.SwitchScene("patient_management_doctor");
                        break;
                    } else {
                        AlertEmail();
                        return;
                    }
                }
            }
            if (!userFound) {
                AlertEmail();
                return;
            }
        }

    }

    @FXML
    void Back_To_SignUpDoctor(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_doctor");
    }

    private boolean isPasswordStrong(String password) {
        boolean hasDigit = password.matches(".*\\d.*"); // regex untuk at least 1 digit
        boolean hasAlphabet = password.matches(".*[A-Za-z].*");
        return hasDigit && hasAlphabet;
    }

    public void Alert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Input Incorrect!");
        alert.setContentText("Please check your inputted data!");
        alert.showAndWait();
    }

    public void AlertEmail() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Email or Password is Incorrect or not Found!");
        alert.setContentText("Please correct your Email and Password!");
        alert.showAndWait();
    }

    private void AlertPassword() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Password Incorrect!");
        alert.setContentText("Password must contain at least 8 characters with alphabet and number!");
        alert.showAndWait();
    }
    private void AlertSignIn() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("No Account is Registered!");
        alert.setContentText("Be the first Doctor of PractiCare!");
        alert.showAndWait();
    }
    public void AlertWorkExp() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Not Qualified!");
        alert.setContentText("Minimum work experience of 1 year!");
        alert.showAndWait();
    }

    public void AlertTemplate(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
        alert.showAndWait();
    }
}
