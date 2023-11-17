package com.example.practicare.Controller.Patient_Feature;

import PC_object.Database;
import PC_object.Doctor;
import PC_object.RecordClass;
import com.example.practicare.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RecordController {

    @FXML
    private Button back_to_menupatient;

    @FXML
    private TableView<RecordClass> transaction_history;

    @FXML
    private TableColumn<RecordClass, String> category_transaction;

    @FXML
    private TableColumn<RecordClass, String> date_transaction;

    @FXML
    private TableColumn<RecordClass, Integer> no_ID_transaction;

    @FXML
    private TableColumn<RecordClass, String> details_transaction;

    @FXML
    void Back_To_MenuPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void initialize() {
        if (transaction_history != null) {
            no_ID_transaction.setCellValueFactory(new PropertyValueFactory<>("no_ID"));
            category_transaction.setCellValueFactory(new PropertyValueFactory<>("category"));
            date_transaction.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
            details_transaction.setCellValueFactory(new PropertyValueFactory<>("details"));
            ObservableList<RecordClass> record_history = FXCollections.observableArrayList(Database.getCurrentPatient().getList_record());

            for (int a = 0; a< record_history.size(); a++) {
                record_history.get(a).setNo_ID(a + 1);
            }
            transaction_history.setItems(record_history);
        }
    }
}
