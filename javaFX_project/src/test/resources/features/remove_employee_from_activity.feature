Feature: Remove employee from activity

  Scenario: Remove an assigned employee from an activity
    Given an employee with initials "JD01"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    And the employee "JD01" is assigned to the activity "Design"
    When I remove "JD01" from the activity "Design"
    Then "JD01" is not assigned to the activity "Design"
    
  Scenario: Trying to remove a non-assigned employee should fail
    Given an employee with initials "JD02"
    And a project named "ProjectX"
    And an activity named "Design" in the project "ProjectX"
    When I try to remove "JD02" from the activity "Design"
    Then an IllegalArgumentException is thrown