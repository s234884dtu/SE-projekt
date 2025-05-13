package app;

import java.util.*;

/**
 * Authors:
 * - Sumayo: overall system design, state fields
 * - Daniel: authentication and employee management
 * - Esther: project management
 * - Peter: ID generation
 * - Abbas: employee availability
 */
public class SystemModel {
    /**
     * Author: Sumayo
     */
    private final Map<String, Employee> employees = new HashMap<>();
    /**
     * Author: Sumayo
     */
    private final Map<String, Project> projects = new HashMap<>();
    /**
     * Author: Peter
     */
    private int løbenummerCounter = 1;

    /**
     * Author: Sumayo
     */
    private Employee signedInUser = null;

    /**
     * Author: Daniel
     */
    public void signIn(String initials) {
        Employee user = getEmployee(initials);
        if (user == null) {
            throw new IllegalArgumentException("No employee with initials '" + initials + "' exists.");
        }
        signedInUser = user;
    }

    /**
     * Author: Daniel
     */
    public void signOut() {
        signedInUser = null;
    }

    /**
     * Author: Daniel
     */
    public Employee getSignedInUser() {
        return signedInUser;
    }

    /**
     * Author: Daniel
     */
    public Employee createEmployee(String initials) {
        if (employees.containsKey(initials)) {
            throw new IllegalArgumentException("Employee already exists.");
        }
        Employee e = new Employee(initials);
        employees.put(initials, e);
        return e;
    }

    /**
     * Author: Esther
     */
    public Project createProject(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Project name is required.");
        }
        String id = generateProjectId();  
        Project p = new Project(id, name);
        projects.put(name, p);
        return p;
    }

    /**
     * Author: Peter
     */
    private String generateProjectId() {
        int year = 2000 + Calendar.getInstance().get(Calendar.YEAR) % 100;  // 25 for 2025
        return String.format("%02d%03d", year, løbenummerCounter++);
    }

    /**
     * Author: Daniel
     */
    public Employee getEmployee(String initials) {
        return employees.get(initials);
    }

    /**
     * Author: Esther
     */
    public Project getProject(String name) {
        return projects.get(name);
    }

    /**
     * Author: Daniel
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    /**
     * Author: Esther
     */
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    /**
     * Author: Abbas
     */
    public List<Employee> getAvailableEmployees() {
        // Available = not currently assigned to any activity
        return employees.values().stream()
            .filter(e -> e.getAssignedActivities().isEmpty())
            .toList();
    }
}
