Feature: Remove employee from activity

  Scenario: Remove an assigned employee from an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When I remove "JD01" from the activity "Design"
    Then "JD01" is not assigned to the activity "Design"
