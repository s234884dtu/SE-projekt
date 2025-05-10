Feature: Register absence

  Scenario: Register vacation hours for an employee
    Given an employee with initials "JD01"
    When I register 8 hours of VACATION for "JD01"
    Then "JD01" has 8 hours of VACATION absence
