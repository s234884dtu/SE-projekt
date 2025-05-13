package app;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Daniel
 * Unit tests for Absence class constructor validation.
 */
public class AbsenceTest {

    @Test
    void validAbsenceAccepted() {
        Absence a = new Absence(AbsenceType.VACATION, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 5));
        assertEquals(AbsenceType.VACATION, a.getType());
        assertEquals(LocalDate.of(2025, 5, 1), a.getStartDate());
        assertEquals(LocalDate.of(2025, 5, 5), a.getEndDate());
    }

    @Test
    void oneDayAbsenceAllowed() {
        Absence a = new Absence(AbsenceType.SICKNESS, LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 3));
        assertEquals(a.getStartDate(), a.getEndDate());
    }

    @Test
    void startAfterEndThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Absence(AbsenceType.COURSE, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 1));
        });
    }

    @Test
    void nullStartDateThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Absence(AbsenceType.VACATION, null, LocalDate.of(2025, 5, 5));
        });
    }

    @Test
    void nullEndDateThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Absence(AbsenceType.VACATION, LocalDate.of(2025, 5, 1), null);
        });
    }

    @Test
    void nullTypeThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Absence(null, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 5));
        });
    }
}
