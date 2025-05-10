package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssignEmployeeToActivitySteps {
    @When("I assign {string} to the activity {string}")
    public void iAssignToActivity(String initials, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.activity;
        act.assignEmployee(emp);
    }

    @Then("{string} is assigned to the activity {string}")
    public void isAssignedToActivity(String initials, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.activity;
        assertTrue(act.isEmployeeAssigned(emp));
    }
}