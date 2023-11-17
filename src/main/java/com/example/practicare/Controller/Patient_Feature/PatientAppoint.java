package com.example.practicare.Controller.Patient_Feature;

import PC_object.*;
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
import java.time.LocalTime;
import java.util.Objects;

public class PatientAppoint {
    private static boolean temp_mypet;
    private int pointer = -1;
    private ToggleGroup toggleselfpet;
    private Doctor selectedDoctor;
    private Schedule selectedSchedule;
    private LocalDate selectedDate;

    @FXML
    private Button back_to_menupatient;
    @FXML
    private RadioButton checkup_mypet;
    @FXML
    private RadioButton checkup_myself;
    @FXML
    private Button next_to_docappointmentarr;
    @FXML
    private Button done_to_menupatient;
    @FXML
    private Button back_to_choosedoctor;
    @FXML
    private Button back_to_docappointmentarr;
    @FXML
    private Button next_to_chooseschedule;
    @FXML
    private TableView<Doctor> list_doctor_ourown;
    @FXML
    private TableColumn<Doctor, String> address_doctor_ourown;
    @FXML
    private TableColumn<Doctor, String> gender_doctor_ourown;
    @FXML
    private TableColumn<Doctor, String> name_doctor_ourown;
    @FXML
    private TableColumn<Doctor, Long> phone_doctor_ourown;
    @FXML
    private TableColumn<Doctor, String> speciality_doctor_ourown;
    @FXML
    private TableColumn<Doctor, String> workexp_doctor_ourown;

    // halaman SCHEDULE
    @FXML
    private Button find_schedule;
    @FXML
    private Button book_checkup;
    @FXML
    private TextField checkup_symptoms;
    @FXML
    private DatePicker date_checkup;
    @FXML
    private TableView<Schedule> list_schedule_checkup;
    @FXML
    private TableColumn<Schedule, String> duration_checkup;
    @FXML
    private TableColumn<Schedule, String> status_checkup;
    @FXML
    private TableColumn<Schedule, String> start_checkup;
    @FXML
    private TableColumn<Schedule, String> end_checkup;

    @FXML
    void initialize() {
        if (date_checkup != null) {
            date_checkup.setOnAction(event -> {
                selectedDate = date_checkup.getValue();
            });
        }
        if (checkup_myself != null && checkup_mypet != null) {
            toggleselfpet = new ToggleGroup();

            checkup_myself.setToggleGroup(toggleselfpet);
            checkup_mypet.setToggleGroup(toggleselfpet);
        }
        if (list_doctor_ourown != null) {
            list_doctor_ourown.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            name_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            address_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("address"));
            gender_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("gender"));
            phone_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            speciality_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("specialist"));
            workexp_doctor_ourown.setCellValueFactory(new PropertyValueFactory<>("stringwork_exp"));
            ObservableList<Doctor> petdoctor_list = FXCollections.observableArrayList();


            if (temp_mypet) {
                for (int a = 0; a < Database.getList_doctor().size(); a++) {
                    if (Objects.equals(Database.getList_doctor().get(a).getSpecialist(), "Veterinarian")) {
                        petdoctor_list.add(Database.getList_doctor().get(a));
                    }
                }
            } else { // no vetenarian
                for (int a = 0; a < Database.getList_doctor().size(); a++) {
                    if (!Objects.equals(Database.getList_doctor().get(a).getSpecialist(), "Veterinarian")) {
                        petdoctor_list.add(Database.getList_doctor().get(a));
                    }
                }
            }

            list_doctor_ourown.setItems(petdoctor_list);
        }
    }

    @FXML
    void handleRowClicked(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectedDoctor = list_doctor_ourown.getSelectionModel().getSelectedItem();

        }
    }

    @FXML
    void Back_To_MenuPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void Next_To_DocAppointmentArr(ActionEvent event) throws IOException {
        temp_mypet = false;
        if (checkup_myself.isSelected()) {
            temp_mypet = false;
        } else if (checkup_mypet.isSelected()) {
            if (Database.getCurrentPatient().getPet() == null) {
                Alert("Pet-less", "No Pet is Found!", "You Have no Pet!");
                return;
            } else {
                temp_mypet = true;
            }
        }

        HelloApplication.SwitchScene("choose-doctor_ourown");
    }

    @FXML
    void Back_To_DocAppointmentArr(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML         // TOMBOL TABEL DOCTOR
    void Next_To_ChooseSchedule(ActionEvent event) throws IOException {
        if (selectedDoctor != null) {
            for (int a = 0; a < Database.getList_doctor().size(); a++) { // PASTIKAN YANG DIUTAK ATIK YANG DI DATABASE, JANGAN YANG BARU
                if (selectedDoctor == Database.getList_doctor().get(a)) {
                    Database.setCurrent_doctor(Database.getList_doctor().get(a));
                }
            }
        } else {
            Alert("Empty!", "No Doctor is Selected", "Please make sure to pick one Doctor!");
            return;
        }
        HelloApplication.SwitchScene("choose_schedule");
    }

    @FXML
    void FindSchedule(ActionEvent event) {
        if (date_checkup.getValue() != null) {
            boolean temp_exist_day = false;
            pointer = -1; //reset

            if (date_checkup.getValue().isBefore(LocalDate.now())) {
                Alert("Error", "Date is Before Now", "Please choose a correct Date!");
                return;
            }

            for (int a = 0; a < Database.getCurrent_doctor().getList_date().size(); a++) {
                if (Database.getCurrent_doctor().getList_date().get(a).getDate() == selectedDate) {
                    pointer = a;

                    temp_exist_day = true;
                }
            }
            if (!temp_exist_day) {
                Database.getCurrent_doctor().addCheckUpDate(date_checkup.getValue());
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
                    Alert("Reserved", "Reserved Schedule", "Please pick other schedule that is Available!");

                    // menolak kalau reserved!
                    list_schedule_checkup.getSelectionModel().clearSelection();
                    selectedSchedule = null;
                    return;
                }
            }
        }
    }

    @FXML
    void Back_To_ChooseDoctor(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("choose-doctor_ourown");
    }

    @FXML
    void Book_CheckUp(ActionEvent event) throws IOException {

        if (selectedSchedule != null) {
            if (Objects.equals(checkup_symptoms.getText(), "")) {
                Alert("Empty!", "No Symptoms detail", "Please explain your Symptoms!!");
                return;
            } else {
                for (int a = 0; a < Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule().size(); a++) {
                    if (Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule().get(a) == selectedSchedule) {
                        Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule().get(a).setSymptoms(
                                checkup_symptoms.getText());

                        Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule().get(a).setStatus(
                                "Reserved");

                        Database.getCurrent_doctor().getList_date().get(pointer).getList_schedule().get(a).setPatient(
                                Database.getCurrentPatient());

                        // TAMBAH KE RECORD
                        Database.getCurrentPatient().getList_record().add(new CheckUpRecord(LocalDate.now(), Database.getCurrent_doctor(), checkup_symptoms.getText(), selectedDate, selectedSchedule.getStart(), selectedSchedule.getEnd()));

                        // TAMBAH BOOKING COUNT UNTUK NOTIFICATION
                        Database.getCurrent_doctor().addBooking_count();
                    }
                }
            }

        } else {
            Alert("Empty!", "No Schedule is Selected", "Please make sure to pick one Schedule!");
            return;
        }

        checkup_symptoms.clear();
        HelloApplication.SwitchScene("payment_doctor");
    }

    @FXML
    void Done_To_MenuPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    public void Alert(String title, String header, String desc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(desc);
        alert.showAndWait();
    }
}
