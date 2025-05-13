package app;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Abbas
 * Unit tests for Project.assignEmployeeToActivity
 */
public class AssignEmployeeTest {

    @Test
    void actorCanAssignIfNoLeaderSet() {
        Employee actor = new Employee("LEAD");
        Employee target = new Employee("TARG");
        Project project = new Project("25001", "TestProj");
        Activity activity = new Activity("Design", LocalDate.now(), LocalDate.now().plusDays(1), 10);
        project.addActivity(activity);

        project.assignEmployeeToActivity("Design", actor, target);
        assertTrue(activity.isEmployeeAssigned(target));
    }

    @Test
    void leaderCanAssignEmployee() {
        Employee leader = new Employee("LEAD");
        Employee target = new Employee("TARG");
        Project project = new Project("25002", "TestProj");
        Activity activity = new Activity("Dev", LocalDate.now(), LocalDate.now().plusDays(1), 8);
        project.addActivity(activity);
        project.assignProjectLeader(null, leader);

        project.assignEmployeeToActivity("Dev", leader, target);
        assertTrue(activity.isEmployeeAssigned(target));
    }

    @Test
    void nonLeaderCannotAssign() {
        Employee leader = new Employee("LEAD");
        Employee intruder = new Employee("INTR");
        Employee target = new Employee("TARG");
        Project project = new Project("25003", "TestProj");
        Activity activity = new Activity("Test", LocalDate.now(), LocalDate.now().plusDays(1), 6);
        project.addActivity(activity);
        project.assignProjectLeader(null, leader);

        assertThrows(SecurityException.class, () -> {
            project.assignEmployeeToActivity("Test", intruder, target);
        });
    }

    @Test
    void throwsIfActivityNotFound() {
        Employee actor = new Employee("LEAD");
        Employee target = new Employee("TARG");
        Project project = new Project("25004", "TestProj");

        assertThrows(IllegalArgumentException.class, () -> {
            project.assignEmployeeToActivity("Unknown", actor, target);
        });
    }

    @Test
    void throwsIfTargetIsNull() {
        Employee actor = new Employee("LEAD");
        Project project = new Project("25005", "TestProj");
        Activity activity = new Activity("Plan", LocalDate.now(), LocalDate.now().plusDays(1), 4);
        project.addActivity(activity);
        project.assignProjectLeader(null, actor);

        assertThrows(IllegalArgumentException.class, () -> {
            project.assignEmployeeToActivity("Plan", actor, null);
        });
    }

    @Test
    void throwsIfEmployeeAlreadyAssigned() {
        Employee actor = new Employee("LEAD");
        Employee target = new Employee("TARG");
        Project project = new Project("25006", "TestProj");
        Activity activity = new Activity("Repeat", LocalDate.now(), LocalDate.now().plusDays(1), 6);
        project.addActivity(activity);
        project.assignProjectLeader(null, actor);

        // Første gang OK
        project.assignEmployeeToActivity("Repeat", actor, target);

        // Anden gang: skal fejle
        assertThrows(IllegalStateException.class, () -> {
            project.assignEmployeeToActivity("Repeat", actor, target);
        });
    }

}
