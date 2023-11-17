package com.example.practicare.Controller.Patient_Feature;

import java.time.LocalDate;
import java.time.LocalTime;

import PC_object.Database;
import PC_object.Medicine;

import PC_object.MedicineRecord;
import PC_object.Patient;
import com.example.practicare.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;


public class MedicineOrder { // implements Initializable
    private static LinkedList<Medicine> list_purchased_med = new LinkedList<>();
    private Medicine selectedMedicine, cart_selectedMedicine;
    @FXML
    private Button add_itemagain, back_to_menupatient, checkout_medicine, back_to_medicineorders, paynow_medicine, done_to_menupatient;

    @FXML
    private TextField amount_medicine;

    @FXML
    private TableView<Medicine> list_medicine;
    @FXML
    private TableView<Medicine> list_orderedmedicine;

    @FXML
    private ChoiceBox<String> paymenttype_medicine;

    @FXML
    private TableColumn<Medicine, String> name_medicine;

    @FXML
    private TableColumn<Medicine, String> price_medicine;

    @FXML
    private TableColumn<Medicine, String> category_medicine;

    @FXML
    private TableColumn<Medicine, String> dose_medicine;

    @FXML
    private TableColumn<Integer, String> dose_orderedmedicine;

    @FXML
    private TableColumn<Integer, String> category_orderedmedicine;

    @FXML
    private TableColumn<Integer, String> name_orderedmedicine;

    @FXML
    private TableColumn<Integer, String> price_orderedmedicine;

    @FXML
    private Label total_checkout;

    @FXML
    private Button remove_cart;

    @FXML
    void initialize() {
        if (total_checkout != null) {
            int checkout = 0;
            for (int a = 0; a < list_purchased_med.size(); a++) {
                checkout = checkout + list_purchased_med.get(a).getAmount() * list_purchased_med.get(a).getPrice();
            }
            total_checkout.setText("$" + String.valueOf(checkout));
        }
        if (paymenttype_medicine != null) {
            ObservableList<String> pay_type = FXCollections.observableArrayList(
                    "Cash", "Credit"
            );

            paymenttype_medicine.setItems(pay_type);
        }
        if (list_medicine != null) {
            list_medicine.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            name_medicine.setCellValueFactory(new PropertyValueFactory<>("name"));
            price_medicine.setCellValueFactory(new PropertyValueFactory<>("price_display"));
            category_medicine.setCellValueFactory(new PropertyValueFactory<>("category"));
            dose_medicine.setCellValueFactory(new PropertyValueFactory<>("dose"));

            ObservableList<Medicine> medicineList = FXCollections.observableArrayList(
                    new Medicine("Paracetamol", "Analgesik, Antipiretik", "Every 4 - 6 hours", 5),
                    new Medicine("Amoxicillin", "Antibiotik", "Every 8 hours", 10),
                    new Medicine("Omeprazole", "PPI", "Before breakfast", 15),
                    new Medicine("Loratadine", "Antihistamin", "Once per day", 10),
                    new Medicine("Metformin", "Antidiabetes", "Depends on \npatient condition", 20),
                    new Medicine("Ibuprofen", "Analgesik, Antiinflamasi nonsteroid", "Every 4 - 6 hours", 10),
                    new Medicine("Cetirizine", "Antihistamin", "Once per day", 10),
                    new Medicine("Simvastatin", "Statin", "Depends on \npatient condition", 20),
                    new Medicine("Metoprolol", "Beta blocker", "Depends on \npatient condition", 20),
                    new Medicine("Fluoxetine", "Antidepresan", "Depends on \npatient condition", 20),
                    new Medicine("Aspirin", "Analgesik, Antipiretik", "Once per day", 5),
                    new Medicine("Diphenhydramine", "Antihistamin", "Before sleep", 10),
                    new Medicine("Gabapentin", "Antikonvulsan", "Depends on \npatient condition", 20),
                    new Medicine("Prednisone", "Steroid", "Depends on \npatient condition", 10),
                    new Medicine("Diazepam", "Benzodiazepine", "Depends on \npatient condition", 10)
            );
            list_medicine.setItems(medicineList);
        }
        if (list_orderedmedicine != null) {

            name_orderedmedicine.setCellValueFactory(new PropertyValueFactory<>("name"));
            price_orderedmedicine.setCellValueFactory(new PropertyValueFactory<>("total_display"));
            category_orderedmedicine.setCellValueFactory(new PropertyValueFactory<>("category"));
            dose_orderedmedicine.setCellValueFactory(new PropertyValueFactory<>("dose"));

            ObservableList<Medicine> checkout_list = FXCollections.observableArrayList(list_purchased_med);
            list_orderedmedicine.setItems(checkout_list);
        }
    }

