package com.terminalio.sitetracker.pageObjects.docsresults;

import com.terminalio.sitetracker.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public abstract class DocsResultsBody extends BasePage {
    public DocsResultsBody(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String selectDataTableTypeSelector = ".//input[@role='combobox']";
    private String selectDataTableTypeOptionsLocator = ".//lightning-base-combobox-item//span[@title='{replace}']";
    protected String iframeGenericLocator = ".//iframe[@name='preview']";
    private String tableWaitLocator = ".//lightning-datatable//table//tr[1]//*[@data-label='Label']//span";

    @FindBy(xpath=".//button[contains(text(), 'Run')]")
    private WebElement runButton;

    public void clickOnRunButton(){
        getWait().until(ExpectedConditions.visibilityOf(runButton));
        runButton.click();
    }

    public boolean checkDataTablePageIsShown(){
        return getDriver().getCurrentUrl().contains("lightning-datatable");
    }

    public void selectDataTableType(String tableType){
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);
        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableWaitLocator)));

        getDriver().switchTo().defaultContent();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectDataTableTypeSelector)));
        //super.jsCLickExecution(getDriver().findElement(By.xpath(selectDataTableTypeSelector)));
        getDriver().findElement(By.xpath(selectDataTableTypeSelector)).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getSelectDataTableTypeOptionsLocator(tableType))));
        getDriver().findElement(By.xpath(getSelectDataTableTypeOptionsLocator(tableType))).click();
    }

    private String getSelectDataTableTypeOptionsLocator(String optioName){
        return selectDataTableTypeOptionsLocator.replace("{replace}", optioName);
    }

    public abstract void updateResultsTableRowColumn(String rowNumber, String columnName, String newValue);

    public abstract boolean checkUpdateResultsTableRowColumn(String rowNumber);

}
