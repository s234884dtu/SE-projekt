package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Sumayo
 * Unit tests for assigning a project leader and employee initials validation.
 */
public class AssignLeaderTest {

    @Test
    void cannotAssignNullLeader() {
        Project project = new Project("25020", "Leadless");
        assertThrows(IllegalArgumentException.class, () -> {
            project.assignProjectLeader(null, null);
        });
    }

    @Test
    void canAssignValidLeader() {
        Employee e = new Employee("JD01");
        Project project = new Project("25021", "WithLeader");
        project.assignProjectLeader(null, e);
        assertEquals(e, project.getProjectLeader());
    }

    @Test
    void cannotAssignLeaderWithEmptyInitials() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("");
        });
    }

    @Test
    void cannotAssignLeaderWithTooLongInitials() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("ABCDE");
        });
    }
}
