package model.persistence;

import model.datastrukture.Behandler;
import model.datastrukture.Patient;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;
import model.persistence.firebase.FirebaseDAO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class FirebaseDAOTest {
    @Test
    void Test01() {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        assertThrows(BehandlerManglerException.class,() -> firebaseDAO.opretForloeb(null, new Patient()));
    }

    @Test
    void Test02() {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        assertThrows(PatientManglerException.class,() -> firebaseDAO.opretForloeb(new Behandler(), null));
    }

    @Test
    void Test03() {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        assertThrows(BehandlerManglerException.class,() -> firebaseDAO.opretForloeb(null, null));
    }

    @Test
    void Test04() throws PatientManglerException, BehandlerManglerException, IOException, PatientErAlleredeIForloebException {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        firebaseDAO.opretForloeb(new Behandler(), new Patient());
    }

    @Test
    void Test05() throws ExecutionException, InterruptedException {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        ArrayList<Patient> patienter = new ArrayList<Patient>();
        patienter = firebaseDAO.hentPatienter(true);
        for (Patient patient : patienter) {
            assertTrue(patient.getErIForloeb());
        }
    }

    @Test
    void Test06() throws ExecutionException, InterruptedException {
        FirebaseDAO firebaseDAO = new FirebaseDAO();
        ArrayList<Patient> patienter = new ArrayList<Patient>();
        patienter = firebaseDAO.hentPatienter(false);
        for (Patient patient : patienter) {
            assertFalse(patient.getErIForloeb());
        }
    }
}
