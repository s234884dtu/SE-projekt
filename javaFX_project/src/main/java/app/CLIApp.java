package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

/**
 * Main CLI controller
 * Authors:
 * - Sumayo: overall structure, project/report features, help/list/report
 * - Esther: add-activity
 * - Abbas: assign/remove employee
 * - Peter: register-hours
 * - Daniel: auth, absence, create-employee
 */
public class CLIApp {

    /**
     * Author: Sumayo
     */
    public static void main(String[] args) throws Exception {
        SystemModel sys = new SystemModel();
        sys.createEmployee("huba"); // system admin

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        System.out.println("Welcome to the Project Management and Time-Tracking CLI. Type 'help' for commands.");
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

                    /**
                     * Author: Sumayo
                     */
                    case "help":
                        System.out.println("""
                            Available commands:
                              sign-in <initials>
                              whoami
                              create-employee <initials>
                              create-project <name>
                              assign-leader <initials> <project>
                              add-activity <activity> <start:yyyy-MM-dd> <end:yyyy-MM-dd> <budgetedHours> <project>
                              assign-employee <initials> <activity> <project>
                              remove-employee <initials> <activity> <project>
                              register-hours <initials> <hours> <activity> <project>
                              register-absence-period <initials> <type> <start:yyyy-MM-dd> <end:yyyy-MM-dd>
                              view-report <project>
                              list-available
                              help / exit
                            """);
                        break;

                    /**
                     * Author: Daniel
                     */
                    case "sign-in":
                        sys.signIn(parts[1]);
                        System.out.println("Signed in as: " + sys.getSignedInUser().getInitials());
                        break;

                    /**
                     * Author: Daniel
                     */
                    case "whoami":
                        Employee current = sys.getSignedInUser();
                        System.out.println(current == null
                                ? "No user is currently signed in."
                                : "You are signed in as: " + current.getInitials());
                        break;

                    /**
                     * Author: Daniel
                     */
                    case "create-employee":
                        requireSignIn(sys);
                        sys.createEmployee(parts[1]);
                        System.out.println("Created employee: " + parts[1]);
                        break;

                    /**
                     * Author: Sumayo
                     */
                    case "create-project":
                        requireSignIn(sys);
                        Project createdProject = sys.createProject(parts[1]);
                        System.out.printf("Created project: %s (%s)%n", createdProject.getName(), createdProject.getId());
                        break;

                    /**
                     * Author: Sumayo
                     */
                    case "assign-leader": {
                        requireSignIn(sys);
                        String ini = parts[1], projName = parts[2];
                        Project projectToAssignLeader = sys.getProject(projName);
                        Employee actor = sys.getSignedInUser();
                        Employee newLeader = sys.getEmployee(ini);
                        projectToAssignLeader.assignProjectLeader(actor, newLeader);
                        System.out.printf("Assigned %s as leader of %s%n", ini, projName);
                        break;
                    }

                    /**
                     * Author: Esther
                     */
                    case "add-activity": {
                        requireSignIn(sys);
                        String actName = parts[1];
                        LocalDate startDate = LocalDate.parse(parts[2]);
                        LocalDate endDate = LocalDate.parse(parts[3]);
                        double budgetedHours = Double.parseDouble(parts[4]);
                        String projName = parts[5];
                        Project projectToAddActivity = sys.getProject(projName);
                        Activity newActivity = projectToAddActivity.createActivity(
                                actName, startDate, endDate, budgetedHours, sys.getSignedInUser());
                        System.out.printf("Added activity %s to %s (from %s to %s, %.1f budgeted hours)%n",
                                actName, projName, startDate, endDate, budgetedHours);
                        break;
                    }

                    /**
                     * Author: Abbas
                     */
                    case "assign-employee": {
                        requireSignIn(sys);
                        String ini = parts[1], actName = parts[2], projName = parts[3];
                        Project projectToAssignEmployee = sys.getProject(projName);
                        Employee actor = sys.getSignedInUser();
                        Employee target = sys.getEmployee(ini);
                        projectToAssignEmployee.assignEmployeeToActivity(actName, actor, target);
                        System.out.printf("Assigned %s to activity %s%n", ini, actName);
                        break;
                    }

                    /**
                     * Author: Abbas
                     */
                    case "remove-employee": {
                        requireSignIn(sys);
                        String ini = parts[1], actName = parts[2], projName = parts[3];
                        Project projectToRemoveFrom = sys.getProject(projName);
                        Activity activity = projectToRemoveFrom.findActivity(actName);
                        activity.removeEmployee(sys.getEmployee(ini));
                        System.out.printf("Removed %s from activity %s%n", ini, actName);
                        break;
                    }

                    /**
                     * Author: Peter
                     */
                    case "register-hours": {
                        requireSignIn(sys);
                        String ini = parts[1];
                        double hrs = Double.parseDouble(parts[2]);
                        String actName = parts[3], projName = parts[4];
                        Project projectToRegisterHours = sys.getProject(projName);
                        Activity activity = projectToRegisterHours.findActivity(actName);
                        activity.registerHours(sys.getEmployee(ini), hrs);
                        System.out.printf("%s logged %.1f hours on %s%n", ini, hrs, actName);
                        break;
                    }

                    /**
                     * Author: Daniel
                     */
                    case "register-absence-period": {
                        requireSignIn(sys);
                        if (parts.length < 5) {
                            throw new IllegalArgumentException("Usage: register-absence-period <initials> <type> <start:yyyy-MM-dd> <end:yyyy-MM-dd>");
                        }
                        String ini = parts[1];
                        AbsenceType type = AbsenceType.valueOf(parts[2].toUpperCase());
                        LocalDate start = LocalDate.parse(parts[3]);
                        LocalDate end = LocalDate.parse(parts[4]);
                        Employee emp = sys.getEmployee(ini);
                        emp.registerAbsence(type, start, end);
                        System.out.printf("Registered %s absence for %s from %s to %s%n", type, ini, start, end);
                        break;
                    }

                    /**
                     * Author: Sumayo
                     */
                    case "view-report": {
                        requireSignIn(sys);
                        String projName = parts[1];
                        Project projectToView = sys.getProject(projName);
                        if (projectToView.hasLeader() && !projectToView.isLeader(sys.getSignedInUser())) {
                            throw new SecurityException("Only the project leader can view the report.");
                        }
                        System.out.println(projectToView.generateProjectHoursReport());
                        break;
                    }

                    /**
                     * Author: Sumayo
                     */
                    case "list-available": {
                        requireSignIn(sys);
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

    /**
     * Author: Sumayo
     */
    private static void requireSignIn(SystemModel sys) {
        if (sys.getSignedInUser() == null) {
            throw new IllegalStateException("You must sign in first.");
        }
    }
}
