package ui.java_fx;

import entities.Patient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JavafxMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Patient patient = new Patient();
        Scene scene = new Scene(new Label(patient.getName()),500,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
