Feature: View project report

  Scenario: View total hours worked in a project
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And "JD01" adds an activity named "Design" with start date "2025-05-01", end date "2025-05-14", and 10.0 budgeted hours to "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    And the employee "JD01" registers 4.0 hours on the activity "Design"
    When I view the total hours of project "ProjectX"
    Then the total hours should be 4.0

  Scenario: View total hours when no hours are registered
    Given an employee with initials "JD02"
    And a project named "ProjectY"
    And an activity named "Planning" in the project "ProjectY"
    When I view the total hours of project "ProjectY"
    Then the total hours should be 0.0