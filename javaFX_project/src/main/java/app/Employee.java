package app;

import java.util.*;

public class Employee {
    private final String initials;
    private final List<Activity> assignedActivities = new ArrayList<>();
    private final List<Absence> registeredAbsences = new ArrayList<>();

    public Employee(String initials) {
        if (initials.length() != 4) {
            throw new IllegalArgumentException("Initials must be exactly 4 characters.");
        }
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public void assignToActivity(Activity activity) {
        assignedActivities.add(activity);
    }

    public void removeFromActivity(Activity activity) {
        assignedActivities.remove(activity);
    }

    public List<Activity> getAssignedActivities() {
        return assignedActivities;
    }

    public void registerAbsence(AbsenceType type, int hours) {
        registeredAbsences.add(new Absence(type, hours));
    }

    public List<Absence> getAbsences() {
        return registeredAbsences;
    }

    public int getTotalAbsenceHours() {
        return registeredAbsences.stream().mapToInt(Absence::getHours).sum();
    }
}
