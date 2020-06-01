package view.java_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ForsideController {

    public Button mineForloeb;

    public void mineForloebSide(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/java_fx/MineForloeb.fxml"));
        Parent mineForloebParent = (Parent) fxmlLoader.load();
        MineForloebController mineForloebController = fxmlLoader.getController();
        mineForloebController.ListViewForloeb();
        stage.setScene(new Scene(mineForloebParent));
    }
}
