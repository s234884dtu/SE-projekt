package app;

import java.time.LocalDate;
import java.util.*;

/**
 * Authors:
 * - Esther: class, constructor, getters
 * - Abbas: employee assignment and removal
 * - Peter: work hour registration and related methods
 */
public class Activity {
    private final String name;
    private final Map<Employee, Double> workHours = new HashMap<>();
    private final Set<Employee> assignedEmployees = new HashSet<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private double budgetedHours;

    /**
     * Author: Esther
     */
    public Activity(String name, LocalDate startDate, LocalDate endDate, double budgetedHours) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetedHours = budgetedHours;
    }

    /**
     * Author: Esther
     */
    public String getName() {
        return name;
    }

    /**
     * Author: Esther
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Author: Esther
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Author: Esther
     */
    public double getBudgetedHours() {
        return budgetedHours;
    }

    /**
     * Author: Abbas
     */
    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
        employee.assignToActivity(this);
    }

    /**
     * Author: Abbas
     */
    public void removeEmployee(Employee employee) {
        if (!assignedEmployees.contains(employee)) {
            throw new IllegalArgumentException(
                "Cannot remove employee " + employee.getInitials() + ": not currently assigned."
            );
        }
        assignedEmployees.remove(employee);
        employee.removeFromActivity(this);
    }

    /**
     * Author: Abbas
     */
    public boolean isEmployeeAssigned(Employee employee) {
        return assignedEmployees.contains(employee);
    }

    /**
     * Author: Peter
     */
    public void registerHours(Employee employee, double hours) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null.");
        }
        if (hours <= 0 || hours % 0.5 != 0) {
            throw new IllegalArgumentException("Hours must be in 0.5 increments and greater than 0.");
        }
        workHours.put(employee, workHours.getOrDefault(employee, 0.0) + hours);
    }

    /**
     * Author: Peter
     */
    public double getHoursFor(Employee employee) {
        return workHours.getOrDefault(employee, 0.0);
    }

    /**
     * Author: Peter
     */
    public Map<Employee, Double> getAllWorkHours() {
        return workHours;
    }

    /**
     * Author: Abbas
     */
    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
}
