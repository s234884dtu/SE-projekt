package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateActivitySteps {

    @When("I add an activity named {string} with start week {int}, end week {int}, and {int} budgeted hours to {string}")
    public void iAddAnActivityWithDetails(String activityName, int startWeek, int endWeek, int budgetedHours, String projectName) {
        Project project = CommonSteps.system.getProject(projectName);
        if (CommonSteps.employee == null) {
            throw new IllegalStateException("You must define an employee before creating an activity.");
        }
        project.createActivity(activityName, startWeek, endWeek, budgetedHours, CommonSteps.employee);
    }

    @When("{string} adds an activity named {string} with start week {int}, end week {int}, and {int} budgeted hours to {string}")
    public void employeeAddsActivity(String employeeInitials, String activityName, int startWeek, int endWeek, int budgetedHours, String projectName) {
        Employee actor = CommonSteps.system.getEmployee(employeeInitials);
        if (actor == null) {
            throw new IllegalArgumentException("Employee " + employeeInitials + " not found.");
        }
        Project project = CommonSteps.system.getProject(projectName);
        project.createActivity(activityName, startWeek, endWeek, budgetedHours, actor);
    }

    @Then("the project {string} has an activity named {string}")
    public void theProjectHasAnActivity(String projectName, String activityName) {
        Project proj = CommonSteps.system.getProject(projectName);
        assertNotNull(proj.findActivity(activityName));
    }

    @Then("the activity {string} has start week {int}, end week {int}, and {int} budgeted hours")
    public void theActivityHasDetails(String name, int startWeek, int endWeek, int budgetedHours) {
        Activity a = CommonSteps.project.findActivity(name);
        assertEquals(startWeek, a.getStartWeek());
        assertEquals(endWeek, a.getEndWeek());
        assertEquals(budgetedHours, a.getBudgetedHours());
    }
}
