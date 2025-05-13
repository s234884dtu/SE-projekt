package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Author: Esther
 * Cucumber steps for creating and verifying an activity within a project.
 */
public class CreateActivitySteps {

    @When("I add an activity named {string} with start date {string}, end date {string}, and {double} budgeted hours to {string}")
    public void iAddAnActivityWithDetails(String activityName, String startDateStr, String endDateStr, double budgetedHours, String projectName) {
        Project project = CommonSteps.system.getProject(projectName);
        if (CommonSteps.employee == null) {
            throw new IllegalStateException("You must define an employee before creating an activity.");
        }
        LocalDate start = LocalDate.parse(startDateStr);
        LocalDate end = LocalDate.parse(endDateStr);
        project.createActivity(activityName, start, end, budgetedHours, CommonSteps.employee);
    }

    @When("{string} adds an activity named {string} with start date {string}, end date {string}, and {double} budgeted hours to {string}")
    public void employeeAddsActivity(String employeeInitials, String activityName, String startDateStr, String endDateStr, double budgetedHours, String projectName) {
        Employee actor = CommonSteps.system.getEmployee(employeeInitials);
        if (actor == null) {
            throw new IllegalArgumentException("Employee " + employeeInitials + " not found.");
        }
        Project project = CommonSteps.system.getProject(projectName);
        LocalDate start = LocalDate.parse(startDateStr);
        LocalDate end = LocalDate.parse(endDateStr);
        project.createActivity(activityName, start, end, budgetedHours, actor);
    }

    @Then("the project {string} has an activity named {string}")
    public void theProjectHasAnActivity(String projectName, String activityName) {
        Project proj = CommonSteps.system.getProject(projectName);
        assertNotNull(proj.findActivity(activityName));
    }

    @Then("the activity {string} has start date {string}, end date {string}, and {double} budgeted hours")
    public void theActivityHasDetails(String name, String expectedStart, String expectedEnd, double expectedBudgetedHours) {
        Activity a = CommonSteps.project.findActivity(name);
        assertEquals(LocalDate.parse(expectedStart), a.getStartDate());
        assertEquals(LocalDate.parse(expectedEnd), a.getEndDate());
        assertEquals(expectedBudgetedHours, a.getBudgetedHours(), 0.001); // Compare double with tolerance
    }
}
