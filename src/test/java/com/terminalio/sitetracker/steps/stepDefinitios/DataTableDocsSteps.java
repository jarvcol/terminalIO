package com.terminalio.sitetracker.steps.stepDefinitios;


import com.terminalio.sitetracker.pageObjects.docsresults.DocsPage;
import com.terminalio.sitetracker.util.TestContext;
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

    @When("the user navigates to the datatable component")
    public void userNavigatesToDataTableComp() {
        docsPage.clickOnComponentRef();
        docsPage.getFilterPage().writeToQuickFindInput("datatable");
        docsPage.clickComponentsFilter("datatable");
    }

    @Then("table results are shown")
    public void userIsAtDataTableComp() {
        docsPage.getDocsResultsBody().checkDataTablePageIsShown();
    }
}
