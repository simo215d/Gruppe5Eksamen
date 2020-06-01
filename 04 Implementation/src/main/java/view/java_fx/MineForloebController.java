package view.java_fx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.datastrukture.Patient;
import model.persistence.firebase.FirebaseDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MineForloebController {

    @FXML
    private TableView<Patient> TableViewForloeb;

    @FXML
    private TableColumn<Patient, String> EmailView;

    @FXML
    private TableColumn<Patient, String> NavnView;

    public void ListViewForloeb() throws InterruptedException, ExecutionException, IOException {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        ArrayList<Patient> list = firebaseDAO.hentPatienter(true);
        EmailView.setCellValueFactory(new PropertyValueFactory<>("email"));
        NavnView.setCellValueFactory(new PropertyValueFactory<>("navn"));
        TableViewForloeb.getItems().addAll(list);
    }
}
