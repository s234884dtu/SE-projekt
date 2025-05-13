package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Peter
 * Cucumber steps for registering and verifying work hours on activities.
 */
public class RegisterHoursSteps {
    @When("the employee {string} registers {double} hours on the activity {string}")
    public void the_employee_registers_hours_on_the_activity(String initials, Double hours, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        act.registerHours(emp, hours);
    }

    @Then("the employee {string} has registered {double} hours on the activity {string}")
    public void the_employee_has_registered_hours_on_the_activity(String initials, Double expectedHours, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        assertEquals(expectedHours, act.getHoursFor(emp), 0.001); // 3rd argument is a delta (tolerance)
    }
}
