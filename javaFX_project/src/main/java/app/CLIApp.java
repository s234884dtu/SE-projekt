package app;

import app.SystemModel;
import app.Project;
import app.Activity;
import app.Employee;
import app.AbsenceType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CLIApp {
    public static void main(String[] args) throws Exception {
        SystemModel sys = new SystemModel();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        System.out.println("Welcome to the Time-Tracking CLI. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            line = in.readLine();
            if (line == null) break;

            String[] parts = line.trim().split("\\s+");
            if (parts.length == 0) continue;
            String cmd = parts[0];

            try {
                switch (cmd) {
                    case "exit":
                        System.out.println("Goodbye!");
                        return;

                    case "help":
                        System.out.println("""
                            Available commands:
                              create-employee <initials>
                              create-project <name>
                              assign-leader <initials> <project>
                              add-activity <activity> <project>
                              assign-employee <initials> <activity> <project>
                              remove-employee <initials> <activity> <project>
                              register-hours <initials> <hours> <activity> <project>
                              register-absence <initials> <type> <hours>
                              view-report <project>
                              list-available
                              help / exit
                            """);
                        break;

                    case "create-employee":
                        sys.createEmployee(parts[1]);
                        System.out.println("Created employee: " + parts[1]);
                        break;

                    case "create-project":
                        sys.createProject(parts[1]);
                        System.out.println("Created project: " + parts[1]);
                        break;

                    case "assign-leader": {
                        String ini = parts[1], projName = parts[2];
                        Project p = sys.getProject(projName);
                        p.setProjectLeader(sys.getEmployee(ini));
                        System.out.printf("Assigned %s as leader of %s%n", ini, projName);
                        break;
                    }

                    case "add-activity": {
                        String actName = parts[1], projName = parts[2];
                        Project p = sys.getProject(projName);
                        p.createActivity(actName);
                        System.out.printf("Added activity %s to %s%n", actName, projName);
                        break;
                    }

                    case "assign-employee": {
                        String ini = parts[1], actName = parts[2], projName = parts[3];
                        Project p = sys.getProject(projName);
                        Activity a = p.findActivity(actName);
                        a.assignEmployee(sys.getEmployee(ini));
                        System.out.printf("Assigned %s to activity %s%n", ini, actName);
                        break;
                    }

                    case "remove-employee": {
                        String ini = parts[1], actName = parts[2], projName = parts[3];
                        Project p = sys.getProject(projName);
                        Activity a = p.findActivity(actName);
                        a.removeEmployee(sys.getEmployee(ini));
                        System.out.printf("Removed %s from activity %s%n", ini, actName);
                        break;
                    }

                    case "register-hours": {
                        String ini = parts[1], actName = parts[3], projName = parts[4];
                        int hrs = Integer.parseInt(parts[2]);
                        Project p = sys.getProject(projName);
                        Activity a = p.findActivity(actName);
                        a.registerHours(sys.getEmployee(ini), hrs);
                        System.out.printf("%s logged %d hours on %s%n", ini, hrs, actName);
                        break;
                    }

                    case "register-absence": {
                        String ini = parts[1], type = parts[2], h = parts[3];
                        int hrsAbs = Integer.parseInt(h);
                        sys.getEmployee(ini).registerAbsence(
                            AbsenceType.valueOf(type.toUpperCase()), hrsAbs);
                        System.out.printf("%s registered %d hours of %s%n", ini, hrsAbs, type);
                        break;
                    }

                    case "view-report": {
                        String projName = parts[1];
                        int total = sys.getProject(projName).getTotalWorkHours();
                        System.out.printf("Total hours for %s: %d%n", projName, total);
                        break;
                    }

                    case "list-available": {
                        List<Employee> free = sys.getAvailableEmployees();
                        System.out.print("Available: ");
                        free.forEach(e -> System.out.print(e.getInitials() + " "));
                        System.out.println();
                        break;
                    }

                    default:
                        System.out.println("Unknown command; type 'help' to see list.");
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }
}
