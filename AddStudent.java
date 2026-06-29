
package com.mycompany.finalproject_ap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddStudent {

    // Display Add Student screen
    public void show(Stage stage, String doctorName) {

        // Title
        Label title = new Label("Add New Student");
        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        // Student name
        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();
        nameField.setMaxWidth(250);

        // Student ID
        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setMaxWidth(250);

        // Major
        Label majorLabel = new Label("Major:");

        TextField majorField = new TextField();
        majorField.setMaxWidth(250);

        // Buttons
        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(150);

        Button backButton = new Button("Back to Dashboard");
        backButton.setPrefWidth(150);

        // Save student data
        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String major = majorField.getText().trim();
            
            // Check empty fields
            if (name.isEmpty() || id.isEmpty() || major.isEmpty()) {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Please fill in all fields!"
                );

                alert.showAndWait();
                return;
            }

            // Save student data to file
            try (BufferedWriter writer =
                         new BufferedWriter(
                                 new FileWriter(
                                         "students.txt",
                                         true
                                 )
                         )) {

                writer.write(
                        name + "," +
                        id + "," +
                        major + "," +
                        "Not Added"
                );

                writer.newLine();

                // Success message
                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Success");
                alert.setHeaderText(null);

                alert.setContentText(
                        "Student data saved successfully!"
                );

                alert.showAndWait();

                // Clear fields
                nameField.clear();
                idField.clear();
                majorField.clear();

            } catch (IOException ex) {

                // Error message
                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("File Error");

                alert.setContentText(
                        "An error occurred while saving."
                );

                alert.showAndWait();
            }
        });

        // Back to dashboard
        backButton.setOnAction(e -> {

            Dashboard dashboardPage =
                    new Dashboard(doctorName);

            dashboardPage.show(stage);

        });

        // Layout
        VBox layout = new VBox();

        layout.getChildren().addAll(
                title,
                nameLabel,
                nameField,
                idLabel,
                idField,
                majorLabel,
                majorField,
                saveButton,
                backButton
        );

        layout.setSpacing(12);
        layout.setAlignment(Pos.CENTER);
        
        // Scene
        Scene scene = new Scene(layout, 500, 400);

        stage.setTitle("Add Student");
        stage.setScene(scene);
        stage.show();
    }

}

