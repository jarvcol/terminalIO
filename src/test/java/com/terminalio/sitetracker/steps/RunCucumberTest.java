package com.terminalio.sitetracker.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome=true,
        plugin = {
                "pretty",
                "html:target/site/cucumber-pretty",
                "junit:target/cucumber.xml",
                "rerun:target/rerun.txt",
                "json:target/cucumber.json"}
)
public class RunCucumberTest {
}
