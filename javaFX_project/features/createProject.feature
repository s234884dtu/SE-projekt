Feature: Create project

  Scenario: Create project with a name
   When a project is created with name "test"
   Then the list of projects contains a project with that name
