Feature: Access endpoints
  Scenario: Access public endpoint without authentication
    When I access the public endpoint
    Then the response status should be 200

  Scenario: Access private endpoint without authentication
    When I access the private endpoint
    Then the response status should be 401

  Scenario: Access private endpoint with authentication
    Given I am logged in as "user" with password "password"
    When I access the private endpoint
    Then the response status should be 200