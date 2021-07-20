package com.terminalio.sitetracker.pageObjects.docsresults;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DataTableResults extends DocsResultsBody {

    public DataTableResults(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String rowEditButtonLocator = ".//lightning-datatable//table//tr[{row}]//*[@data-label='{column}']//button[@data-action-edit='true']";
    private String genericInputLocator = "input[name='dt-inline-edit-text']";
    private String tableEditableCellElementLocator = ".//lightning-datatable//table//tr[{row}]//*[@data-label='{column}']";

    private String iframeGenericLocator = ".//iframe[@name='preview']";
    private String selectDateButtonLocator = "button[title='Select a date']";
    private String inputTimeLocator = "input[name='dt-inline-edit-datetime']";


    @Override
    public void updateResultsTableRowColumn(String rowNumber, String columnName, String newValue) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(getEditableButtonElementLocatorByRowColumn(rowNumber, columnName))));
        super.jsCLickExecution(getDriver().findElement(By.xpath(getEditableButtonElementLocatorByRowColumn(rowNumber, columnName))));

        if(columnName.equals("CloseAt"))
            setEditableDate(newValue);
        else{
            getDriver().findElement(By.cssSelector(genericInputLocator)).clear();
            getDriver().findElement(By.cssSelector(genericInputLocator)).sendKeys(newValue+Keys.TAB);
        }
        getDriver().switchTo().defaultContent();
    }

    @Override
    public boolean checkUpdateResultsTableRowColumn(String rowNumber, String columnName, String newValue) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        return getDriver().findElement(By.xpath(
                getEditableCellElementLocatorByRowColumn(rowNumber, columnName)))
                .getText().contains(newValue);
    }

    private void setEditableDate(String newDate){
        String[] dateSections = newDate.split(",");
        if(newDate.contains("Today")){
            //TODO: Logic to set date clicking on today
            getDriver().findElement(By.cssSelector(selectDateButtonLocator)).click();
        }
        else{
            getDriver().findElements(By.cssSelector(inputTimeLocator)).get(0).clear();
            getDriver().findElements(By.cssSelector(inputTimeLocator)).get(0).sendKeys(dateSections[0]+Keys.TAB);

        }
        getDriver().findElements(By.cssSelector(inputTimeLocator)).get(1).clear();
        getDriver().findElements(By.cssSelector(inputTimeLocator)).get(1).sendKeys(dateSections[1]+Keys.TAB);
    }

    private String getEditableButtonElementLocatorByRowColumn(String rowNumber, String columnName){
        return rowEditButtonLocator.replace("{row}", rowNumber).replace("{column}", columnName);
    }

    private String getEditableCellElementLocatorByRowColumn(String rowNumber, String columnName){
        return tableEditableCellElementLocator.replace("{row}", rowNumber).replace("{column}", columnName);
    }

}
