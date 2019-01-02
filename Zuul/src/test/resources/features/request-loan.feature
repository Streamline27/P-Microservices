Feature: Testing a REST API

  Scenario: user can create loan request
    When user creates loan request
    And validates it
    And confirms validated request
    And gets created loan
    Then loan contains request id
