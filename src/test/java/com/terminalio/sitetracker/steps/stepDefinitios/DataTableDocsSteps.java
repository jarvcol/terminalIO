package com.terminalio.sitetracker.steps.stepDefinitios;


import com.terminalio.sitetracker.pageObjects.docsresults.DocsPage;
import com.terminalio.sitetracker.util.TestContext;
import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class DataTableDocsSteps {

    TestContext testContext;
    DocsPage docsPage;

    private DataTable dataTable;

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
        docsPage.getDocsResultsBody().selectDataTableType(filter);
    }

    @When("the user runs the datatable")
    public void userRunsDataTable() {
        docsPage.getDocsResultsBody().clickOnRunButton();
    }

    @When("the user updates the value from row {}")
    public void userUpdatesTableRowWith(String rowNumber, DataTable dataTable) {
        this.dataTable = dataTable;
        for (int i=0; i<dataTable.asLists().get(0).size(); i++){
            docsPage.getDocsResultsBody().updateResultsTableRowColumn(rowNumber,
                    dataTable.asLists().get(0).get(i), dataTable.asLists().get(1).get(i));
        }
    }

    @Then("table results are shown")
    public void userIsAtDataTableComp() {
        docsPage.getDocsResultsBody().checkDataTablePageIsShown();
    }

    @Then("table results are updated for row (.*)")
    public void resultsTableIsUpdated(String rowNumber) {
        Assert.assertTrue("Values were not updated", docsPage.getDocsResultsBody().checkUpdateResultsTableRowColumnCount(rowNumber));

        for (int i=0; i<dataTable.asLists().get(0).size(); i++){
            Assert.assertTrue("Value "+dataTable.asLists().get(0).get(i)+" was not updated", docsPage.getDocsResultsBody().
                    checkUpdateResultsTableRowColumn(rowNumber, dataTable.asLists().get(0).get(i),
                            dataTable.asLists().get(1).get(i)));
        }
    }
}
