package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectLeaderSteps {
    @When("I assign {string} as the project leader of {string}")
    public void iAssignAsProjectLeader(String initials, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        Employee emp = CommonSteps.system.getEmployee(initials);
        proj.setProjectLeader(emp);
    }

    @Then("{string} is the project leader of {string}")
    public void isTheProjectLeader(String initials, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        assertEquals(initials, proj.getProjectLeader().getInitials());
    }
}