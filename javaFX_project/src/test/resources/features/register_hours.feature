Feature: Register work hours

  Scenario: Employee registers hours on an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When the employee "JD01" registers 5 hours on the activity "Design"
    Then the employee "JD01" has registered 5 hours on the activity "Design"
