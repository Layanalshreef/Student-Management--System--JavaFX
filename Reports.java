
package com.mycompany.finalproject_ap;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reports {


    public void show(Stage stage, String doctorName) {

        // Title of the reports screen
        Label title = new Label("Reports");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Table to display students information
        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

        TableColumn<Student, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getColumns().addAll(nameCol, idCol, majorCol, gradeCol);

        // Variables for report calculations
        int totalStudents = 0;
        int passedStudents = 0;
        int failedStudents = 0;
        double totalGrades = 0;
        double highestGrade = 0;

        // Read students data from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 4) {
                    String name = data[0];
                    String id = data[1];
                    String major = data[2];
                    String gradeText = data[3];

                    // Add student to the table
                    table.getItems().add(new Student(name, id, major, gradeText));

                    double grade = Double.parseDouble(gradeText);

                    totalStudents++;
                    totalGrades += grade;

                    if (grade > highestGrade) {
                        highestGrade = grade;
                    }

                    if (grade >= 60) {
                        passedStudents++;
                    } else {
                        failedStudents++;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error reading students file");
            alert.showAndWait();
        }

        // Calculate the average grade
        double average = 0;

        if (totalStudents > 0) {
            average = totalGrades / totalStudents;
        }

        // Labels to show report results
        Label totalLabel = new Label("Number of Students: " + totalStudents);
        Label averageLabel = new Label("Average Grade: " + String.format("%.2f", average));
        Label highestLabel = new Label("Highest Grade: " + highestGrade);
        Label passedLabel = new Label("Passed Students: " + passedStudents);
        Label failedLabel = new Label("Failed Students: " + failedStudents);

        // Button to go back to dashboard
        Button backButton = new Button("Back");
        backButton.setPrefWidth(120);

        backButton.setOnAction(e -> {
            Dashboard dashboard = new Dashboard(doctorName);
            dashboard.show(stage);
        });

        // Main layout
        VBox root = new VBox(12);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                totalLabel,
                averageLabel,
                highestLabel,
                passedLabel,
                failedLabel,
                table,
                backButton
        );

        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
}
