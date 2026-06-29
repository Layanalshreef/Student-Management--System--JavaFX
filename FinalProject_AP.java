
package com.mycompany.finalproject_ap;

import javafx.application.Application;
import javafx.stage.Stage;

public class FinalProject_AP extends Application{

    @Override
    public void start(Stage primaryStage) {

        Login loginPage = new Login();
        loginPage.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}