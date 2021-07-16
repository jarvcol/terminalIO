package com.terminalio.sitetracker.steps.stepDefinitios;


import com.terminalio.sitetracker.pageObjects.docsresults.DocsPage;
import com.terminalio.sitetracker.util.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DataTableDocsSteps {

    TestContext testContext;
    DocsPage docsPage;

    public DataTableDocsSteps(TestContext testContext) {
        this.testContext = testContext;
        this.docsPage = testContext.getPageObjectManager().getDocsPage();
    }

    @Given("a web browser is at the Salesforce documentation page")
    public void userOpensPage() {
        docsPage.goToPage();
    }

    @When("the user searches for (.*) component")
    public void userNavigatesToDataTableComp(String filter) {
        docsPage.clickOnComponentRef();
        docsPage.getFilterPage().writeToQuickFindInput(filter);
        docsPage.clickComponentsFilter(filter);
    }

    @When("the user specifies the (.*) type of data table")
    public void userSelectsTypeOfDataTable(String filter) {

    }

    @When("the user runs the datatable")
    public void userRunsDataTable(String filter) {

    }

    @When("the user updates the value from row (.*) with")
    public void userUpdatesTableRowWith(String filter, DataTable dataTable) {

    }

    @Then("table results are shown")
    public void userIsAtDataTableComp() {
        docsPage.getDocsResultsBody().checkDataTablePageIsShown();
    }

    @Then("table results are updated")
    public void resultsTableIsUpdated() {

    }
}
