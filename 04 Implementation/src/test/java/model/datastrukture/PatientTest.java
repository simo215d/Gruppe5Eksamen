package model.datastrukture;

import model.exceptions.IntetNavnException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    void Test01() {
        PatientImpl patient = new PatientImpl();
        assertThrows(NullPointerException.class, () -> patient.setNavn(null));
    }

    @Test
    void Test02() {
        PatientImpl patient = new PatientImpl();
        assertThrows(IntetNavnException.class, () -> patient.setNavn(""));
    }

    @Test
    void Test03() throws IntetNavnException {
        PatientImpl patient = new PatientImpl();
        patient.setNavn("Anton");
        assertEquals("Anton", patient.getNavn());
    }

    @Test
    void Test04() {
        PatientImpl patient = new PatientImpl();
        patient.setErIForloeb(true);
        assertTrue(patient.getErIForloeb());
    }

    @Test
    void Test05() {
        PatientImpl patient = new PatientImpl();
        patient.setErIForloeb(false);
        assertFalse(patient.getErIForloeb());
    }
}
