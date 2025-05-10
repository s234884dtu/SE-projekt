package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterHoursSteps {
    @When("the employee {string} registers {int} hours on the activity {string}")
    public void the_employee_registers_hours_on_the_activity(String initials, Integer hours, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        act.registerHours(emp, hours);
    }

    @Then("the employee {string} has registered {int} hours on the activity {string}")
    public void the_employee_has_registered_hours_on_the_activity(String initials, Integer expectedHours, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        assertEquals(expectedHours.intValue(), act.getHoursFor(emp));
    }
}