package entities;

public class Behandler {
    private String navn;
    public void setNavn(String navn) {
        if (navn == null ) throw new NullPointerException(){};
        this.navn = navn;
    }
}
