package com.example.practicare.Controller;

import com.example.practicare.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class SwitchRole {

    @FXML
    private Button doctor_role;
    @FXML
    private Button patient_role;
    @FXML
    void RoleDoctorPicked(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("doctor");
    }

    @FXML
    void RolePatientPicked(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("patient");
    }

}