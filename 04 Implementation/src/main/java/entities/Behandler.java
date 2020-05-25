package entities;

import entities.exceptions.IntetNavnException;

public class Behandler {
    private String navn;
    public void setNavn(String navn) throws IntetNavnException {
        if (navn == null) throw new NullPointerException(){};
        if (navn.length() == 0) throw new IntetNavnException(){};
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}
