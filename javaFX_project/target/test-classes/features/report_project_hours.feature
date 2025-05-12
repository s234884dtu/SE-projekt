Feature: View project report

  Scenario: View total hours worked in a project
    Given a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And an employee with initials "JD01"
    And the employee "JD01" is assigned to the activity "Design"
    And the employee "JD01" registers 4.0 hours on the activity "Design"
    When I view the total hours of project "ProjectX"
    Then the total hours should be 4
