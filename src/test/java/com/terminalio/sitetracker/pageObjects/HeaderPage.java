package com.terminalio.sitetracker.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends BasePage{

    public HeaderPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
