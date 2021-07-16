package com.terminalio.sitetracker.pageObjects.docsresults;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DataTableResults extends DocsResultsBody {

    public DataTableResults(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkDataTablePageIsShown(){
        return getDriver().getCurrentUrl().contains("lightning-datatable");
    }

}
