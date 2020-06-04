package model.datastrukture;

import model.exceptions.IntetNavnException;

public interface Patient {
    String getEmail();

    void setEmail(String email);

    void setNavn(String navn) throws IntetNavnException;

    String getNavn();

    void setErIForloeb(boolean b);

    boolean getErIForloeb();
}
