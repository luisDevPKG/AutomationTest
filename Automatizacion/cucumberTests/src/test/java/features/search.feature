Feature: As a Serenity user I want to login with my username and password
  Scenario: Login successfully

    Given as user of Serenity
    When the user enter his username
    And his password in the login form
    And the user clicks the login button
    Then the user must enter the page and view the dashboard