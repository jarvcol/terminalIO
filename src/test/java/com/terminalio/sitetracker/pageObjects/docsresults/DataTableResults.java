package com.terminalio.sitetracker.pageObjects.docsresults;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DataTableResults extends DocsResultsBody {

    public DataTableResults(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String genericInputLocator = ".//input[contains(@name, 'dt-inline-edit-')]";
    private String tableEditableCellElementLocator = ".//lightning-datatable//table//tr[{row}]//*[@data-label='{column}']";
    private String tableCellEditedElementLocator = ".//lightning-datatable//table//tr[{row}]//*[contains(@class, 'slds-is-edited')]";
    private String selectDateButtonLocator = ".//input[@name='dt-inline-edit-datetime']";
    private String rowEditButtonLocator = ".//lightning-datatable//table//tr[{row}]//*[@data-label='{column}']//button";
    private String inputTimeLocator = ".//input[@name='dt-inline-edit-datetime']";
    private String todayButtonDatePickerLocator = ".//button[@title='Select a date']";


    @Override
    public void updateResultsTableRowColumn(String rowNumber, String columnName, String newValue) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(getEditableButtonElementLocatorByRowColumn(rowNumber, columnName))));
        super.jsCLickExecution(getDriver().findElement(By.xpath(getEditableButtonElementLocatorByRowColumn(rowNumber, columnName))));
        //getDriver().findElement(By.xpath(getEditableButtonElementLocatorByRowColumn(rowNumber, columnName))).click();

        if(columnName.equals("CloseAt"))
            setEditableDate(newValue);
        else{
            getDriver().findElement(By.xpath(genericInputLocator)).clear();
            getDriver().findElement(By.xpath(genericInputLocator)).sendKeys(newValue+Keys.TAB);
        }
        getDriver().switchTo().defaultContent();
    }

    @Override
    public boolean checkUpdateResultsTableRowColumn(String rowNumber) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);
        boolean checkResult = getDriver().findElements(By.xpath(tableCellEditedElementLocator.replace("{row}", rowNumber))).size() == 5;
        getDriver().switchTo().defaultContent();
        return checkResult;
    }
    /*public boolean checkUpdateResultsTableRowColumn(String rowNumber, String columnName, String newValue) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        return getDriver().findElement(By.xpath(
                getEditableCellElementLocatorByRowColumn(rowNumber, columnName)))
                .getText().contains(newValue);
    }*/

    private void setEditableDate(String newDate){
        String[] dateSections = newDate.split(",");
        if(newDate.contains("Today")){
            getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(selectDateButtonLocator)));
            getDriver().findElement(By.xpath(selectDateButtonLocator)).click();
            getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(todayButtonDatePickerLocator)));
            super.jsCLickExecution(getDriver().findElement(By.xpath(todayButtonDatePickerLocator)));
        }
        else{
            getDriver().findElements(By.xpath(inputTimeLocator)).get(0).clear();
            getDriver().findElements(By.xpath(inputTimeLocator)).get(0).sendKeys(dateSections[0]+Keys.TAB);

        }
        getDriver().findElements(By.xpath(inputTimeLocator)).get(1).clear();
        getDriver().findElements(By.xpath(inputTimeLocator)).get(1).sendKeys(dateSections[1]+Keys.TAB);
    }

    private String getEditableCellElementLocatorByRowColumn(String rowNumber, String columnName){
        return tableEditableCellElementLocator.replace("{row}", rowNumber).replace("{column}", columnName);
    }

    private String getEditableButtonElementLocatorByRowColumn(String rowNumber, String columnName){
        return rowEditButtonLocator.replace("{row}", rowNumber).replace("{column}", columnName);
    }

}
