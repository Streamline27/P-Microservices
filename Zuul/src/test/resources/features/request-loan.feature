Feature: Testing a REST API

  Scenario: User can create loan request
    When user creates loan request with following details:
      | yearlyTurnover            | 300000              |
      | companyRegistrationNumber | LV-1063             |
      | amount                    | 3000                |
      | email                     | company@company.com |
      | companyName               | Vasjas Inc.         |
      | phone                     | Vasjas Inc.         |
      | term                      | 6                   |
    And validates it
    And confirms validated request
    And gets created loan
    Then loan contains request id

  Scenario: User can't confirm non validated request
    When user creates loan request with following details:
      | yearlyTurnover            | 3000                |
      | companyRegistrationNumber | LV-1064             |
      | amount                    | 3000                |
      | email                     | company@company.com |
      | companyName               | Vasjas Inc.         |
      | phone                     | Vasjas Inc.         |
      | term                      | 6                   |
    And validates it
    Then response shows that request is invalid