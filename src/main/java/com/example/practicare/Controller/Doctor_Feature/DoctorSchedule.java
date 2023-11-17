package com.example.practicare.Controller.Doctor_Feature;

import PC_object.Database;
import PC_object.Doctor;
import PC_object.Patient;
import PC_object.Schedule;
import com.example.practicare.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class DoctorSchedule {
    private static Patient lookup_patient;
    private int pointer = -1;
    private static Schedule selectedSchedule;
    @FXML
    private DatePicker date_practice;
    @FXML
    private Button edit_doctorpersonaldata;
    @FXML
    private Button next_to_lookuppatientdata;
    @FXML
    private Button find_schedule;
    @FXML
    private TableView<Schedule> list_schedule_checkup;
    @FXML
    private Label displayname_doctor;
    @FXML
    private Button logout_to_chooserole;
    @FXML
    private TableColumn<Schedule, String> start_checkup;
    @FXML
    private TableColumn<Schedule, String> end_checkup;
    @FXML
    private TableColumn<Schedule, String> status_checkup;
    @FXML
    private TableColumn<Schedule, String> duration_checkup;

    //CHECK PATIENT DATA
    @FXML
    private Button back_to_patientmanagement;

    @FXML
    private Button done_patientmanagement;

    @FXML
    private Label look_address_patient;

    @FXML
    private Label look_date_patient;

    @FXML
    private Label look_fullname_patient;

    @FXML
    private Label look_gender_patient;

    @FXML
    private Label look_symptoms_patient;

    @FXML
    private Label look_phone_patient;

    @FXML
    private Label look_relativephone_patient;

    @FXML
    private Label look_status_patient;

    @FXML
    private TextField edit_address_doctor;

    @FXML
    private TextField edit_age_doctor;

    @FXML
    private TextField edit_fullname_doctor;

    @FXML
    private ChoiceBox<String> edit_gender_doctor;

    @FXML
    private TextField edit_phone_doctor;

    @FXML
    private ChoiceBox<String> edit_speciality_doctor;

    @FXML
    private TextField edit_workexp_doctor;

    @FXML
    private Button save_doctorpersonaldata;

    @FXML
    void initialize() {
        if (list_schedule_checkup != null) {
            if (Database.getCurrent_doctor().getBooking_count() != 0) {
                Success("New Booking!", "You have " + Database.getCurrent_doctor().getBooking_count() + " new Booking from patients!", "Please check each date to have further information!");
                Database.getCurrent_doctor().resetBooking();
                return;
            }
        }
        if (displayname_doctor != null) {
            displayname_doctor.setText(Database.getCurrent_doctor().getFullname());
        }
        if (edit_gender_doctor != null) {
            ObservableList<String> genderlist = FXCollections.observableArrayList(
                    "Male", "Female"
            );
            edit_gender_doctor.setItems(genderlist);
        }
        if (edit_speciality_doctor != null) {
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

            edit_speciality_doctor.setItems(specialistList);
        }
        if (edit_fullname_doctor != null) {
            edit_fullname_doctor.setText(Database.getCurrent_doctor().getFullname());
            edit_address_doctor.setText(Database.getCurrent_doctor().getAddress());
            edit_gender_doctor.setValue(Database.getCurrent_doctor().getGender());
            edit_age_doctor.setText(String.valueOf(Database.getCurrent_doctor().getAge()));
            edit_phone_doctor.setText(String.valueOf((Database.getCurrent_doctor().getPhone_number())));
            edit_speciality_doctor.setValue(String.valueOf(Database.getCurrent_doctor().getSpecialist()));
            edit_workexp_doctor.setText(String.valueOf(Database.getCurrent_doctor().getWork_exp()));
        }

        if (look_fullname_patient != null) {
            look_fullname_patient.setText(lookup_patient.getFullname());
            look_address_patient.setText(lookup_patient.getAddress());
            look_date_patient.setText(lookup_patient.getDate_of_birth().toString());
            look_gender_patient.setText(lookup_patient.getGender());
            look_status_patient.setText(lookup_patient.getMarital_status());
            look_phone_patient.setText(String.valueOf(lookup_patient.getPhone_number()));
            look_relativephone_patient.setText(String.valueOf(lookup_patient.getRelative_phone_number()));
            look_symptoms_patient.setText(selectedSchedule.getSymptoms());
        }
    }

    @FXML
    void Edit_DoctorPersonalData(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("personal_data_doctor");
    }

    @FXML
    void FindSchedule(ActionEvent event) throws IOException {
        if (date_practice.getValue() != null) {
            boolean temp_exist_day = false;
            pointer = -1; //reset

            if (date_practice.getValue().isBefore(LocalDate.now())) {
                Alert("Error", "Date is Before Now", "Please choose a correct Date!");
                return;
            }

            for (int a = 0; a < Database.getCurrent_doctor().getList_date().size(); a++) {
                if (Database.getCurrent_doctor().getList_date().get(a).getDate() == date_practice.getValue()) {
                    pointer = a;

                    temp_exist_day = true;
                }
            }
            if (!temp_exist_day) {
                Database.getCurrent_doctor().addCheckUpDate(date_practice.getValue());
                pointer = Database.getCurrent_doctor().getList_date().size() - 1;
            }

            list_schedule_checkup.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            duration_checkup.setCellValueFactory(new PropertyValueFactory<>("duration"));
            status_checkup.setCellValueFactory(new PropertyValueFactory<>("status"));
            start_checkup.setCellValueFactory(new PropertyValueFactory<>("start"));
            end_checkup.setCellValueFactory(new PropertyValueFactory<>("end"));

            ObservableList<Schedule> schedule_list = FXCollections.observableArrayList(
                    Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule());

            list_schedule_checkup.setItems(schedule_list);
        } else {
            Alert("Error", "No Date Picked", "Please pick a date to show schedule!");
            return;
        }

    }

    @FXML
    void handleRowClicked01(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectedSchedule = list_schedule_checkup.getSelectionModel().getSelectedItem();
            if (selectedSchedule != null) {
                if (Objects.equals(selectedSchedule.getStatus(), "Reserved")) {
                    lookup_patient = selectedSchedule.getPatient();

                } else { // ini kalau "available"
                    Alert("Available", "No Patient Data Available", "Please pick a Reserved Schedule!");
                    list_schedule_checkup.getSelectionModel().clearSelection();
                    selectedSchedule = null;
                }
            }
        }
    }

    @FXML
    void Next_To_LookUpPatientData(ActionEvent event) throws IOException {
        if (selectedSchedule == null) {
            Alert("Error", "There is no patient on that day", "Please pick a correct schedule!");
        } else {

            HelloApplication.SwitchScene("lookup_patient_data");
        }
    }

    @FXML
    void LogOut_To_ChooseRole(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("choose_role");
    }

    @FXML
    void Back_ToPatientManagement(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("patient_management_doctor");
    }

    @FXML
    void Back_To_PatientMangement(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("patient_management_doctor");
    }
    @FXML
    void Done_PatientManagement(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("patient_management_doctor");
    }

    @FXML
    void Save_DoctorPersonalData(ActionEvent event) throws IOException {
        try {
            Database.getCurrent_doctor().setFullname(edit_fullname_doctor.getText());
            Database.getCurrent_doctor().setAddress(edit_address_doctor.getText());
            Database.getCurrent_doctor().setGender(edit_gender_doctor.getValue());
            Database.getCurrent_doctor().setAge(Long.parseLong(edit_age_doctor.getText()));
            Database.getCurrent_doctor().setPhone_number(Long.parseLong(edit_phone_doctor.getText()));
            Database.getCurrent_doctor().setSpecialist(edit_speciality_doctor.getValue());
            Database.getCurrent_doctor().setWork_exp(Long.parseLong(edit_workexp_doctor.getText()));

            if (Objects.equals(edit_fullname_doctor.getText(), "") || Objects.equals(edit_address_doctor.getText(), "") || (String) edit_gender_doctor.getValue() == null || (String) edit_speciality_doctor.getValue() == null) {
                Alert("Error!", "Input is still empty!", "Please fill in the input with a value!");
                return;
            }
            if (Double.parseDouble(edit_workexp_doctor.getText()) < 1) {
                Alert("Error", "Not Qualified!", "Minimum work experience of 1 year!");
                return;
            }
        } catch (NumberFormatException e) {
            Alert("Error!", "Input Incorrect!", "Please check your inputted data!");
            return;
        }

        HelloApplication.SwitchScene("patient_management_doctor");
    }

    public void Alert(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
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
