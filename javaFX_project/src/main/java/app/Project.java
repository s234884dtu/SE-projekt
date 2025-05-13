package app;

import java.time.LocalDate;
import java.util.*;

/**
 * Authors:
 * - Sumayo: constructor, project leader handling, identifiers, reporting interfaces
 * - Esther: activity handling and creation
 * - Abbas: employee assignment
 * - Peter: hour summaries and reporting
 */
public class Project {
    private final String name;
    private final String id;
    private Employee projectLeader;
    private final List<Activity> activities = new ArrayList<>();

    /**
     * Author: Sumayo
     */
    public Project(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Author: Sumayo
     */
    public String getName() {
        return name;
    }

    /**
     * Author: Sumayo
     */
    public String getId() {
        return id;
    }

    /**
     * Author: Sumayo
     */
    public Employee getProjectLeader() {
        return projectLeader;
    }

    /**
     * Author: Esther
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Author: Esther
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * Author: Esther
     */
    public Activity findActivity(String name) {
        return activities.stream()
            .filter(a -> a.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    /**
     * Author: Abbas
     */
    public void assignEmployeeToActivity(String activityName, Employee actor, Employee target) {
        if (target == null) {
            throw new IllegalArgumentException("Target employee is null.");
        }

        if (hasLeader() && !isLeader(actor)) {
            throw new SecurityException("Only the project leader can assign employees.");
        }
        Activity activity = findActivity(activityName);
        if (activity == null) {
            throw new IllegalArgumentException("Activity not found: " + activityName);
        }

        if (activity.isEmployeeAssigned(target)) {
            throw new IllegalStateException("Employee already assigned to this activity.");
        }
        activity.assignEmployee(target);
    }

    /**
     * Author: Peter
     */
    public double getTotalWorkHours() {
        return activities.stream()
            .flatMap(a -> a.getAllWorkHours().values().stream())
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    /**
     * Author: Peter
     */
    public double getTotalBudgetedHours() {
        return activities.stream()
            .mapToDouble(Activity::getBudgetedHours)
            .sum();
    }

    /**
     * Author: Peter
     */
    public String generateProjectHoursReport() {
        double registered = getTotalWorkHours();
        double budgeted = getTotalBudgetedHours();
        return String.format("Project %s (%s): %.1f hours used of %.1f budgeted", name, id, registered, budgeted);
     }

    /**
     * Author: Esther
     */
    public Activity createActivity(String name, LocalDate startDate, LocalDate endDate, double budgetedHours, Employee actor) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Activity name must not be empty.");
        }
         if (hasLeader() && !isLeader(actor)) {
            throw new SecurityException("Only the project leader can create activities.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        if (budgetedHours < 0) {
            throw new IllegalArgumentException("Budgeted hours must be non-negative.");
        }

        Activity activity = new Activity(name, startDate, endDate, budgetedHours);
        activities.add(activity);
        return activity;
    }

    /**
     * Author: Sumayo
     */
    public boolean isLeader(Employee employee) {
        return employee.equals(projectLeader);
    }

    /**
     * Author: Sumayo
     */
    public boolean hasLeader() {
        return projectLeader != null;
    }

    /**
     * Author: Sumayo
     */
    public void assignProjectLeader(Employee actor, Employee newLeader) {
        if (newLeader == null) {
            throw new IllegalArgumentException("Cannot assign project leader: employee does not exist.");
        }
        this.projectLeader = newLeader;
    }

    /**
     * Author: Sumayo
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
