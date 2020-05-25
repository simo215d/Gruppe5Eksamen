package entities;
import entities.exceptions.IntetNavnException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehandlerTest {
    @Test
    void Test01() {
        Behandler behandler = new Behandler();
        assertThrows(NullPointerException.class, () -> behandler.setNavn(null));
    }

    @Test
    void Test02() {
        Behandler behandler = new Behandler();
        assertThrows(IntetNavnException.class, () -> behandler.setNavn(""));
    }

    @Test
    void Test03() throws IntetNavnException {
        Behandler behandler = new Behandler();
        behandler.setNavn("Anton");
        assertEquals("Anton", behandler.getNavn());
    }
}