package view.java_fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.persistence.firebase.FirebaseDAO;
import viewModel.ViewModel;

import java.io.IOException;

import java.io.IOException;

public class Main extends Application {

    static ViewModel viewModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        viewModel = new ViewModel();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/java_fx/Forside.fxml"));
        Parent root = loader.load();
        ForsideController forsideController = loader.getController();
        forsideController.setMain(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}