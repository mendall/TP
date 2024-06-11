Feature: User management

  Scenario: Get all users
    Given the following users exist:
      | id | name     |
      | 1  | John Doe |
      | 2  | Jane Doe |
    When I get all users
    Then the response contains the following users:
      | id | name     |
      | 1  | John Doe |
      | 2  | Jane Doe |
