package com.terminalio.sitetracker.steps.stepDefinitios;

import com.terminalio.sitetracker.util.TestContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks{

    TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void InitializeTest() throws Exception {
        testContext.getWebDriverManager().getDriver();
    }

    @After
    public void TearDownTest() {
        testContext.getWebDriverManager().closeDriver();
    }

}