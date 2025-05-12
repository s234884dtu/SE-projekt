Feature: Register absence

  Scenario: Register vacation for an employee
    Given an employee with initials "JD01"
    When I register VACATION absence for "JD01" from "2025-07-01" to "2025-07-05"
    Then "JD01" has a VACATION absence from "2025-07-01" to "2025-07-05"
