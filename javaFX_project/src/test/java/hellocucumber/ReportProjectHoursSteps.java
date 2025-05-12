package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportProjectHoursSteps {
    private double reportedHours;

    @When("I view the total hours of project {string}")
    public void iViewTotalHours(String projectName) {
        Project proj = CommonSteps.system.getProject(projectName);
        if (proj == null) {
            throw new IllegalArgumentException("Project not found: " + projectName);
        }
        reportedHours = proj.getTotalWorkHours();
    }

    @Then("the total hours should be {double}")
    public void theTotalHoursShouldBe(double expected) {
        assertEquals(expected, reportedHours, 0.001); // ✅ use delta for double comparison
    }
}
