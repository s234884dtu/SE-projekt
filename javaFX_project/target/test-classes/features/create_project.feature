Feature: Create project

  Scenario: Create project with a unique name
    When I create a project named "ProjectX"
    Then the project named "ProjectX" exists in the system
