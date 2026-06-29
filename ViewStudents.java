package com.mycompany.finalproject_ap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ViewStudents {

    
    // Display View Students screen
    public void show(Stage stage, String doctorName) {

        // Title
        Label title = new Label("View Students");
        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        // Create table
        TableView<Student> table =
                new TableView<>();

        // Name column
        TableColumn<Student, String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        // ID column
        TableColumn<Student, String> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        // Major column
        TableColumn<Student, String> majorCol =
                new TableColumn<>("Major");

        majorCol.setCellValueFactory(
                new PropertyValueFactory<>("major")
        );

        // Grade column
        TableColumn<Student, String> gradeCol =
                new TableColumn<>("Grade");

        gradeCol.setCellValueFactory(
                new PropertyValueFactory<>("grade")
        );

        // Add columns to table
        table.getColumns().addAll(
                nameCol,
                idCol,
                majorCol,
                gradeCol
        );

        // Store students
        ArrayList<Student> students =
                new ArrayList<>();

        try {

            // Open students file
            BufferedReader studentReader =
                    new BufferedReader(
                            new FileReader("students.txt")
                    );

            String line;

            // Read file line by line
            while ((line = studentReader.readLine()) != null) {

                // Ignore empty lines
                if (line.trim().isEmpty()) {

                    continue;
                }

                // Split data using comma
                String[] studentData =
                        line.split(",");

                // Check valid data
                if (studentData.length < 4) {

                    continue;
                }

                // Student information
                String name = studentData[0];
                String id = studentData[1];
                String major = studentData[2];
                String grade = studentData[3];

                // Add student object
                students.add(
                        new Student(
                                name,
                                id,
                                major,
                                grade
                        )
                );
            }

            studentReader.close();

        } catch (IOException e) {

            // Error message
            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");

            alert.setContentText(
                    "Error reading students file"
            );

            alert.showAndWait();
        }

        // Add students to table
        table.getItems().addAll(students);

        // Resize columns automatically
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        // Back button
        Button backButton =
                new Button("Back");

        // Return to dashboard
        backButton.setOnAction(e -> {

            Dashboard dashboardPage =
                    new Dashboard(doctorName);

            dashboardPage.show(stage);

        });

        // Layout
        VBox layout = new VBox();

        layout.getChildren().addAll(
                title,
                table,
                backButton
        );

        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene =
                new Scene(layout, 700, 500);

        stage.setTitle("View Students");
        stage.setScene(scene);
        stage.show();
    }
}
