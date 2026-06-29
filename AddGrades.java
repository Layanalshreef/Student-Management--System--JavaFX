
package com.mycompany.finalproject_ap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class AddGrades {

    // Display Add Grades screen
    public void show(Stage stage, String doctorName) {

        // Title
        Label title = new Label("Add Grades");

        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        // Student selection
        Label studentLabel =
                new Label("Select Student");

        ComboBox<String> studentComboBox =
                new ComboBox<>();

        studentComboBox.setPrefWidth(250);

        // Load students from file
        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("students.txt")
                    );

            String line;

            // Read file line by line
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Add student names to ComboBox
                studentComboBox.getItems().add(data[0]);
            }

            reader.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        // Grade input
        Label gradeLabel =
                new Label("Enter Grade");

        TextField gradeField =
                new TextField();

        gradeField.setMaxWidth(250);

        // Buttons
        Button saveButton =
                new Button("Save Grade");

        saveButton.setPrefWidth(150);

        Button backButton =
                new Button("Back");

        backButton.setPrefWidth(150);

        // Save grade action
        saveButton.setOnAction(e -> {

            try {

                // Get selected student
                String studentName =
                        studentComboBox.getValue();

                // Get entered grade
                double grade =
                        Double.parseDouble(
                                gradeField.getText()
                        );

                // Check grade range
                if (grade < 0 || grade > 100) {

                    Alert alert =
                            new Alert(Alert.AlertType.ERROR);

                    alert.setContentText(
                            "Grade must be between 0 and 100"
                    );

                    alert.show();
                    return;
                }

                // Store updated students
                ArrayList<String> students =
                        new ArrayList<>();

                BufferedReader reader =
                        new BufferedReader(
                                new FileReader("students.txt")
                        );

                String line;

                // Read all students
                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(",");

                    // Update selected student grade
                    if (data[0].equals(studentName)) {

                        data[3] =
                                String.valueOf(grade);

                        line =
                                data[0] + "," +
                                data[1] + "," +
                                data[2] + "," +
                                data[3];
                    }

                    students.add(line);
                }

                reader.close();

                // Rewrite updated data
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter("students.txt")
                        );

                for (String student : students) {

                    writer.write(student);
                    writer.newLine();
                }

                writer.close();

                // Success message
                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );

                alert.setContentText(
                        "Grade saved successfully"
                );

                alert.show();

                // Clear fields
                gradeField.clear();
                studentComboBox.setValue(null);

            } catch (NumberFormatException ex) {

                // Invalid number alert
                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setContentText(
                        "Please enter a valid number"
                );

                alert.show();

            } catch (Exception ex) {

                ex.printStackTrace();
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
                studentLabel,
                studentComboBox,
                gradeLabel,
                gradeField,
                saveButton,
                backButton
        );

        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene =
                new Scene(layout, 500, 400);

        stage.setTitle("Add Grades");
        stage.setScene(scene);
        stage.show();
    }
}
