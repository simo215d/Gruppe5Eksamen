package entities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehandlerTest {
    Behandler behandler = new Behandler();
    @Test
    void Test01() {
        Behandler behandler = new Behandler();
        assertThrows(NullPointerException.class, () -> behandler.setNavn(null));
    }

    @Test
    void Test02() {


    }

    @Test
    void Test03() {


    }
}