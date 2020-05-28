package model.datastrukture;

public class Forloeb {
    private String behandler;
    private String patient;

    public Forloeb(Behandler behandler, Patient patient){
        this.behandler = behandler.getNavn();
        this.patient = patient.getNavn();
    }

    public String getBehandler() {
        return behandler;
    }

    public void setBehandler(String behandler) {
        this.behandler = behandler;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
