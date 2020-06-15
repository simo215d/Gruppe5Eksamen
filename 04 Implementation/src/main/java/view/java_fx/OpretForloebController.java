package view.java_fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.datastrukture.Behandler;
import model.datastrukture.BehandlerImpl;
import model.datastrukture.Patient;
import model.datastrukture.PatientImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class OpretForloebController extends MenuController {
    private Main main;
    private Stage stage;
    //fxml elements
    private ComboBox<String> behandlercombobox;
    private ComboBox<String> patientcombobox;
    private ArrayList<Behandler> behandlere;
    private ArrayList<Patient> patienter;

    public void load(Main main, Stage stage) throws IOException {
        //setting references
        Main.viewModel.listenToUserChange();
        Main.viewModel.observeFirebase(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setBehandlere();
                    }
                });
                System.out.println("OBSERVER UPDATED: behandlere");
            }
        });
        Main.viewModel.observeFirebase(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setPatienter();
                    }
                });
                System.out.println("OBSERVER UPDATED: patienter");
            }
        });
        this.stage=stage;
        this.main = main;
        behandlercombobox = (ComboBox<String>) stage.getScene().lookup("#behandlercombobox");
        patientcombobox = (ComboBox<String>) stage.getScene().lookup("#patientcombobox");
        //loading combobox info
        //setBehandlere();
        //setPatienter();
    }

    //TODO VIEW MODEL SKAL BRUGES NU :!:!:!!::!!
    private void setBehandlere() {
        behandlercombobox.getItems().clear();
        try {
            behandlere = Main.viewModel.hentBehandlere();
            for (Behandler behandler : behandlere){
                //System.out.println("BEHANDLER LOADED: "+behandler.getEmail());
                behandlercombobox.getItems().add(behandler.getNavn());
                behandlercombobox.getSelectionModel().select(behandlere.get(0).getNavn());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setPatienter(){
        patientcombobox.getItems().clear();
        try {
            patienter = Main.viewModel.hentPatienter(false);
            for (Patient patient : patienter){
                //System.out.println("PATIENT LOADED: "+patient.getEmail());
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
            Main.viewModel.opretForloeb(behandlere.get(behandlercombobox.getSelectionModel().getSelectedIndex()), patienter.get(patientcombobox.getSelectionModel().getSelectedIndex()));
            System.out.println("successfuldt oprettet forløb!");
            //back to front page
            main.start(stage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
