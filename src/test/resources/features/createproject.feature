@ProjectCreation
Feature: Project Creation

  Background:
    Given user launches the application
    When user enters valid username and password
    And user clicks on login button
    Then user should be redirected to dashboard

  Scenario: User adds new project successfully
    When user clicks on Project menu
    And user clicks on Add New Project button
    And user enters project details
    And user clicks on Add button
    Then project should be created successfully
