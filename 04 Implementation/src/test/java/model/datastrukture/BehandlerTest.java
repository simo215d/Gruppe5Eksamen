package model.datastrukture;
import model.exceptions.IntetNavnException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehandlerTest {
    @Test
    void Test01() {
        BehandlerImpl behandler = new BehandlerImpl();
        assertThrows(NullPointerException.class, () -> behandler.setNavn(null));
    }

    @Test
    void Test02() {
        BehandlerImpl behandler = new BehandlerImpl();
        assertThrows(IntetNavnException.class, () -> behandler.setNavn(""));
    }

    @Test
    void Test03() throws IntetNavnException {
        BehandlerImpl behandler = new BehandlerImpl();
        behandler.setNavn("Anton");
        assertEquals("Anton", behandler.getNavn());
    }
}