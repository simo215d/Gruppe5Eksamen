package model.datastrukture;

import model.exceptions.IntetNavnException;

public class Patient {
    private String navn;
    private boolean erIForloeb;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNavn(String navn) throws IntetNavnException {
        if (navn == null) throw new NullPointerException(){};
        if (navn.length() == 0) throw new IntetNavnException(){};
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setErIForloeb(boolean b) {
        this.erIForloeb = b;
    }

    public boolean getErIForloeb() {
        return erIForloeb;
    }
}
