package view.java_fx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class OpretBrugerController extends MenuController{
    public TextField cPRTF;
    public TextField fornavnTF;
    public TextField efterNavnTF;
    public TextField mobilTF;
    public TextField telefonTF;
    public TextField emailTF;
    public Button opretPatientBtn;
    public Button opretnBehandlerBtn;
    private Stage stage;
    private Main main;

    public void load(Main main, Stage stage){
        //setting references
        this.stage = stage;
        this.main = main;
    }

    public void opretBruger(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        boolean erBehandler = false;
        if (actionEvent.getSource().equals(opretnBehandlerBtn)) {
            erBehandler = true;
        }
        String cpr = cPRTF.getText();
        String fornavn = fornavnTF.getText();
        String efternavn = efterNavnTF.getText();
        String mobil = mobilTF.getText();
        String telefon = telefonTF.getText();
        String email = emailTF.getText();
        Main.viewModel.opretBruger(cpr,fornavn,efternavn,mobil,telefon,email,erBehandler);
        main.start(stage);
    }
}
