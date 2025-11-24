package com.example.cv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CVHistoryController {

    @FXML private TableView<CV> cvTable;
    @FXML private TableColumn<CV, String> nameColumn;
    @FXML private TableColumn<CV, String> emailColumn;

    private final DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadData();
    }

    private void loadData() {
        ObservableList<CV> list = FXCollections.observableArrayList(dbHandler.getAllCVs());
        cvTable.setItems(list);
    }

    @FXML
    public void handleDelete() {
        CV selected = cvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a CV to delete.");
            return;
        }
        if (dbHandler.deleteCV(selected.getId())) {
            loadData();
        } else {
            showAlert("Error deleting CV.");
        }
    }

    @FXML
    public void handleEdit() {
        CV selected = cvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a CV to edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cv_form.fxml"));
            Parent root = loader.load();

            CVFormController controller = loader.getController();
            controller.setEditMode(selected);

            Stage stage = (Stage) cvTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit CV");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) cvTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}