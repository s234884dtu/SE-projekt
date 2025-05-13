Feature: Create activity with details

  Scenario: Add an activity with details
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    When I add an activity named "Design" with start date "2025-05-05", end date "2025-06-30", and 20.0 budgeted hours to "ProjectX"
    Then the project "ProjectX" has an activity named "Design"
    And the activity "Design" has start date "2025-05-05", end date "2025-06-30", and 20.0 budgeted hours

    Scenario: Project leader enters an invalid duration
    Given a project named "ProjectX" with leader "JD01"
    When the leader "JD01" tries to add an activity named "Oops" with start date "2025-05-01", end date "2025-05-07", and -3.0 budgeted hours to "ProjectX"
    Then an IllegalArgumentException is thrown