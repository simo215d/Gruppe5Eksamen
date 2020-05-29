package model.persistence;

import com.google.cloud.firestore.Firestore;
import model.datastrukture.Behandler;
import model.datastrukture.Patient;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface DAO {
    public void opretForloeb(Behandler behandler, Patient patient) throws BehandlerManglerException, PatientManglerException, IOException, PatientErAlleredeIForloebException, ExecutionException, InterruptedException;
    public ArrayList<Patient> hentPatienter(boolean erIForloeb) throws ExecutionException, InterruptedException, IOException;
    public ArrayList<Behandler> hentBehandlere() throws ExecutionException, InterruptedException, IOException;
}
