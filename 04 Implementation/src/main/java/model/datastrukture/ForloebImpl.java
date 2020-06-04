package model.datastrukture;

import model.exceptions.IntetNavnException;

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
    public void setBehandlerEmail(String behandlerEmail) throws IntetNavnException {
        if (behandlerEmail == null) throw new NullPointerException(){};
        if (behandlerEmail.length() == 0) throw new IntetNavnException(){};
        this.behandlerEmail = behandlerEmail;
    }

    @Override
    public String getPatientEmail() {
        return patientEmail;
    }

    @Override
    public void setPatientEmail(String patientEmail) throws IntetNavnException {
        if (patientEmail == null) throw new NullPointerException(){};
        if (patientEmail.length() == 0) throw new IntetNavnException(){};
        this.patientEmail = patientEmail;
    }
}
