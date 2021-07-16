Feature: Salesforce feature

  Scenario: Base Test Scenario
    Given a web browser is at the Salesforce documentation page
    When the user searches for datatable component
    Then table results are shown

  Scenario: Modify Datatable from Inline Edit
    Given a web browser is at the Salesforce documentation page
    When the user searches for datatable component
    And the user specifies the Datatable from Inline Edit type of data table
    And the user runs the datatable
    And the user updates the value from row 3 with
      | Label      | Website            | Phone number   | CloseAt           | Balance |
      | Larry Page | https://google.com | (555)-755-6575 | ‘Today’, 12:57 PM | 770.54  |
    Then table results are updated