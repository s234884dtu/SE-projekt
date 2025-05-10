package app;

import java.util.*;

public class SystemModel {
    private final Map<String, Employee> employees = new HashMap<>();
    private final Map<String, Project> projects = new HashMap<>();

    public Employee createEmployee(String initials) {
        if (employees.containsKey(initials)) {
            throw new IllegalArgumentException("Employee already exists.");
        }
        Employee e = new Employee(initials);
        employees.put(initials, e);
        return e;
    }

    public Project createProject(String name) {
        if (projects.containsKey(name)) {
            throw new IllegalArgumentException("Project name already in use.");
        }
        Project p = new Project(name);
        projects.put(name, p);
        return p;
    }

    public Employee getEmployee(String initials) {
        return employees.get(initials);
    }

    public Project getProject(String name) {
        return projects.get(name);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public List<Employee> getAvailableEmployees() {
        // Available = not currently assigned to any activity
        return employees.values().stream()
            .filter(e -> e.getAssignedActivities().isEmpty())
            .toList();
    }
}
