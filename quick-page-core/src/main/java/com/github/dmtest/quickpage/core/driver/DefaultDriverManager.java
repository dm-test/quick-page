package com.github.dmtest.quickpage.core.driver;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public class DefaultDriverManager implements DriverManager {
    private static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();

    @Override
    public WebDriver getDriver() {
        if (Objects.isNull(driverContainer.get()) || hasQuit(driverContainer.get())) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driverContainer.set(driver);
        }
        return driverContainer.get();
    }

    @Override
    public void quitDriver() {
        if (!Objects.isNull(driverContainer.get()) || !hasQuit(driverContainer.get())) {
            getDriver().quit();
        }
    }

    private static boolean hasQuit(WebDriver driver) {
        return Objects.isNull(((RemoteWebDriver) driver).getSessionId());
    }
}
