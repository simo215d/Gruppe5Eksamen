package model.datastrukture;

import model.exceptions.IntetNavnException;
import model.exceptions.PatientManglerException;
import model.persistence.FirebaseDAOImplTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForloebTest {

    @Test
    void Test01(){
        String behandlerEmail = null;
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertThrows(NullPointerException.class,() -> forloeb.setBehandlerEmail(behandlerEmail), (String) null);
    }

    @Test
    void Test02(){
        String behandlerEmail = "";
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertThrows(IntetNavnException.class,() -> forloeb.setBehandlerEmail(behandlerEmail), (String) null);
    }

    @Test
    void Test03() throws IntetNavnException {
        String behandlerEmail = "Anton@gmail.com";
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        forloeb.setBehandlerEmail(behandlerEmail);
        assertEquals(behandlerEmail, forloeb.getBehandlerEmail());
    }

    @Test
    void Test04() {
        String behandlerEmail = "Anton@gmail.com";
        MockBehandler behandler = new MockBehandler();
        behandler.setEmail(behandlerEmail);
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertEquals(behandlerEmail, forloeb.getBehandlerEmail());
    }

    @Test
    void Test05(){
        String patientEmail = null;
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertThrows(NullPointerException.class,() -> forloeb.setPatientEmail(patientEmail), (String) null);
    }

    @Test
    void Test06(){
        String patientEmail = "";
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertThrows(IntetNavnException.class,() -> forloeb.setBehandlerEmail(patientEmail), (String) null);
    }

    @Test
    void Test07() throws IntetNavnException {
        String patientEmail = "Anton@gmail.com";
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        forloeb.setPatientEmail(patientEmail);
        assertEquals(patientEmail, forloeb.getPatientEmail());
    }

    @Test
    void Test08(){
        String patientEmail = "Anton@gmail.com";
        MockBehandler behandler = new MockBehandler();
        MockPatient patient = new MockPatient();
        patient.setEmail(patientEmail);
        ForloebImpl forloeb = new ForloebImpl(behandler, patient);
        assertEquals(patientEmail, forloeb.getPatientEmail());
    }

    @Test
    void setBehandlerEmail() {
    }

    @Test
    void getPatientEmail() {
    }

    @Test
    void setPatientEmail() {
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