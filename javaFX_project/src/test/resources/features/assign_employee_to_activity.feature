Feature: Assign employee to activity

  Scenario: Add an employee to an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    When I assign "JD01" to the activity "Design"
    Then "JD01" is assigned to the activity "Design"

   Scenario: Non-leader tries to assign employee
    Given a project named "ProjectX" with leader "JD01"
    And an employee with initials "mab1"
    When "mab1" tries to assign "JD01" to the activity "Design"
    Then a SecurityException is thrown

  Scenario: Leader tries to assign to non-existent activity
    Given a project named "ProjectX" with leader "JD01"
    And an employee with initials "mab1"
    When the leader "JD01" tries to assign "mab1" to the activity "GhostTask"
    Then an IllegalArgumentException is thrown