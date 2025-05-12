package app;

import java.util.*;

public class SystemModel {
    private final Map<String, Employee> employees = new HashMap<>();
    private final Map<String, Project> projects = new HashMap<>();
    private int løbenummerCounter = 1;

    private Employee signedInUser = null;

    public void signIn(String initials) {
        Employee user = getEmployee(initials);
        if (user == null) {
            throw new IllegalArgumentException("No employee with initials '" + initials + "' exists.");
        }
        signedInUser = user;
    }

    public void signOut() {
        signedInUser = null;
    }

    public Employee getSignedInUser() {
        return signedInUser;
    }

    public Employee createEmployee(String initials) {
        if (employees.containsKey(initials)) {
            throw new IllegalArgumentException("Employee already exists.");
        }
        Employee e = new Employee(initials);
        employees.put(initials, e);
        return e;
    }

    public Project createProject(String name) {
        String id = generateProjectId();  
        Project p = new Project(id, name);
        projects.put(name, p);
        return p;
    }

    private String generateProjectId() {
        int year = 2000 + Calendar.getInstance().get(Calendar.YEAR) % 100;  // 25 for 2025
        return String.format("%02d%03d", year, løbenummerCounter++);
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
