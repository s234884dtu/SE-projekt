package app;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Esther
 * Unit tests for Project.createActivity
 */
public class CreateActivityTest {

    @Test
    void nonLeaderCanCreateIfNoLeaderAssigned() {
        Employee actor = new Employee("USER");
        Project p = new Project("25001", "MyProj");

        Activity a = p.createActivity("Design", LocalDate.now(), LocalDate.now().plusDays(5), 10.0, actor);
        assertTrue(p.getActivities().contains(a));
    }

    @Test
    void leaderCanCreateActivity() {
        Employee leader = new Employee("LEAD");
        Project p = new Project("25002", "SecureProj");
        p.assignProjectLeader(null, leader);

        Activity a = p.createActivity("Test", LocalDate.now(), LocalDate.now().plusDays(2), 6.0, leader);
        assertTrue(p.getActivities().contains(a));
    }

    @Test
    void nonLeaderCannotCreateIfLeaderExists() {
        Employee leader = new Employee("LEAD");
        Employee intruder = new Employee("INTR");
        Project p = new Project("25003", "BlockedProj");
        p.assignProjectLeader(null, leader);

        assertThrows(SecurityException.class, () -> {
            p.createActivity("Hack", LocalDate.now(), LocalDate.now().plusDays(1), 2.0, intruder);
        });
    }

    @Test
    void emptyActivityNameNotAllowed() {
        Employee actor = new Employee("LEAD");
        Project p = new Project("25030", "NoEmpty");
        p.assignProjectLeader(null, actor);

        assertThrows(IllegalArgumentException.class, () -> {
            p.createActivity("", LocalDate.now(), LocalDate.now().plusDays(1), 5, actor);
        });
    }
}
