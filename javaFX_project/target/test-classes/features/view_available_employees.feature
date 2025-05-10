Feature: View available employees

  Scenario: List employees with no assigned activities
    Given an employee with initials "JD01"
    And an employee with initials "AB12"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When I list available employees
    Then only "AB12" should be listed
