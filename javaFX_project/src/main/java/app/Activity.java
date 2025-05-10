package app;

import java.util.*;

public class Activity {
    private final String name;
    private final Map<Employee, Integer> workHours = new HashMap<>();
    private final Set<Employee> assignedEmployees = new HashSet<>();

    public Activity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public void registerHours(Employee employee, int hours) {
        if (!assignedEmployees.contains(employee)) {
            throw new IllegalStateException("Employee is not assigned to this activity.");
        }
        workHours.put(employee, workHours.getOrDefault(employee, 0) + hours);
    }

    public int getHoursFor(Employee employee) {
        return workHours.getOrDefault(employee, 0);
    }

    public Map<Employee, Integer> getAllWorkHours() {
        return workHours;
    }

    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
}
