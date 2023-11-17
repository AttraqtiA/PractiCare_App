package com.example.practicare.Controller.Patient_Feature;

import PC_object.Database;
import PC_object.Doctor;
import PC_object.Patient;
import PC_object.Pet;
import com.example.practicare.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AccountPat {
    private static String email, password, fullname, address, gender, marital_status;
    private static LocalDate date_of_birth;
    private static Long phone_number, relative_phone_number;
    private String pet_name, pet_gender;
    private Long pet_age;
    private ToggleGroup havepettoggle;
    @FXML
    private Button signup_menu, signin_menu;
    @FXML
    private TextField address_pat, fullname_pat, phone_pat, relativephone_pat,
            email_signin_pat, email_signup_pat, age_pet, name_pet;
    @FXML
    private PasswordField password_signup_pat, password_signin_pat;
    @FXML
    private Button back_role, back_to_signuppatient, next_to_signuppatient02, signin_pat, back_to_patient,
            next_to_signuppatient01, back_to_signuppatient01, next_to_signuppatient03, back_to_signuppatient02, signup_pat;
    @FXML
    private DatePicker datebirth_pat;
    @FXML
    private ChoiceBox<String> gender_pat, status_pat, gender_pet;
    @FXML
    private RadioButton have_pet, havent_pet;

    @FXML
    void initialize() {
        if (datebirth_pat != null) {
            datebirth_pat.setOnAction(event -> {
                LocalDate temp = datebirth_pat.getValue();

            });
        }
        if (gender_pat != null || gender_pet != null) {
            ObservableList<String> genderlist = FXCollections.observableArrayList(
                    "Male", "Female"
            );
            if (gender_pat != null) {
                gender_pat.setItems(genderlist);
            } else {
                gender_pet.setItems(genderlist);
            }
        }
        if (status_pat != null) {
            ObservableList<String> marriage_status = FXCollections.observableArrayList(
                    "Single", "Married"
            );
            status_pat.setItems(marriage_status);
        }
        if (have_pet != null && havent_pet != null) {
            havepettoggle = new ToggleGroup();

            have_pet.setToggleGroup(havepettoggle);
            havent_pet.setToggleGroup(havepettoggle);
        }

    }

    @FXML
    void SignUp_Menu(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_patient");
    }

    @FXML
    void SignIn_Menu(ActionEvent event) throws IOException {
        if (Database.getList_patient().isEmpty()) {
            AlertSignIn();

        } else {
            HelloApplication.SwitchScene("sign-in_patient");
        }
    }

    @FXML
    void BacktoChooseRole(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("choose_role");
    }

    @FXML
    void Next_To_SignUpPatient01(ActionEvent event) throws IOException {
        email = email_signup_pat.getText();
        password = password_signup_pat.getText();
        if (Database.getList_patient().size() > 0) {
            for (int a = 0; a < Database.getList_patient().size(); a++) {
                if (Objects.equals(email_signup_pat.getText(), Database.getList_patient().get(a).getEmail())) {
                    AlertTemplate("Error", "Email already registered!", "Please type in another email or Sign In instead!");
                    return;
                }
            }
        }
        if (Objects.equals(email_signup_pat.getText(), "") || Objects.equals(password_signup_pat.getText(), "")) {
            Alert();
            return;
        }
        if (!email_signup_pat.getText().contains("@") || email_signup_pat.getText().length() <= 5) {
            Alert();
            return;
        }
        if (password_signup_pat.getText().length() < 8 || !isPasswordStrong(password_signup_pat.getText())) {
            AlertPassword();
            return;
        }

        HelloApplication.SwitchScene("sign-up_patient01");
    }

    @FXML
    void Back_To_Patient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("patient");
    }

    @FXML
    void Back_To_SignUpPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_patient");
    }

    @FXML
    void Next_To_SignUpPatient02(ActionEvent event) throws IOException {
        fullname = fullname_pat.getText();
        address = address_pat.getText();
        date_of_birth = datebirth_pat.getValue();

        gender = (String) gender_pat.getValue();
        marital_status = (String) status_pat.getValue();

        try {
            phone_number = Long.parseLong(phone_pat.getText());
            relative_phone_number = Long.parseLong(relativephone_pat.getText());
            if (Objects.equals(fullname_pat.getText(), "") || Objects.equals(address_pat.getText(), "") || datebirth_pat.getValue() == null || (String) gender_pat.getValue() == null || (String) status_pat.getValue() == null) {
                Alert();
                return;
            }
        } catch (NumberFormatException e) {
            Alert();
            return;
        }

        HelloApplication.SwitchScene("sign-up_patient02");
    }

    @FXML
    void Back_To_SignUpPatient01(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_patient01");
    }

    @FXML
    void Next_To_SignUpPatient03(ActionEvent event) throws IOException {
        if (have_pet.isSelected()) {
            HelloApplication.SwitchScene("sign-up_patient03");
        } else if (havent_pet.isSelected()) {
            Patient temp_patient = new Patient(email, password, fullname, address, gender, marital_status, phone_number, relative_phone_number, date_of_birth);
            Database.getList_patient().add(temp_patient);
            Database.setCurrentPatient(Database.getList_patient().get(Database.getList_patient().size() - 1));

            HelloApplication.SwitchScene("menu_patient");
        }
    }

    @FXML
    void Back_To_SignUpPatient02(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("sign-up_patient02");
    }

    @FXML
    void signup_pat(ActionEvent event) throws IOException {
        pet_name = name_pet.getText();
        pet_gender = (String) gender_pet.getValue();

        try {
            pet_age = Long.parseLong(age_pet.getText());
            if (Objects.equals(pet_name, "") || pet_gender == null) {
                Alert();
                return;
            }
        } catch (NumberFormatException e) {
            Alert();
            return;
        }

        Pet cleo = new Pet(pet_name, pet_gender, pet_age); // cleo adalah anjing dari Louis Fernando :3
        Patient temp_patient = new Patient(email, password, fullname, address, gender, marital_status, phone_number, relative_phone_number, date_of_birth, cleo);

        Database.getList_patient().add(temp_patient);
        Database.setCurrentPatient(Database.getList_patient().get(Database.getList_patient().size() - 1));

        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void SignIn_Pat(ActionEvent event) throws IOException {

        if (Objects.equals(email_signin_pat.getText(), "") || Objects.equals(password_signin_pat.getText(), "") || !email_signin_pat.getText().contains("@")) {
            Alert();
            return;
        } else {
            boolean userFound = false;
            for (Patient patient : Database.getList_patient()) {
                if (Objects.equals(email_signin_pat.getText(), patient.getEmail())) {
                    if (Objects.equals(password_signin_pat.getText(), patient.getPassword())) {
                        userFound = true;
                        Database.setCurrentPatient(patient);
                        Success("Success", "Successfully signed in!", "Welcome! " + Database.getCurrentPatient().getFullname());
                        HelloApplication.SwitchScene("menu_patient");
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

    private boolean isPasswordStrong(String password) {
        boolean hasDigit = password.matches(".*\\d.*"); // regex untuk at least 1 digit
        boolean hasAlphabet = password.matches(".*[A-Za-z].*");
        return hasDigit && hasAlphabet;
    }

    public void Alert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Incorrect!");
        alert.setContentText("Please check your inputted data!");
        alert.showAndWait();
    }

    public void AlertEmail() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Email or Password is Incorrect or not Found!");
        alert.setContentText("Please correct your Email and Password!");
        alert.showAndWait();
    }

    public void AlertTemplate(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
        alert.showAndWait();
    }

    private void AlertPassword() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Password Incorrect!");
        alert.setContentText("Password must contain at least 8 characters with alphabet and number");
        alert.showAndWait();
    }

    private void AlertSignIn() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No Account is Registered!");
        alert.setContentText("Be the first Member of PractiCare!");
        alert.showAndWait();
    }
    public void Success(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
        alert.showAndWait();
    }
}
