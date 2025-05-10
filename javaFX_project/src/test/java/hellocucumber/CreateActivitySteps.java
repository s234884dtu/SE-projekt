package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateActivitySteps {
    @When("I add an activity named {string} to {string}")
    public void iAddAnActivityNamedTo(String activityName, String projectName) {
        CommonSteps.project.createActivity(activityName);
    }

    @Then("the project {string} has an activity named {string}")
    public void theProjectHasAnActivity(String projectName, String activityName) {
        Project proj = CommonSteps.system.getProject(projectName);
        assertNotNull(proj.findActivity(activityName));
    }
}