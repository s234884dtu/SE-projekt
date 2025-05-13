package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Abbas
 * Cucumber steps for listing and validating available employees.
 */
public class ViewAvailableEmployeesSteps {
    private List<Employee> available;

    @When("I list available employees")
    public void iListAvailableEmployees() {
        available = CommonSteps.system.getAvailableEmployees();
    }

    @Then("only {string} should be listed")
    public void onlyShouldBeListed(String expectedInitials) {
        assertEquals(1, available.size());
        assertEquals(expectedInitials, available.get(0).getInitials());
    }
}
