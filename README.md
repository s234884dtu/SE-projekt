for at køre test for cucumber og junit skriv først exit dernæst cd javafx_project også mvn clean verify<br><br><br>




hvis du så ville prøve at bruge programmet så kører du CLIApp.java som java code<br>
hvor den så vil skrive i terminalen<br>
"Welcome to the Project Management and Time-Tracking CLI. Type 'help' for commands."<br>
hvis du så skriver help i terminalen får du dette svar<br><br>

Available commands:<br>
sign-in <initials><br>
whoami<br>
create-employee <initials><br>
  create-project <name><br>
  assign-leader <initials> <project><br>
  add-activity <activity> <start:yyyy-MM-dd> <end:yyyy-MM-dd> <budgetedHours> <project><br>
  assign-employee <initials> <activity> <project><br>
  remove-employee <initials> <activity> <project><br>
  register-hours <initials> <hours> <activity> <project><br>
  register-absence-period <initials> <type> <start:yyyy-MM-dd> <end:yyyy-MM-dd><br>
  view-report <project><br>
  list-available<br>
  help / exit <br><br><br>
 

  
Eksempel på hvad du kan skrive og hvad den siger<br>
  sign-in huba<br>
  og terminalen skriver tilbage "Signed in as: huba"<br>
  dernæst kan du skrive:<br>
  create-project hus<br>
  også skriver terminalen "Created project: hus (2025001)"<br>
hvis du vil assign huba som projektleader skriv:<br>
assign-leader huba hus<br>
terminalen skriver "Assigned huba as leader of hus"<br>
  tilføj aktivitet med:<br>
  add-activity fiksgulv 2015-05-01 2025-05-30 15.5 hus<br>
  og terminalen skriver "Added activity fiksgulv to hus (from 2015-05-01 to 2025-05-30, 15,5 budgeted hours)"<br>

