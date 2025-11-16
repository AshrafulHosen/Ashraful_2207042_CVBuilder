package com.example.cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CVFormController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea projectsArea;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    @FXML
    public void handleGenerateCV() {
        try {
            // Validate required fields
            if (fullNameField.getText().trim().isEmpty()) {
                showError("Full Name is required");
                return;
            }

            // Load the preview screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cv_preview.fxml"));
            Parent root = loader.load();

            // Get the controller and pass data
            CVPreviewController previewController = loader.getController();
            previewController.setCVData(
                    fullNameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    addressField.getText().trim(),
                    educationArea.getText().trim(),
                    skillsArea.getText().trim(),
                    experienceArea.getText().trim(),
                    projectsArea.getText().trim()
            );

            // Switch to preview scene
            Stage stage = (Stage) fullNameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Preview");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error generating CV preview");
        }
    }

    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) fullNameField.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}