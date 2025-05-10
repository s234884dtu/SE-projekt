Feature: Create activity in a project

  Scenario: Add an activity to a project
    Given a project named "ProjectX"
    When I add an activity named "Design" to "ProjectX"
    Then the project "ProjectX" has an activity named "Design"
