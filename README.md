for at køre test for cucumber og junit skriv først exit dernæst cd javafx_project også mvn clean verify




hvis du så ville prøve at bruge programmet så kører du CLIApp.java
hvor den så vil skrive i terminalen
Welcome to the Project Management and Time-Tracking CLI. Type 'help' for commands.
hvis du så skriver help i terminalen får du dette svar

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

  Eksempel på hvad du kan skrive og hvad den siger
  sign-in huba
  og terminalen skriver tilbage "Signed in as: huba"
  dernæst kan du skrive:
  create-project hus
  også skriver terminalen "Created project: hus (2025001)"
  hvis du vil assign huba som projektleader skriv:
  assign-leader huba hus
  terminalen skriver "Assigned huba as leader of hus"
  tilføj aktivitet med:
  add-activity fiksgulv 2015-05-01 2025-05-30 15.5 hus
  og terminalen skriver "Added activity fiksgulv to hus (from 2015-05-01 to 2025-05-30, 15,5 budgeted hours)"

