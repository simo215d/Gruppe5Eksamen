package viewModel;

import model.datastrukture.Behandler;
import model.datastrukture.Patient;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;
import persistence.DAO;
import persistence.firebase.FirebaseDAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ViewModel {
    private DAO firebaseDAO;

    public ViewModel() {
        firebaseDAO = new FirebaseDAOImpl();
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

    public void opretBruger(String cpr, String fornavn, String efternavn, String mobil, String telefon, String email, boolean erBehandler) throws ExecutionException, InterruptedException, IOException {
        firebaseDAO.opretBruger(cpr,fornavn,efternavn,mobil,telefon,email,erBehandler);
    }
}