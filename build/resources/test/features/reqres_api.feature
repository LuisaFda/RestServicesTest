Feature: Use reqresApi service
  As a user
  I need to use the basic operations of the service
  To manage user information

  Background:
    Given the user consumes the service

  Scenario Outline: List a resource
    When He converts the information consulted the service <path> for the user <idResource>
    Then it will validate the result <response>
    Examples:
      | idResource | path         | response |
      | 2          | api/unknown/ | 200      |
      | 23         | api/unknown/ | 404      |

  Scenario Outline: Login
    When the user consults the login of the application in <path> with <email> and <password>
    Then it will validate the <response> and the message

    Examples:
      | email              | password   | path      | response |
      | eve.holt@reqres.in | cityslicka | api/login | 200      |
      | peter@klaven       |            | api/login | 400      |


  Scenario Outline: Update a user
    When the user update a user of the application in <path> with <name> and <job>
    Then it will validate the <response> and the the changes

    Examples:
      | name        | job           | path        | response |
      | morpheus    | zion resident | api/login/2 | 200      |
      | Emma@klaven | Wong          | api/login/3 | 200      |