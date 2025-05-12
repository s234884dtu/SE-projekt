package app;

import java.time.LocalDate;
import java.util.*;

public class Activity {
    private final String name;
    private final Map<Employee, Double> workHours = new HashMap<>();
    private final Set<Employee> assignedEmployees = new HashSet<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private double budgetedHours;

    public Activity(String name, LocalDate startDate, LocalDate endDate, double budgetedHours) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetedHours = budgetedHours;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getBudgetedHours() {
        return budgetedHours;
    }

    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
        employee.assignToActivity(this);
    }

    public void removeEmployee(Employee employee) {
        assignedEmployees.remove(employee);
        employee.removeFromActivity(this);
    }

    public boolean isEmployeeAssigned(Employee employee) {
        return assignedEmployees.contains(employee);
    }

    public void registerHours(Employee employee, double hours) {
        if (hours <= 0 || hours % 0.5 != 0) {
            throw new IllegalArgumentException("Hours must be in 0.5 increments and greater than 0.");
        }
        workHours.put(employee, workHours.getOrDefault(employee, 0.0) + hours);
    }

    public double getHoursFor(Employee employee) {
        return workHours.getOrDefault(employee, 0.0);
    }

    public Map<Employee, Double> getAllWorkHours() {
        return workHours;
    }

    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
}
