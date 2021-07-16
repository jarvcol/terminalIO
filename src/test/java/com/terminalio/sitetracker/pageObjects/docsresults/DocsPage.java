package com.terminalio.sitetracker.pageObjects.docsresults;

import com.terminalio.sitetracker.pageObjects.BasePage;
import com.terminalio.sitetracker.pageObjects.HeaderPage;
import com.terminalio.sitetracker.pageObjects.filters.FilterPage;
import com.terminalio.sitetracker.util.PageObjectManager;
import com.terminalio.sitetracker.util.TestContext;
import com.terminalio.sitetracker.util.UrlSetUp;
import gherkin.lexer.Pa;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DocsPage extends BasePage {

    public DocsPage(WebDriver driver, PageObjectManager pageObjectManager){
        super(driver);
        PageFactory.initElements(driver, this);
        this.pageObjectManager = pageObjectManager;
        headerPage = pageObjectManager.getHeaderPage();
        filterPage = pageObjectManager.getFilterPage();
    }

    private PageObjectManager pageObjectManager;
    private HeaderPage headerPage;
    private FilterPage filterPage;
    private DocsResultsBody docsResultsBody;

    @FindBy(xpath=".//a[@title='Component Reference']")
    private WebElement compReferenceLink;

    @FindBy(xpath=".//a[@title='Developer Guide']")
    private WebElement devGuideLink;

    public void goToPage(){
        super.NavigateTo(UrlSetUp.getUrl());
        super.acceptCookies();
    }

    public void clickOnComponentRef(){
        getWait().until(ExpectedConditions.elementToBeClickable(compReferenceLink));
        super.jsExecution(compReferenceLink);
    }

    public FilterPage getFilterPage(){
        return filterPage;
    }

    public void clickComponentsFilter(String input){
        filterPage.clickOnComponentFilterResult(input);
        docsResultsBody = pageObjectManager.getDataTableResults();
    }

    public DocsResultsBody getDocsResultsBody(){
        return docsResultsBody;
    }

}
