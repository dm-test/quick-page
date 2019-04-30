package com.github.dmtest.quickpage.core.driver;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DefaultDriverManager implements DriverManager {
    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        if (Objects.isNull(driver) || hasQuit(driver)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    @Override
    public void quitDriver() {
        if (!Objects.isNull(driver) && !hasQuit(driver)) {
            getDriver().quit();
        }
    }

    private boolean hasQuit(@Nonnull WebDriver driver) {
        return Objects.isNull(((RemoteWebDriver) driver).getSessionId());
    }
}
