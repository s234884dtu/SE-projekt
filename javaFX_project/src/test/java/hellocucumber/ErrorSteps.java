package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Sumayo
 * Cucumber steps for system-wide error handling and validation testing.
 */
public class ErrorSteps {
    private Exception caught;

    @Given("a project named {string} with leader {string}")
    public void aProjectNamedWithLeader(String projectName, String leaderInitials) {
        Employee leader = CommonSteps.system.getEmployee(leaderInitials);
        if (leader == null) {
            leader = CommonSteps.system.createEmployee(leaderInitials);
        }

        Project proj = CommonSteps.system.getProject(projectName);
        if (proj == null) {
            proj = CommonSteps.system.createProject(projectName);
        }

        proj.assignProjectLeader(leader, leader);
        CommonSteps.project = proj;
    }

    @When("the leader {string} tries to add an activity named {string} with start date {string}, end date {string}, and {double} budgeted hours to {string}")
    public void leaderTriesInvalidDuration(String leaderIni, String name, String start, String end, double budget, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        Employee leader = CommonSteps.system.getEmployee(leaderIni);
        caught = assertThrows(IllegalArgumentException.class, () ->
            proj.createActivity(
                name,
                LocalDate.parse(start),
                LocalDate.parse(end),
                budget,
                leader
            )
        );
    }

    @When("{string} tries to add an activity named {string} with start date {string}, end date {string}, and {double} budgeted hours to {string}")
    public void nonLeaderTriesCreate(String actorIni, String name, String start, String end, double budget, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        Employee actor = CommonSteps.system.getEmployee(actorIni);
        caught = assertThrows(SecurityException.class, () ->
            proj.createActivity(
                name,
                LocalDate.parse(start),
                LocalDate.parse(end),
                budget,
                actor
            )
        );
    }

    @When("{string} assigns {string} to the activity {string}")
    public void nonLeaderAssignsDirect(String actorIni, String targetIni, String activityName) {
        Project proj = CommonSteps.project;
        final Employee actor = CommonSteps.system.getEmployee(actorIni);
        final Employee target = CommonSteps.system.getEmployee(targetIni);
        if (actor == null) throw new IllegalStateException("Actor must exist");
        if (target == null) throw new IllegalStateException("Target must exist");

        if (proj.findActivity(activityName) == null) {
            proj.createActivity(
                activityName,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                10,
                proj.getProjectLeader()
            );
        }

        caught = assertThrows(SecurityException.class, () ->
            proj.assignEmployeeToActivity(activityName, actor, target)
        );
    }

    @When("{string} tries to assign {string} to the activity {string}")
    public void nonLeaderTriesAssignEmployee(String actorIni, String targetIni, String activityName) {
        nonLeaderAssignsDirect(actorIni, targetIni, activityName);
    }

    @When("the leader {string} tries to assign {string} to the activity {string}")
    public void leaderAssignsToMissingActivity(String leaderIni, String targetIni, String activityName) {
        Project proj = CommonSteps.project;
        Employee leader = CommonSteps.system.getEmployee(leaderIni);
        Employee target = CommonSteps.system.getEmployee(targetIni);

        caught = assertThrows(IllegalArgumentException.class, () ->
            proj.assignEmployeeToActivity(activityName, leader, target)
        );
    }

    @When("I try to remove {string} from the activity {string}")
    public void iTryToRemoveFromTheActivity(String initials, String activityName) {
        Activity act = CommonSteps.project.findActivity(activityName);
        Employee emp = CommonSteps.system.getEmployee(initials);

        caught = assertThrows(IllegalArgumentException.class, () ->
            act.removeEmployee(emp)
        );
    }

    @When("the employee {string} tries to register {double} hours on the activity {string}")
    public void employeeTriesInvalidHours(String initials, Double hours, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        caught = assertThrows(IllegalArgumentException.class, () ->
            act.registerHours(emp, hours)
        );
    }

    @When("{string} tries to assign {string} as the project leader of {string}")
    public void triesToAssignNonexistentLeader(String actorIni, String newLeaderIni, String projectName) {
        Project proj  = CommonSteps.system.getProject(projectName);
        Employee actor = CommonSteps.system.getEmployee(actorIni);
        Employee newLeader = CommonSteps.system.getEmployee(newLeaderIni);
        caught = assertThrows(IllegalArgumentException.class, () ->
            proj.assignProjectLeader(actor, newLeader)
        );
    }

    @When("I try to create an employee with initials {string}")
    public void iTryToCreateAnEmployeeWithInitials(String initials) {
        caught = assertThrows(IllegalArgumentException.class, () ->
            CommonSteps.system.createEmployee(initials)
        );
    }

    @When("I try to create a project named {string}")
    public void iTryToCreateAProjectNamed(String name) {
        caught = assertThrows(IllegalArgumentException.class, () ->
            CommonSteps.system.createProject(name)
        );
    }

    @When("I try to register {word} absence for {string} from {string} to {string}")
    public void tryRegisterAbsence(String type, String initials, String startDateStr, String endDateStr) {
        Exception ex;
        try {
            AbsenceType absenceType = AbsenceType.valueOf(type.toUpperCase());
            Employee emp = CommonSteps.system.getEmployee(initials);
            LocalDate start = LocalDate.parse(startDateStr);
            LocalDate end   = LocalDate.parse(endDateStr);
            ex = assertThrows(IllegalArgumentException.class,
                              () -> emp.registerAbsence(absenceType, start, end));
        } catch (IllegalArgumentException e) {
            ex = e;
        }
        caught = ex;
    }

    @Then("an IllegalArgumentException is thrown")
    public void assertIllegalArg() {
        assertNotNull(caught);
        assertTrue(caught instanceof IllegalArgumentException);
    }

    @Then("a SecurityException is thrown")
    public void assertSecurity() {
        assertNotNull(caught);
        assertTrue(caught instanceof SecurityException);
    }
}
