@resources 
Feature: Create Resource
 

  Scenario: User creates a new resource successfully
    
    When user clicks on Resources menu
    And user clicks on Add New button
    And user enters resource details
    And user clicks on Add button
    Then resource should be created successfully
