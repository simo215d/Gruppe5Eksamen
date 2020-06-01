package view.java_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.persistence.firebase.FirebaseDAO;

import java.io.IOException;

public class ForsideController {
    private Main main;

    public void opretForloeb(ActionEvent event) throws IOException {
        //Parent root = new FXMLLoader(getClass().getResource("/view/java_fx/OpretForloeb.fxml")).load();
        //use fxml loader to get elements associated with the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/java_fx/OpretForloeb.fxml"));
        Parent root = loader.load();
        OpretForloebController opretForloebController = loader.getController();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        opretForloebController.load(main, primaryStage);
    }

    public void setMain(Main main){
        this.main = main;
    }
}
