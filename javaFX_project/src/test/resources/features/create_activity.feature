Feature: Create activity

  Scenario: Add an activity with details
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    When I add an activity named "Design" with start week 10, end week 12, and 20 budgeted hours to "ProjectX"
    Then the project "ProjectX" has an activity named "Design"
