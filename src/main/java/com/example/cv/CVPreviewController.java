package com.example.cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CVPreviewController {

    @FXML private Label fullNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    @FXML private TextArea educationPreview;
    @FXML private TextArea skillsPreview;
    @FXML private TextArea experiencePreview;
    @FXML private TextArea projectsPreview;

    @FXML private VBox previewContainer;

    public void setCVData(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects) {
        fullNameLabel.setText(fullName);
        emailLabel.setText(email);
        phoneLabel.setText(phone);
        addressLabel.setText(address);

        educationPreview.setText(education);
        skillsPreview.setText(skills);
        experiencePreview.setText(experience);
        projectsPreview.setText(projects);
    }

    @FXML
    public void handleBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) fullNameLabel.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBackToForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cv_form.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) fullNameLabel.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Create");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}