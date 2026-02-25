@Login
Feature: Login Feature

  Scenario: Valid user login
    Given user launches the application
    When user enters valid username and password
    And user clicks on login button
    Then user should be redirected to dashboard