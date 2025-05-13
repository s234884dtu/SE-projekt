package app;

import java.time.LocalDate;
import java.util.*;

/**
 * Authors:
 * - Daniel: constructor, initials, absence
 * - Abbas: assigned activity handling
 */
public class Employee {
    private final String initials;
    private final List<Activity> assignedActivities = new ArrayList<>();
    private final List<Absence> registeredAbsences = new ArrayList<>();

    /**
     * Author: Daniel
     */
    public Employee(String initials) {
        if (initials.length() < 1 || initials.length() > 4) {
            throw new IllegalArgumentException("Initials must be 1 to 4 characters.");
        }        
        this.initials = initials;
    }

    /**
     * Author: Daniel
     */
    public String getInitials() {
        return initials;
    }

    /**
     * Author: Abbas
     */
    public void assignToActivity(Activity activity) {
        assignedActivities.add(activity);
    }

    /**
     * Author: Abbas
     */
    public void removeFromActivity(Activity activity) {
        assignedActivities.remove(activity);
    }

    /**
     * Author: Abbas
     */
    public List<Activity> getAssignedActivities() {
        return assignedActivities;
    }

    /**
     * Author: Daniel
     */
    public void registerAbsence(AbsenceType type, LocalDate start, LocalDate end) {
        registeredAbsences.add(new Absence(type, start, end));
    }

    /**
     * Author: Daniel
     */
    public List<Absence> getAbsences() {
        return registeredAbsences;
    }  
}
