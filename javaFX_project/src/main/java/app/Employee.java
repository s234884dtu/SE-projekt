package app;

import java.time.LocalDate;
import java.util.*;

public class Employee {
    private final String initials;
    private final List<Activity> assignedActivities = new ArrayList<>();
    private final List<Absence> registeredAbsences = new ArrayList<>();

    public Employee(String initials) {
        if (initials.length() < 1 || initials.length() > 4) {
            throw new IllegalArgumentException("Initials must be 1 to 4 characters.");
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

    public void registerAbsence(AbsenceType type, LocalDate start, LocalDate end) {
        registeredAbsences.add(new Absence(type, start, end));
    }

    public List<Absence> getAbsences() {
        return registeredAbsences;
    }  
}
