package model.datastrukture;

public class Forloeb {
    private String behandlerEmail;
    private String patientEmail;

    public Forloeb(Behandler behandler, Patient patient){
        this.behandlerEmail = behandler.getEmail();
        this.patientEmail = patient.getEmail();
    }

    public String getBehandlerEmail() {
        return behandlerEmail;
    }

    public void setBehandlerEmail(String behandlerEmail) {
        this.behandlerEmail = behandlerEmail;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}
