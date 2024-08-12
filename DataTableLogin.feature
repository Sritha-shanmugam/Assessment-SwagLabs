Feature: SwagLabs Login using DataTable
  Scenario Outline: Login with valid credentials
    Given User open the login page
    When User enter "<Username>" and "<Password>"
    And Click Login button
    Examples:
      | Username | Password |
      |standard_user|secret_sauce|
      |problem_user |secret_sauce|
      |locked_out_user|secret_sauce|
      |performance_glitch_user|secret_sauce|



