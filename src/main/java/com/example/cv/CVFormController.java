package com.example.cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert; // Import added

public class CVFormController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea projectsArea;

    // Instantiate the handler
    private final DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    public void handleGenerateCV() {
        try {
            // 1. Validate required fields
            if (fullNameField.getText().trim().isEmpty()) {
                showError("Full Name is required");
                return;
            }

            // 2. Save to Database
            boolean isSaved = dbHandler.saveCV(
                    fullNameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    addressField.getText().trim(),
                    educationArea.getText().trim(),
                    skillsArea.getText().trim(),
                    experienceArea.getText().trim(),
                    projectsArea.getText().trim()
            );

            if (!isSaved) {
                showError("Failed to save CV to database. Proceeding with preview only.");
            } else {
                System.out.println("CV Saved successfully!");
            }

            // 3. Proceed to Preview (Existing code)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cv_preview.fxml"));
            Parent root = loader.load();

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
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