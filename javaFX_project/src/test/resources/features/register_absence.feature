Feature: Register absence

  Scenario: Register vacation for an employee
    Given an employee with initials "JD01"
    When I register VACATION absence for "JD01" from "2025-07-01" to "2025-07-05"
    Then "JD01" has a VACATION absence from "2025-07-01" to "2025-07-05"
  
  Scenario: End date before start date
    Given an employee with initials "JD01"
    When I try to register VACATION absence for "JD01" from "2025-07-05" to "2025-07-01"
    Then an IllegalArgumentException is thrown

  Scenario: Invalid absence type
    Given an employee with initials "JD01"
    When I try to register BREAK absence for "JD01" from "2025-07-01" to "2025-07-05"
    Then an IllegalArgumentException is thrown