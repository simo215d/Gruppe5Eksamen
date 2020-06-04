package model.datastrukture;

import model.exceptions.IntetNavnException;

public class PatientImpl implements Patient {
    private String navn;
    private boolean erIForloeb;
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
        if (navn == null) throw new NullPointerException(){};
        if (navn.length() == 0) throw new IntetNavnException(){};
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
