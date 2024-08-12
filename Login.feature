Feature: SwagLabs Login
  Scenario: Login with valid credentials
    Given I open the login page
    When I enter correct username and password
    Then I should be logged in successfully

  Scenario: Login with invalid credentials
    Given I open the login page
    When I enter incorrect username and password
    Then I should see an error message

 Scenario: Login with Locked user credentials
   Given I open the login page
   When I enter locked username and password
   Then I should see an lock user error message

  Scenario:Login with glitch user credentials
    Given I open the login page
    When I enter glitch username and password
    Then I should be logged in

