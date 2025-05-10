package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterAbsenceSteps {
    @When("I register {int} hours of {word} for {string}")
    public void iRegisterAbsence(int hours, String type, String initials) {
        AbsenceType absenceType = AbsenceType.valueOf(type.toUpperCase());
        Employee emp = CommonSteps.system.getEmployee(initials);
        emp.registerAbsence(absenceType, hours);
    }

    @Then("{string} has {int} hours of {word} absence")
    public void hasHoursOfAbsence(String initials, int expected, String type) {
        AbsenceType absenceType = AbsenceType.valueOf(type.toUpperCase());
        Employee emp = CommonSteps.system.getEmployee(initials);
        int total = emp.getAbsences().stream()
                .filter(a -> a.getType() == absenceType)
                .mapToInt(Absence::getHours)
                .sum();
        assertEquals(expected, total);
    }
}