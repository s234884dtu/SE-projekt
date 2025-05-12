package hellocucumber;

import app.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class RegisterAbsenceSteps {

    @When("I register {word} absence for {string} from {string} to {string}")
    public void iRegisterAbsence(String type, String initials, String startDateStr, String endDateStr) {
        AbsenceType absenceType = AbsenceType.valueOf(type.toUpperCase());
        Employee emp = CommonSteps.system.getEmployee(initials);
        LocalDate start = LocalDate.parse(startDateStr);
        LocalDate end = LocalDate.parse(endDateStr);
        emp.registerAbsence(absenceType, start, end);
    }

    @Then("{string} has a {word} absence from {string} to {string}")
    public void hasAbsencePeriod(String initials, String type, String startDateStr, String endDateStr) {
        AbsenceType expectedType = AbsenceType.valueOf(type.toUpperCase());
        LocalDate expectedStart = LocalDate.parse(startDateStr);
        LocalDate expectedEnd = LocalDate.parse(endDateStr);

        Employee emp = CommonSteps.system.getEmployee(initials);
        boolean matchFound = emp.getAbsences().stream().anyMatch(a ->
            a.getType() == expectedType &&
            a.getStartDate().equals(expectedStart) &&
            a.getEndDate().equals(expectedEnd)
        );

        assertTrue(matchFound, String.format("%s should have a %s absence from %s to %s", initials, type, startDateStr, endDateStr));
    }
}
