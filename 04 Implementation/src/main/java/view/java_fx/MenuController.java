package view.java_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public abstract class MenuController {
    private Main main;
    @FXML
    private Button mineForloeb;

    public void forside(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/java_fx/Forside.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage.setScene(new Scene(root));
    }

    public void mineForloebSide(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/java_fx/MineForloeb.fxml"));
        Parent mineForloebParent = (Parent) fxmlLoader.load();
        MineForloebController mineForloebController = fxmlLoader.getController();
        mineForloebController.ListViewForloeb();
        stage.setScene(new Scene(mineForloebParent));
    }

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

    public void opretBruger(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        //Parent root = new FXMLLoader(getClass().getResource("/view/java_fx/OpretForloeb.fxml")).load();
        //use fxml loader to get elements associated with the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/java_fx/OpretBruger.fxml"));
        Parent root = loader.load();
        OpretBrugerController opretBrugerController = loader.getController();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        opretBrugerController.load(main, primaryStage);
    }

    public void setMain(Main main){
        this.main = main;
    }
}
