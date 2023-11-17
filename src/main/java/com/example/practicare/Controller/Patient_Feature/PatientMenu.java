package com.example.practicare.Controller.Patient_Feature;

import PC_object.Database;
import PC_object.Patient;
import com.example.practicare.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

public class PatientMenu {

    @FXML
    private Button edit_patientpersonaldata, go_to_docappointmentarr, go_to_medicineorders,
            go_to_petpersonaldata, go_to_record, logout_to_chooserole;

    @FXML
    private Label displayname_patient;

    @FXML
    private Button back_to_menupatient;

    @FXML
    private TextField edit_address_patient;

    @FXML
    private DatePicker edit_datebirth_patient;

    @FXML
    private TextField edit_fullname_patient;

    @FXML
    private ChoiceBox<String> edit_gender_patient;


    @FXML
    private TextField edit_phone_patient;

    @FXML
    private TextField edit_relativephone_patient;

    @FXML
    private ChoiceBox<String> edit_status_patient;

    @FXML
    private Button save_patientpersonaldata;

    @FXML
    private TextField edit_age_pet;

    @FXML
    private ChoiceBox<String> edit_gender_pet;

    @FXML
    private TextField edit_name_pet;

    @FXML
    private Button save_petpersonaldata;

    @FXML
    void initialize() {

        if (displayname_patient != null) {
            displayname_patient.setText(Database.getCurrentPatient().getFullname());
        }
        if (edit_fullname_patient != null) {
            edit_fullname_patient.setText(Database.getCurrentPatient().getFullname());
            edit_address_patient.setText(Database.getCurrentPatient().getAddress());

            edit_datebirth_patient.setValue(Database.getCurrentPatient().getDate_of_birth());
            edit_gender_patient.setValue(Database.getCurrentPatient().getGender());

            ObservableList<String> genderlist = FXCollections.observableArrayList(
                    "Male", "Female"
            );
            edit_gender_patient.setItems(genderlist);

            edit_status_patient.setValue(Database.getCurrentPatient().getMarital_status());
            ObservableList<String> marriedlist = FXCollections.observableArrayList(
                    "Single", "Married"
            );
            edit_status_patient.setItems(marriedlist);
            edit_phone_patient.setText(String.valueOf(Database.getCurrentPatient().getPhone_number()));
            edit_relativephone_patient.setText(String.valueOf(Database.getCurrentPatient().getRelative_phone_number()));
        }
        if (edit_name_pet != null) {
            edit_name_pet.setText(Database.getCurrentPatient().getPet().getName());
            edit_age_pet.setText(String.valueOf(Database.getCurrentPatient().getPet().getAge()));
            edit_gender_pet.setValue(Database.getCurrentPatient().getPet().getGender());

            ObservableList<String> genderlist = FXCollections.observableArrayList(
                    "Male", "Female"
            );
            edit_gender_pet.setItems(genderlist);
        }
    }

    @FXML
    void Edit_PatientPersonalData(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("personal_data_patient");
    }

    @FXML
    void Go_To_DocAppointmentArr(ActionEvent event) throws IOException {
        if (Database.getList_doctor().isEmpty()) {
            AlertNoDoctor();
            return;
        } else {
            HelloApplication.SwitchScene("choose_check-up");
        }
    }

    @FXML
    void Go_To_MedicineOrders(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("medicine_orders");
    }

    @FXML
    void Go_To_PetPersonalData(ActionEvent event) throws IOException {
        if(Database.getCurrentPatient().getPet() != null) {
            HelloApplication.SwitchScene("personal_data_pet");
        } else {
            Alert("No Pet", "No pet is found", "You didn't register any pet!");
        }
    }

    @FXML
    void Go_To_Record(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("record_patient");
    }

    @FXML
    void LogOut_To_ChooseRole(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("choose_role");
    }
    @FXML
    void Back_To_MenuPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void Save_PatientPersonalData(ActionEvent event) throws IOException {
        try {
            Database.getCurrentPatient().setFullname(edit_fullname_patient.getText());
            Database.getCurrentPatient().setAddress(edit_address_patient.getText());
            Database.getCurrentPatient().setDate_of_birth(edit_datebirth_patient.getValue());
            Database.getCurrentPatient().setGender(edit_gender_patient.getValue());
            Database.getCurrentPatient().setMarital_status(edit_status_patient.getValue());
            Database.getCurrentPatient().setPhone_number(Long.parseLong(edit_phone_patient.getText()));
            Database.getCurrentPatient().setRelative_phone_number(Long.parseLong(edit_relativephone_patient.getText()));

            if (Objects.equals(edit_fullname_patient.getText(), "") || Objects.equals(edit_address_patient.getText(), "") || edit_datebirth_patient.getValue() == null ||(String) edit_gender_patient.getValue() == null || (String) edit_status_patient.getValue() == null) {
                Alert("Error!", "Input is still empty!", "Please fill in the input with a value!");
                return;
            }
        } catch (NumberFormatException e) {
            Alert("Error!", "Input Incorrect!", "Please check your inputted data!");
            return;
        }

        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void Save_PetPersonalData(ActionEvent event) throws IOException {
        try {
            Database.getCurrentPatient().getPet().setName(edit_name_pet.getText());
            Database.getCurrentPatient().getPet().setAge(Long.parseLong(String.valueOf(edit_age_pet.getText())));
            Database.getCurrentPatient().getPet().setGender(edit_gender_pet.getValue());

            if (Objects.equals(edit_name_pet.getText(), "") || (String) edit_gender_pet.getValue() == null) {
                Alert("Error!", "Input is still empty!", "Please fill in the input with a value!");
                return;
            }
        } catch (NumberFormatException e) {
            Alert("Error!", "Input Incorrect!", "Please check your inputted data!");
            return;
        }

        HelloApplication.SwitchScene("menu_patient");
    }

    public void Alert(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
        alert.showAndWait();
    }

    private void AlertNoDoctor() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Maintenance");
        alert.setContentText("Hang in There, the Service will be ready in a moment!");
        alert.showAndWait();
    }
}
