package model.datastrukture;

import model.exceptions.IntetNavnException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    void Test01() {
        Patient patient = new Patient();
        assertThrows(NullPointerException.class, () -> patient.setNavn(null));
    }

    @Test
    void Test02() {
        Patient patient = new Patient();
        assertThrows(IntetNavnException.class, () -> patient.setNavn(""));
    }

    @Test
    void Test03() throws IntetNavnException {
        Patient patient = new Patient();
        patient.setNavn("Anton");
        assertEquals("Anton", patient.getNavn());
    }

    @Test
    void Test04() {
        Patient patient = new Patient();
        patient.setErIForloeb(true);
        assertTrue(patient.getErIForloeb());
    }

    @Test
    void Test05() {
        Patient patient = new Patient();
        patient.setErIForloeb(false);
        assertFalse(patient.getErIForloeb());
    }
}
