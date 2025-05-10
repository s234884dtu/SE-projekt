Feature: Create employee

  Scenario: Create employee with 4-letter initials
    When I create an employee with initials "JD01"
    Then the employee with initials "JD01" exists in the system
