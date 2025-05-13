Feature: Create employee

  Scenario: Create employee with 4-letter initials
    When I create an employee with initials "JD01"
    Then the employee with initials "JD01" exists in the system

  Scenario: Cannot create employee with empty initials
    When I try to create an employee with initials ""
    Then an IllegalArgumentException is thrown

  Scenario: Cannot create employee with more than 4 letters
    When I try to create an employee with initials "ABCDE"
    Then an IllegalArgumentException is thrown

  Scenario: Cannot create duplicate employee
    Given an employee with initials "JD01"
    When I try to create an employee with initials "JD01"
    Then an IllegalArgumentException is thrown

