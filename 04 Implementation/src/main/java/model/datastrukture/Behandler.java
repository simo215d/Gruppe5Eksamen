package model.datastrukture;

import model.exceptions.IntetNavnException;

public interface Behandler {
    String getEmail();

    void setEmail(String email);

    void setNavn(String navn) throws IntetNavnException;

    String getNavn();
}
