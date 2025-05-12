package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CLIApp {
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

                    case "help":
                        System.out.println("""
                            Available commands:
                              sign-in <initials>
                              whoami
                              create-employee <initials>
                              create-project <name>
                              assign-leader <initials> <project>
                              add-activity <activity> <startWeek> <endWeek> <budgetedHours> <project>
                              assign-employee <initials> <activity> <project>
                              remove-employee <initials> <activity> <project>
                              register-hours <initials> <hours> <activity> <project>
                              register-absence <initials> <type> <hours>
                              view-report <project>
                              list-available
                              help / exit
                            """);
                        break;

                    case "sign-in":
                        sys.signIn(parts[1]);
                        System.out.println("Signed in as: " + sys.getSignedInUser().getInitials());
                        break;

                    case "whoami":
                        Employee current = sys.getSignedInUser();
                        System.out.println(current == null
                                ? "No user is currently signed in."
                                : "You are signed in as: " + current.getInitials());
                        break;

                    case "create-employee":
                        requireSignIn(sys);
                        sys.createEmployee(parts[1]);
                        System.out.println("Created employee: " + parts[1]);
                        break;

                    case "create-project":
                        requireSignIn(sys);
                        Project createdProject = sys.createProject(parts[1]);
                        System.out.printf("Created project: %s (%s)%n", createdProject.getName(), createdProject.getId());
                        break;

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

                    case "add-activity": {
                        requireSignIn(sys);
                        String actName = parts[1];
                        int startWeek = Integer.parseInt(parts[2]);
                        int endWeek = Integer.parseInt(parts[3]);
                        int budgetedHours = Integer.parseInt(parts[4]);
                        String projName = parts[5];
                        Project projectToAddActivity = sys.getProject(projName);
                        Activity newActivity = projectToAddActivity.createActivity(
                                actName, startWeek, endWeek, budgetedHours, sys.getSignedInUser());
                        System.out.printf("Added activity %s to %s (weeks %d–%d, %d budgeted hours)%n",
                                actName, projName, startWeek, endWeek, budgetedHours);
                        break;
                    }

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

                    case "remove-employee": {
                        requireSignIn(sys);
                        String ini = parts[1], actName = parts[2], projName = parts[3];
                        Project projectToRemoveFrom = sys.getProject(projName);
                        Activity activity = projectToRemoveFrom.findActivity(actName);
                        activity.removeEmployee(sys.getEmployee(ini));
                        System.out.printf("Removed %s from activity %s%n", ini, actName);
                        break;
                    }

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

                    case "register-absence": {
                        requireSignIn(sys);
                        String ini = parts[1], type = parts[2], h = parts[3];
                        int hrsAbs = Integer.parseInt(h);
                        sys.getEmployee(ini).registerAbsence(
                                AbsenceType.valueOf(type.toUpperCase()), hrsAbs);
                        System.out.printf("%s registered %d hours of %s%n", ini, hrsAbs, type);
                        break;
                    }

                    case "view-report": {
                        requireSignIn(sys);
                        String projName = parts[1];
                        Project projectToView = sys.getProject(projName);
                        if (projectToView.hasLeader() && !projectToView.isLeader(sys.getSignedInUser())) {
                            throw new SecurityException("Only the project leader can view the report.");
                        }
                        int total = projectToView.getTotalWorkHours();
                        System.out.printf("Total hours for %s: %d%n", projName, total);
                        break;
                    }

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

    private static void requireSignIn(SystemModel sys) {
        if (sys.getSignedInUser() == null) {
            throw new IllegalStateException("You must sign in first.");
        }
    }
}
