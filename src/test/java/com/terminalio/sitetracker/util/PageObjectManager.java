package com.terminalio.sitetracker.util;

import com.terminalio.sitetracker.pageObjects.HeaderPage;
import com.terminalio.sitetracker.pageObjects.docsresults.DataTableInLineEditResults;
import com.terminalio.sitetracker.pageObjects.docsresults.DocsPage;
import com.terminalio.sitetracker.pageObjects.filters.FilterPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;
    private HeaderPage headerPage;
    private FilterPage filterPage;
    private DataTableInLineEditResults dataTableResults;
    private DocsPage docsPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HeaderPage getHeaderPage() {
        return (headerPage == null) ? headerPage = new HeaderPage(driver) : headerPage;
    }

    public FilterPage getFilterPage() {
        return (filterPage == null) ? filterPage = new FilterPage(driver) : filterPage;
    }

    public DataTableInLineEditResults getDataTableResults() {
        return (dataTableResults == null) ? dataTableResults = new DataTableInLineEditResults(driver) : dataTableResults;
    }

    public DocsPage getDocsPage() {
        return (docsPage == null) ? docsPage = new DocsPage(driver, this) : docsPage;
    }

}
