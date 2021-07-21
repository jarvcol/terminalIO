package com.terminalio.sitetracker.pageObjects.docsresults;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataTableInLineEditResults extends DocsResultsBody {

    public DataTableInLineEditResults(WebDriver driver){
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
    public boolean checkUpdateResultsTableRowColumnCount(String rowNumber) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);
        boolean checkResult = getDriver().findElements(By.xpath(tableCellEditedElementLocator.replace("{row}", rowNumber))).size() == 5;
        getDriver().switchTo().defaultContent();
        return checkResult;
    }

    public boolean checkUpdateResultsTableRowColumn(String rowNumber, String columnName, String expectedValue) {
        WebElement firstIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(firstIframe);

        WebElement secondIframe = getDriver().findElements(By.xpath(iframeGenericLocator)).get(0);
        getDriver().switchTo().frame(secondIframe);

        boolean checkResult;
        if(expectedValue.contains("Today")){
            checkResult = getDriver().findElement(By.xpath(
                    getEditableCellElementLocatorByRowColumn(rowNumber, columnName)))
                    .getText().contains(expectedValue.split(",")[0].replace("Today", getCurrentDateFormated()));
        }else{
            checkResult = getDriver().findElement(By.xpath(
                    getEditableCellElementLocatorByRowColumn(rowNumber, columnName)))
                    .getText().contains(expectedValue);
        }
        getDriver().switchTo().defaultContent();
        return checkResult;
    }

    private void setEditableDate(String newDate){
        String[] dateSections = newDate.split(",");
        if(newDate.contains("Today")){
            getDriver().findElements(By.xpath(inputTimeLocator)).get(0).clear();
            getDriver().findElements(By.xpath(inputTimeLocator)).get(0).sendKeys(getCurrentDateFormated()+Keys.TAB);
            /*getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(selectDateButtonLocator)));
            getDriver().findElement(By.xpath(selectDateButtonLocator)).click();
            getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(todayButtonDatePickerLocator)));
            super.jsCLickExecution(getDriver().findElement(By.xpath(todayButtonDatePickerLocator)));
            getDriver().findElement(By.xpath(todayButtonDatePickerLocator)).click();*/
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

    private String getCurrentDateFormated(){
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());

    }

}
