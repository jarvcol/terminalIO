Feature: Salesforce feature

  Scenario: Modify table in documentation
    Given a web browser is at the Salesforce documentation page
    When the user navigates to the datatable component
    Then table results are shown
