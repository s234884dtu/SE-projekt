package app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ActivityTest {

    private Activity activity;
    private Employee employee;

    @BeforeEach
    void setUp() {
        activity = new Activity("Design", LocalDate.now(), LocalDate.now().plusDays(7), 20.0);
        employee = new Employee("JD01");
        activity.assignEmployee(employee);
    }

    @Test
    void registerValidHalfHour() {
        activity.registerHours(employee, 0.5);
        assertEquals(0.5, activity.getHoursFor(employee));
    }

    @Test
    void registerValidWholeHour() {
        activity.registerHours(employee, 1.0);
        assertEquals(1.0, activity.getHoursFor(employee));
    }

    @Test
    void registerValidTwoAndHalfHours() {
        activity.registerHours(employee, 2.5);
        assertEquals(2.5, activity.getHoursFor(employee));
    }

    @Test
    void registerZeroHoursThrows() {
        assertThrows(IllegalArgumentException.class, () -> activity.registerHours(employee, 0.0));
    }

    @Test
    void registerNegativeHoursThrows() {
        assertThrows(IllegalArgumentException.class, () -> activity.registerHours(employee, -1.0));
    }

    @Test
    void registerNonHalfIncrementThrows() {
        assertThrows(IllegalArgumentException.class, () -> activity.registerHours(employee, 3.1));
    }
}