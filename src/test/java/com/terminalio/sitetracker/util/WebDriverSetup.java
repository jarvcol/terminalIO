package com.terminalio.sitetracker.util;

import com.terminalio.sitetracker.enums.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSetup {
    private WebDriver driver;
    private static DriverType driverType;

    public WebDriverSetup() {
        driverType = driverType.valueOf(FileReaderManager.getInstance().getConfigReader().getBrowser().toLowerCase().trim());
    }

    public WebDriver getDriver() {
        if(driver == null) driver = setUpDriver();
        return driver;
    }

    private WebDriver setUpDriver() {
        switch (driverType) {
            case firefox : driver = new FirefoxDriver();
                break;
            case chrome :
                WebDriverManager.chromedriver().browserVersion("87.0.4280.141").setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case ie : driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}