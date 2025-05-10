package app;

public class Absence {
    private final AbsenceType type;
    private final int hours;

    public Absence(AbsenceType type, int hours) {
        this.type = type;
        this.hours = hours;
    }

    public AbsenceType getType() {
        return type;
    }

    public int getHours() {
        return hours;
    }
}
