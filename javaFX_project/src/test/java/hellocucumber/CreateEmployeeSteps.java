package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateEmployeeSteps {
    @When("I create an employee with initials {string}")
    public void iCreateAnEmployeeWithInitials(String initials) {
        CommonSteps.system.createEmployee(initials);
    }

    @Then("the employee with initials {string} exists in the system")
    public void theEmployeeWithInitialsExists(String initials) {
        assertEquals(initials, CommonSteps.system.getEmployee(initials).getInitials());
    }
}