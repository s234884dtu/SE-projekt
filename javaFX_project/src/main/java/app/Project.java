package app;

import java.util.*;

public class Project {
    private final String name;
    private Employee projectLeader;
    private final List<Activity> activities = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProjectLeader(Employee leader) {
        this.projectLeader = leader;
    }

    public Employee getProjectLeader() {
        return projectLeader;
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

    public List<Activity> getActivities() {
        return activities;
    }

    public int getTotalWorkHours() {
        return activities.stream()
            .mapToInt(a -> a.getAllWorkHours().values().stream().mapToInt(Integer::intValue).sum())
            .sum();
    }
    public Activity createActivity(String activityName) {
        Activity activity = new Activity(activityName);
        activities.add(activity);
        return activity;
    }
}
