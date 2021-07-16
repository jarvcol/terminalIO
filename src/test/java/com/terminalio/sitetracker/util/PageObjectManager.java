package com.terminalio.sitetracker.util;

import com.terminalio.sitetracker.pageObjects.HeaderPage;
import com.terminalio.sitetracker.pageObjects.docsresults.DataTableResults;
import com.terminalio.sitetracker.pageObjects.docsresults.DocsPage;
import com.terminalio.sitetracker.pageObjects.filters.FilterPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private static WebDriver driver;
    private static HeaderPage headerPage;
    private static FilterPage filterPage;
    private static DataTableResults dataTableResults;
    private static DocsPage docsPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public static HeaderPage getHeaderPage() {
        return (headerPage == null) ? headerPage = new HeaderPage(driver) : headerPage;
    }

    public static FilterPage getFilterPage() {
        return (filterPage == null) ? filterPage = new FilterPage(driver) : filterPage;
    }

    public static DataTableResults getDataTableResults() {
        return (dataTableResults == null) ? dataTableResults = new DataTableResults(driver) : dataTableResults;
    }

    public static DocsPage getDocsPage() {
        return (docsPage == null) ? docsPage = new DocsPage(driver) : docsPage;
    }

}
