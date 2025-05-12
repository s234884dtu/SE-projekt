package hellocucumber;

import app.SystemModel;
import app.Project;
import app.Activity;
import app.Employee;
import io.cucumber.java.en.Given;

import java.time.LocalDate;

public class CommonSteps {
    public static SystemModel system = new SystemModel();
    public static Project project;
    public static Activity activity;
    public static Employee employee;

    @Given("an employee with initials {string}")
    public void anEmployeeWithInitials(String initials) {
        Employee e = system.getEmployee(initials);
        if (e == null) {
            e = system.createEmployee(initials);
        }
        employee = e;
    }

    @Given("a project named {string}")
    public void aProjectNamed(String projectName) {
        Project p = system.getProject(projectName);
        if (p == null) {
            p = system.createProject(projectName);
        }
        project = p;
    }

    @Given("an activity named {string} in the project {string}")
    public void anActivityNamedInProject(String activityName, String projectName) {
        aProjectNamed(projectName);
        Activity a = project.findActivity(activityName);
        if (a == null) {
            if (employee == null) {
                throw new IllegalStateException("An employee must be set before creating an activity.");
            }
            // Temporary default dates (e.g., today to one week from today)
            LocalDate start = LocalDate.now();
            LocalDate end = start.plusDays(7);
            a = project.createActivity(activityName, start, end, 10, employee);
        }
        activity = a;
    }

    @Given("the employee {string} is assigned to the activity {string}")
    public void theEmployeeIsAssignedToTheActivity(String initials, String activityName) {
        anEmployeeWithInitials(initials);
        anActivityNamedInProject(activityName, project.getName());
        activity.assignEmployee(employee);
    }
}
