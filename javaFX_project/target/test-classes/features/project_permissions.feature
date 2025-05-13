Feature: Project permissions

  Scenario: Non-leader tries to assign employee
    Given an employee with initials "JD02"
    And a project named "ProjectX" with leader "JD01"
    When "JD02" assigns "JD02" to the activity "Design"
    Then a SecurityException is thrown

  Scenario: Non-leader tries to create activity
    Given an employee with initials "JD02"
    And a project named "ProjectX" with leader "JD01"
    When "JD02" tries to add an activity named "Blocked" with start date "2025-06-01", end date "2025-06-05", and 5.0 budgeted hours to "ProjectX"
    Then a SecurityException is thrown