    @FXML
    void handleRowClicked(MouseEvent event) {
        if (event.getClickCount() == 1) { // Check for single click
            selectedMedicine = list_medicine.getSelectionModel().getSelectedItem();

        }
    }

    @FXML
    void Add_ItemAgain(ActionEvent event) throws IOException {
        try {
            if (selectedMedicine != null) {
                selectedMedicine.setAmount(Integer.parseInt(amount_medicine.getText()));
                selectedMedicine.CalculateTotalIndividu(); // hitung subtotal!
                if (Integer.parseInt(amount_medicine.getText()) <= 0 || Objects.equals(amount_medicine.getText(), "")) {
                    Alert("Empty!", "Empty Amount", "Please fill the amount of Medicine!");
                    return;
                } else {
                    if (selectedMedicine.getAmount() > 50) {
                        Alert("Error", "Overload", "The amount of Medicine can't exceed 50!");
                        return;
                    } else {
                        list_purchased_med.add(selectedMedicine);
                        Success("Success!", "Item(s) Added", selectedMedicine.getName() + " is successfully added!");
                    }
                }
            } else {
                Alert("Empty!", "Empty Action", "Please make sure to pick one type of Medicine!");
                return;
            }
        } catch (NumberFormatException e) {
            Alert("Error", "Incorrect Input!", "Please check your inputted data!");
            return;
        }

        amount_medicine.clear();
        HelloApplication.SwitchScene("medicine_orders");
    }

    @FXML
    void Back_To_MenuPatient(ActionEvent event) throws IOException {
        list_purchased_med.clear();
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void CheckOut_Medicine(ActionEvent event) throws IOException {
        try {
            if (selectedMedicine != null) {
                selectedMedicine.setAmount(Integer.parseInt(amount_medicine.getText()));
                selectedMedicine.CalculateTotalIndividu();
                if (Integer.parseInt(amount_medicine.getText()) <= 0 || Objects.equals(amount_medicine.getText(), "")) {
                    Alert("Empty!", "Empty Amount", "Please fill the amount of Medicine!");
                    return;
                } else {
                    if (selectedMedicine.getAmount() > 50) {
                        Alert("Error", "Overload", "The amount of Medicine can't exceed 50!");
                        return;
                    } else {
                        list_purchased_med.add(selectedMedicine);
                        Success("Success!", "Item(s) Added", selectedMedicine.getName() + " is successfully added!");
                    }
                }
            } else {
                if (list_purchased_med.isEmpty()) {
                    Alert("Empty!", "Empty Cart", "Please make sure to pick one type of Medicine!");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            Alert("Error", "Incorrect Input!", "Please check your inputted data!");
            return;
        }

        amount_medicine.clear();
        HelloApplication.SwitchScene("payment_medicine");
    }

    @FXML
    void Back_To_MedicineOrders(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("medicine_orders");
    }

    @FXML
    void PayNow_Medicine(ActionEvent event) throws IOException {
        if (paymenttype_medicine.getValue() != null) {
            if (!list_purchased_med.isEmpty()) {
                // MASUK RECORD
                Database.getCurrentPatient().getList_record().add(new MedicineRecord(LocalDate.now(), list_purchased_med, (String)paymenttype_medicine.getValue()));

                list_purchased_med.clear();
                paymenttype_medicine.setValue(null);
                HelloApplication.SwitchScene("payment_medicine01");
            } else {
                Alert("Error!", "No Medicine in cart!", "Please add at least 1 medicine!");
                return;
            }

        } else {
            Alert("Error", "No Payment Detail", "Please select your payment type!");
            return;
        }
    }

    @FXML
    void Done_To_MenuPatient(ActionEvent event) throws IOException {
        HelloApplication.SwitchScene("menu_patient");
    }

    @FXML
    void handleRowClicked01(MouseEvent event) {
        if (event.getClickCount() == 1) {
            cart_selectedMedicine = list_orderedmedicine.getSelectionModel().getSelectedItem();

        }
    }

    @FXML
    void RemoveFromCart(ActionEvent event) throws IOException {
        if (cart_selectedMedicine != null) {
            for (int a = 0; a < list_purchased_med.size(); a++) {
                if (cart_selectedMedicine == list_purchased_med.get(a)) {
                    list_purchased_med.remove(a);
                    Success("Success", "Successfully removed", "The selected medicine is successfully removed from your cart!");
                    HelloApplication.SwitchScene("payment_medicine");
                }
            }
        } else {
            Alert("Error", "No Medicine is Selected", "Please select one to be removed!");
            return;
        }
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
