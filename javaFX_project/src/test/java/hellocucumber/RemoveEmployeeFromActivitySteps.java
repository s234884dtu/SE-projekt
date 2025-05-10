package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveEmployeeFromActivitySteps {
    @When("I remove {string} from the activity {string}")
    public void iRemoveFromTheActivity(String initials, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        assertNotNull(emp, "Employee must exist before removal");
        assertNotNull(act, "Activity must exist before removal");
        act.removeEmployee(emp);
    }

    @Then("{string} is not assigned to the activity {string}")
    public void isNotAssignedToActivity(String initials, String activityName) {
        Employee emp = CommonSteps.system.getEmployee(initials);
        Activity act = CommonSteps.project.findActivity(activityName);
        assertFalse(act.isEmployeeAssigned(emp), 
                    "Employee " + initials + " should have been removed from " + activityName);
    }
}
