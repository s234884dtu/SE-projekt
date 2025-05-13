package app;

import java.time.LocalDate;

/**
 * Author: Daniel
 */
public class Absence {
    private final AbsenceType type;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Author: Daniel
     */
    public Absence(AbsenceType type, LocalDate startDate, LocalDate endDate) {
        if (type == null) {
            throw new IllegalArgumentException("Absence type must not be null.");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end date must not be null.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }

        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Author: Daniel
     */
    public AbsenceType getType() {
        return type;
    }

    /**
     * Author: Daniel
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Author: Daniel
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
