Feature: Assign project leader

  Scenario: Assign a project leader to a project
    Given a project named "ProjectX"
    And an employee with initials "JD01"
    When "admin" assigns "JD01" as the project leader of "ProjectX"
    Then "JD01" is the project leader of "ProjectX"

  Scenario: Cannot assign a non-existent project leader
    Given a project named "ProjectX"
    # note: we do NOT create JD99 here
    When "admin" tries to assign "JD99" as the project leader of "ProjectX"
    Then an IllegalArgumentException is thrown