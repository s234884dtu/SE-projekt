package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateProjectSteps {
    @When("I create a project named {string}")
    public void iCreateAProjectNamed(String name) {
        CommonSteps.system.createProject(name);
    }

    @Then("the project named {string} exists in the system")
    public void theProjectNamedExists(String name) {
        assertNotNull(CommonSteps.system.getProject(name));
    }
}