package model.persistence;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import model.datastrukture.*;
import model.exceptions.BehandlerManglerException;
import model.exceptions.IntetNavnException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;
import persistence.firebase.FirebaseDAOImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class FirebaseDAOImplTest {
    @Test
    void Test01() {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        assertThrows(BehandlerManglerException.class,() -> firebaseDAO.opretForloeb(null, new MockPatient()));
    }

    @Test
    void Test02() {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        assertThrows(PatientManglerException.class,() -> firebaseDAO.opretForloeb(new MockBehandler(), null));
    }

    @Test
    void Test03() throws PatientManglerException, BehandlerManglerException,
            IOException, PatientErAlleredeIForloebException, IntetNavnException,
            ExecutionException, InterruptedException {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        MockPatient patient = new MockPatient();
        MockBehandler behandler = new MockBehandler();
        patient.setNavn("grette");
        behandler.setNavn("simon");
        patient.setEmail("grette@gmail.com");
        behandler.setEmail("simon@gmail.com");
        firebaseDAO.opretForloeb(behandler, patient);
        MockForloeb forloeb = (MockForloeb) firebaseDAO.hentForloeb(patient);
        assertEquals(forloeb.behandlerEmail, behandler.email);
        assertEquals(forloeb.patientEmail, patient.email);
    }

    @Test
    void Test04() throws ExecutionException, InterruptedException, IOException {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        ArrayList<Patient> patienter = new ArrayList<Patient>();
        patienter = firebaseDAO.hentPatienter(true);
        for (Patient patient : patienter) {
            assertTrue(patient.getErIForloeb());
        }
    }

    @Test
    void Test05() throws ExecutionException, InterruptedException, IOException {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        ArrayList<Patient> patienter = new ArrayList<Patient>();
        patienter = firebaseDAO.hentPatienter(false);
        for (Patient patient : patienter) {
            assertFalse(patient.getErIForloeb());
        }
    }

    @Test
    void Test06() throws InterruptedException, ExecutionException, IOException {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        ArrayList<Behandler> behandlere = new ArrayList<Behandler>();
        behandlere = firebaseDAO.hentBehandlere();
        for (Behandler behandler : behandlere) {
            assertNotNull(behandler);
        }
    }

    @Test
    void Test07() throws IOException {
        MockFirebaseDAOImpl firebaseDAO = new MockFirebaseDAOImpl();
        assertNotNull(firebaseDAO.hentDatabase());
    }

    private class MockFirebaseDAOImpl extends FirebaseDAOImpl{
        @Override
        public Forloeb newForloeb(Behandler behandler, Patient patient) {
            return new MockForloeb(behandler, patient);
        }

        @Override
        public Forloeb castDocumentSnapshotToForloeb(DocumentSnapshot documentSnapshot){
            return documentSnapshot.toObject(MockForloeb.class);
        }
    }

    private static class MockForloeb implements Forloeb{
        private String behandlerEmail;
        private String patientEmail;

        public MockForloeb(){}

        public MockForloeb(Behandler behandler, Patient patient){
            this.behandlerEmail = behandler.getEmail();
            this.patientEmail = patient.getEmail();
        }

        @Override
        public String getBehandlerEmail() {
            return behandlerEmail;
        }

        @Override
        public void setBehandlerEmail(String behandlerEmail) {
            this.behandlerEmail = behandlerEmail;
        }

        @Override
        public String getPatientEmail() {
            return patientEmail;
        }

        @Override
        public void setPatientEmail(String patientEmail) {
            this.patientEmail = patientEmail;
        }
    }

    private class MockBehandler implements Behandler{
        private String navn;
        private String email;

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public void setNavn(String navn) throws IntetNavnException {
            this.navn = navn;
        }

        @Override
        public String getNavn() {
            return navn;
        }
    }

    private class MockPatient implements Patient{
        private String navn;
        private String email;
        private boolean erIForloeb;

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public void setNavn(String navn) throws IntetNavnException {
            this.navn = navn;
        }

        @Override
        public String getNavn() {
            return navn;
        }

        @Override
        public void setErIForloeb(boolean b) {
            this.erIForloeb = b;
        }

        @Override
        public boolean getErIForloeb() {
            return erIForloeb;
        }
    }
}
