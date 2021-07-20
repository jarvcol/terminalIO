package com.terminalio.sitetracker.pageObjects;

import com.terminalio.sitetracker.util.FileReaderManager;
import com.terminalio.sitetracker.util.PageObjectManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private PageObjectManager pageObjectManager;

    public BasePage(WebDriver driver){
        wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
        this.driver = driver;
    }

    public BasePage(WebDriver driver, PageObjectManager pageObjectManager){
        wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
        this.driver = driver;
        this.pageObjectManager = pageObjectManager;
    }

    protected WebDriverWait getWait(){
        return wait;
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected PageObjectManager getPageObjectManager(){
        return pageObjectManager;
    }

    protected void NavigateTo(String url){
        driver.get(url);
    }

    protected boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    protected boolean isElementVisible(WebElement element){
        return element.isDisplayed();
    }

    public boolean isAlertPresent(){
        boolean foundAlert = false;
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException exc) {
            foundAlert = false;
        }
        return foundAlert;
    }

    protected void closeAlert(){
        driver.switchTo().alert().dismiss();
    }

    protected void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    protected void acceptCookies(){
        getWait().until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
        getDriver().findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    protected void jsCLickExecution(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
