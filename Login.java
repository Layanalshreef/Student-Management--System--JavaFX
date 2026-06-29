package com.mycompany.finalproject_ap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login {

    public void show(Stage stage) {

        // Title
        Label title = new Label(
                "   Umm Al-Qura University\nStudent Management System"
        );
        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        // Doctor name
        Label nameLabel = new Label("Doctor Name");
        TextField nameField = new TextField();
        nameField.setMaxWidth(200);

        // Doctor ID
        Label idLabel = new Label("Doctor ID");
        PasswordField idField = new PasswordField();
        idField.setMaxWidth(200);

        // Course name
        Label courseLabel = new Label("Course Name");
        TextField courseField = new TextField();
        courseField.setMaxWidth(200);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(150);

        // Open dashboard screen
        loginButton.setOnAction(e -> {

            Dashboard dashboardPage =
                    new Dashboard(nameField.getText());

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
                courseLabel,
                courseField,
                loginButton
        );

        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(layout, 500, 400);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }
}
