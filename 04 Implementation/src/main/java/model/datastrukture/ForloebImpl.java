package model.datastrukture;

public class ForloebImpl implements Forloeb {
    private String behandlerEmail;
    private String patientEmail;

    public ForloebImpl(Behandler behandler, Patient patient){
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
