package model.datastrukture;

import model.exceptions.IntetNavnException;

public class BehandlerImpl implements Behandler {
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
        if (navn == null) throw new NullPointerException(){};
        if (navn.length() == 0) throw new IntetNavnException(){};
        this.navn = navn;
    }

    @Override
    public String getNavn() {
        return navn;
    }
}
