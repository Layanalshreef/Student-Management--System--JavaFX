package com.mycompany.finalproject_ap;

import com.mycompany.finalproject_ap.AddGrades;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
    import javafx.stage.Stage;

public class Dashboard {

    // Store doctor name
    private String doctorName;

    // Constructor to receive doctor name
    public Dashboard(String doctorName) {
        this.doctorName = doctorName;
    }

    public void show(Stage stage) {

        // Page title
        Label title = new Label("Dashboard");

        // Welcome message
        Label welcomeLabel = new Label(
                "Welcome Dr. " + doctorName
        );

        welcomeLabel.setStyle(
                "-fx-font-size: 16px; -fx-font-weight: bold;"
        );

        // Create buttons ****
        
        Button addStudent = new Button("Add Student");
        addStudent.setPrefWidth(200);

        Button addGrades = new Button("Add Grades");
        addGrades.setPrefWidth(200);

        Button viewStudents = new Button("View Students");
        viewStudents.setPrefWidth(200);

        Button reports = new Button("Reports");
        reports.setPrefWidth(200);

        Button logout = new Button("Logout");
        logout.setPrefWidth(200);

        
        // Add Student button action
        
        addStudent.setOnAction(e -> {

            AddStudent addStudentPage = new AddStudent();
            addStudentPage.show(stage, doctorName);

        });

        // Add Grades button action
        
        addGrades.setOnAction(e -> {

            AddGrades addGradesPage = new AddGrades();
            addGradesPage.show(stage, doctorName);

        });

        // View Students button action
        
        viewStudents.setOnAction(e -> {

            ViewStudents viewPage = new ViewStudents();
            viewPage.show(stage, doctorName);

        });

        // Reports button action
        
        reports.setOnAction(e -> {

            Reports reportsPage = new Reports();
            reportsPage.show(stage, doctorName);

        });

        // Logout button action
        
        logout.setOnAction(e -> {

            Login loginPage = new Login();
            loginPage.show(stage);

        });

        // Create layout
        
        VBox layout = new VBox();
        layout.getChildren().addAll(
                welcomeLabel,
                title,
                addStudent,
                addGrades,
                viewStudents,
                reports,
                logout
        );

        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        // Create scene
        Scene scene = new Scene(layout, 500, 400);

        // Set stage properties
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}