package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectLeaderSteps {

    @When("{string} assigns {string} as the project leader of {string}")
    public void assignProjectLeader(String actorInitials, String newLeaderInitials, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        Employee actor = CommonSteps.system.getEmployee(actorInitials);
        Employee newLeader = CommonSteps.system.getEmployee(newLeaderInitials);
        proj.assignProjectLeader(actor, newLeader);
    }

    @Then("{string} is the project leader of {string}")
    public void isTheProjectLeader(String initials, String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        assertEquals(initials, proj.getProjectLeader().getInitials());
    }
}
