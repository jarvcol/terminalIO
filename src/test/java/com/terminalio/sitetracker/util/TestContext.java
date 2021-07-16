package com.terminalio.sitetracker.util;

public class TestContext {

    private WebDriverSetup webDriverSetUp;
    private PageObjectManager pageObjectManager;

    public TestContext(){
        webDriverSetUp = new WebDriverSetup();
        pageObjectManager = new PageObjectManager(webDriverSetUp.getDriver());
    }

    public WebDriverSetup getWebDriverManager() {
        return webDriverSetUp;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}