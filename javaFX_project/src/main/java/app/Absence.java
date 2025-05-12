package app;

import java.time.LocalDate;

public class Absence {
    private final AbsenceType type;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Absence(AbsenceType type, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AbsenceType getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    
}
