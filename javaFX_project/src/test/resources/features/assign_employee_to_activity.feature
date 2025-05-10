Feature: Assign employee to activity

  Scenario: Add an employee to an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    When I assign "JD01" to the activity "Design"
    Then "JD01" is assigned to the activity "Design"
