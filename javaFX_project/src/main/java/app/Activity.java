package app;

import java.util.*;

public class Activity {
    private final String name;
    private final Map<Employee, Double> workHours = new HashMap<>();
    private final Set<Employee> assignedEmployees = new HashSet<>();
    private int startWeek; 
    private int endWeek; 
    private int budgetedHours;

    public Activity(String name) {
        this(name, 0, 0, 0); // fallback to empty weeks and 0 budget
    }    

    public int getStartWeek() {
        return startWeek;
    }
    
    public int getEndWeek() {
        return endWeek;
    }
    
    public int getBudgetedHours() {
        return budgetedHours;
    }
    

    public String getName() {
        return name;
    } 

    public void removeEmployee(Employee employee) {
        assignedEmployees.remove(employee);
        employee.removeFromActivity(this);
    }

    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
        employee.assignToActivity(this);
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

    public Activity(String name, int startWeek, int endWeek, int budgetedHours) { 
        this.name = name; 
        this.startWeek = startWeek; 
        this.endWeek = endWeek; 
        this.budgetedHours = budgetedHours; 
    }

}
