package com.terminalio.sitetracker.pageObjects.docsresults;

import com.terminalio.sitetracker.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class DocsResultsBody extends BasePage {
    public DocsResultsBody(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public abstract boolean checkDataTablePageIsShown();
}
