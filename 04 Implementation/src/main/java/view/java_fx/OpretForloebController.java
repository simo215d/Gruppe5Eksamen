package view.java_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.datastrukture.Behandler;
import model.datastrukture.Patient;

import java.io.IOException;
import java.util.ArrayList;

public class OpretForloebController {
    private Main main;
    private Stage stage;
    //fxml elements
    private ComboBox<String> behandlercombobox;
    private ComboBox<String> patientcombobox;
    private ArrayList<Behandler> behandlere;
    private ArrayList<Patient> patienter;

    public void load(Main main, Stage stage){
        //setting references
        this.stage=stage;
        this.main = main;
        behandlercombobox = (ComboBox<String>) stage.getScene().lookup("#behandlercombobox");
        patientcombobox = (ComboBox<String>) stage.getScene().lookup("#patientcombobox");
        //loading combobox info
        setBehandlere();
        setPatienter();
    }

    //TODO VIEW MODEL SKAL BRUGES NU :!:!:!!::!!
    private void setBehandlere() {
        try {
            behandlere = main.getFirebaseDAO().hentBehandlere();
            for (Behandler behandler : behandlere){
                System.out.println("BEHANDLER LOADED: "+behandler.getEmail());
                behandlercombobox.getItems().add(behandler.getNavn());
                behandlercombobox.getSelectionModel().select(behandlere.get(0).getNavn());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setPatienter(){
        try {
            patienter = main.getFirebaseDAO().hentPatienter(false);
            for (Patient patient : patienter){
                System.out.println("PATIENT LOADED: "+patient.getEmail());
                patientcombobox.getItems().add(patient.getNavn());
            }
            patientcombobox.getSelectionModel().select(patienter.get(0).getNavn());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void opretForloeb(ActionEvent event) throws IOException {
        System.out.println("Behandler: "+behandlere.get(behandlercombobox.getSelectionModel().getSelectedIndex()).getNavn()+" Patient: "+patienter.get(patientcombobox.getSelectionModel().getSelectedIndex()).getNavn());
        try {
            main.getFirebaseDAO().opretForloeb(behandlere.get(behandlercombobox.getSelectionModel().getSelectedIndex()), patienter.get(patientcombobox.getSelectionModel().getSelectedIndex()));
            System.out.println("successfuldt oprettet forløb!");
            //back to front page
            main.start(stage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
