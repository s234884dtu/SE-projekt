Feature: Register work hours

  Scenario: Employee registers hours on an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When the employee "JD01" registers 5.0 hours on the activity "Design"
    Then the employee "JD01" has registered 5.0 hours on the activity "Design"

  Scenario: Employee tries to register a negative number of hours
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When the employee "JD01" tries to register -2.0 hours on the activity "Design"
    Then an IllegalArgumentException is thrown

  Scenario: Employee tries to register hours not on the half-hour boundary
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When the employee "JD01" tries to register 1.25 hours on the activity "Design"
    Then an IllegalArgumentException is thrown
