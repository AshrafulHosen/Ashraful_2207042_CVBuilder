package com.example.cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private int currentCvId = -1; // -1 means Create Mode, anything else means Update Mode

    public void setEditMode(CV cv) {
        this.currentCvId = cv.getId();
        fullNameField.setText(cv.getFullName());
        emailField.setText(cv.getEmail());
        phoneField.setText(cv.getPhone());
        addressField.setText(cv.getAddress());
        educationArea.setText(cv.getEducation());
        skillsArea.setText(cv.getSkills());
        experienceArea.setText(cv.getExperience());
        projectsArea.setText(cv.getProjects());
    }

    @FXML
    public void handleGenerateCV() {
        try {
            if (fullNameField.getText().trim().isEmpty()) {
                showError("Full Name is required");
                return;
            }

            boolean success;

            if (currentCvId == -1) {
                // Create New
                success = dbHandler.saveCV(
                        fullNameField.getText(), emailField.getText(), phoneField.getText(), addressField.getText(),
                        educationArea.getText(), skillsArea.getText(), experienceArea.getText(), projectsArea.getText()
                );
            } else {
                // Update Existing
                success = dbHandler.updateCV(
                        currentCvId,
                        fullNameField.getText(), emailField.getText(), phoneField.getText(), addressField.getText(),
                        educationArea.getText(), skillsArea.getText(), experienceArea.getText(), projectsArea.getText()
                );
            }

            if (success) {
                // Go to Preview
                loadPreview();
            } else {
                showError("Database Error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error processing CV.");
        }
    }

    private void loadPreview() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cv_preview.fxml"));
        Parent root = loader.load();

        CVPreviewController previewController = loader.getController();
        previewController.setCVData(
                fullNameField.getText(), emailField.getText(), phoneField.getText(), addressField.getText(),
                educationArea.getText(), skillsArea.getText(), experienceArea.getText(), projectsArea.getText()
        );

        Stage stage = (Stage) fullNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Preview");
        stage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    public void handleBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fullNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}