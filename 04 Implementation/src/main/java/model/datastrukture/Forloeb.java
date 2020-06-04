package model.datastrukture;

public interface Forloeb {
    String getBehandlerEmail();

    void setBehandlerEmail(String behandlerEmail) throws model.exceptions.IntetNavnException;

    String getPatientEmail();

    void setPatientEmail(String patientEmail) throws model.exceptions.IntetNavnException;
}
