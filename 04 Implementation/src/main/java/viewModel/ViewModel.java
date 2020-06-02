package viewModel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import model.datastrukture.Behandler;
import model.datastrukture.Forloeb;
import model.datastrukture.Patient;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;
import model.persistence.firebase.FirebaseDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewModel {
    private FirebaseDAO firebaseDAO;

    public ViewModel() {
        firebaseDAO = new FirebaseDAO();
    }

    public void opretForloeb(Behandler behandler, Patient patient){
        try {
            firebaseDAO.opretForloeb(behandler, patient);
        } catch (BehandlerManglerException | PatientErAlleredeIForloebException | PatientManglerException | IOException | ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> hentPatienter(boolean erIForloeb) throws InterruptedException, ExecutionException, IOException {
        return firebaseDAO.hentPatienter(erIForloeb);
    }

    public ArrayList<Behandler> hentBehandlere() throws InterruptedException, ExecutionException, IOException {
        return firebaseDAO.hentBehandlere();
    }
}