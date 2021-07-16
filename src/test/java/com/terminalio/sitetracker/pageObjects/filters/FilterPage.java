package com.terminalio.sitetracker.pageObjects.filters;

import com.terminalio.sitetracker.pageObjects.BasePage;
import com.terminalio.sitetracker.util.PageObjectManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FilterPage extends BasePage {

    public FilterPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath=".//input[@name='Quick Find']")
    private WebElement quickFindInput;

    private String componentResultLocator = ".//h3[contains(text(),'Lightning Web Components')]/following-sibling::componentreference-tree//span[@title='{replace}']";

    public void writeToQuickFindInput(String input){
        getWait().until(ExpectedConditions.visibilityOf(quickFindInput));
        quickFindInput.clear();
        quickFindInput.sendKeys(input);
    }

    public void clickOnComponentFilterResult(String resultText){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(componentResultLocatorValue(resultText))));
        getDriver().findElement(By.xpath(componentResultLocatorValue(resultText))).click();
    }

    private String componentResultLocatorValue(String value){
        return componentResultLocator.replace("{replace}", value);
    }

}
