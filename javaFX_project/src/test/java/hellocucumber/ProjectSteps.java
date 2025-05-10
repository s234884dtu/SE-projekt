package hellocucumber;

import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Then;

public class ProjectSteps {
    ProjectManagementApp pma = new ProjectManagementApp();
    private String projectName;

    @When("a project is created with name {string}")
    public void aProjectIsCreatedWithName(String string) {
        pma.createProject(string);
        projectName = string;

    }
    @Then ("the list of projects contains a project with that name")
    public void theListOfProjectsContainsAProjectWithThatName()   {
        assertTrue(pma.containsProject(projectName));
    }
}
