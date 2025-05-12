package app;

import java.time.LocalDate;
import java.util.*;

public class Project {
    private final String name;
    private final String id;
    private Employee projectLeader;
    private final List<Activity> activities = new ArrayList<>();

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public Activity findActivity(String name) {
        return activities.stream()
            .filter(a -> a.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    public void assignEmployeeToActivity(String activityName, Employee actor, Employee target) {
        if (hasLeader() && !isLeader(actor)) {
            throw new SecurityException("Only the project leader can assign employees.");
        }
        Activity activity = findActivity(activityName);
        if (activity == null) {
            throw new IllegalArgumentException("Activity not found: " + activityName);
        }
        activity.assignEmployee(target);
    }
    

    public double getTotalWorkHours() {
        return activities.stream()
            .flatMap(a -> a.getAllWorkHours().values().stream())
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    public double getTotalBudgetedHours() {
        return activities.stream()
            .mapToDouble(Activity::getBudgetedHours)
            .sum();
    }    

    public String generateProjectHoursReport() {
        double registered = getTotalWorkHours();
        double budgeted = getTotalBudgetedHours();
        return String.format("Project %s (%s): %.1f hours used of %.1f budgeted", name, id, registered, budgeted);
    }

    public Activity createActivity(String name, LocalDate startDate, LocalDate endDate, double budgetedHours, Employee actor) {
        if (hasLeader() && !isLeader(actor)) {
            throw new SecurityException("Only the project leader can create activities.");
        }
        Activity activity = new Activity(name, startDate, endDate, budgetedHours);
        activities.add(activity);
        return activity;
    }  

    public boolean isLeader(Employee employee) {
        return employee.equals(projectLeader);
    }

    public boolean hasLeader() {
        return projectLeader != null;
    }

    public void assignProjectLeader(Employee actor, Employee newLeader) {
        this.projectLeader = newLeader;
    }
    
    @Override
    public String toString() {
        return id + " - " + name;
    }

}
