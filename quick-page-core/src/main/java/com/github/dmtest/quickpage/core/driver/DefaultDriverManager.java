package com.github.dmtest.quickpage.core.driver;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DefaultDriverManager implements DriverManager {
    private Environment environment;
    private WebDriver driver;
    private DesiredCapabilities capabilities;

    public DefaultDriverManager(Environment environment) {
        this.environment = environment;
    }

    @Override
    public WebDriver getDriver() {
        if (Objects.isNull(driver) || hasQuit(driver)) {
            String remoteWebdriverUrl = environment.getConfig().remoteWebdriverUrl();
            if (Objects.isNull(remoteWebdriverUrl)) {
                startLocalDriver();
            } else {
                startRemoteDriver(remoteWebdriverUrl);
            }
        }
        return driver;
    }

    private void startLocalDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void startRemoteDriver(String remoteWebdriverUrlStr) {
        DefaultConfig config = environment.getConfig();
        DesiredCapabilities remoteDriverCapabilities = new DesiredCapabilities();
        remoteDriverCapabilities.setVersion(config.remoteWebdriverBrowserVersion());
        remoteDriverCapabilities.setCapability("enableVNC", config.remoteWebDriverEnableVNC());
        remoteDriverCapabilities.setCapability("screenResolution", config.remoteWebDriverScreenResolution());
        remoteDriverCapabilities.setCapability("name", config.remoteWebdriverProjectName());
        remoteDriverCapabilities.setCapability("timeZone", config.remoteWebdriverTimezone());
        getCapabilities().merge(remoteDriverCapabilities);
        try {
            URL remoteWebdriverUrl = new URL(remoteWebdriverUrlStr);
            driver = new RemoteWebDriver(remoteWebdriverUrl, getCapabilities());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Can't start remote webdriver!", e);
        }
    }

    @Override
    public void quitDriver() {
        if (!Objects.isNull(driver) && !hasQuit(driver)) {
            getDriver().quit();
        }
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        if (Objects.isNull(capabilities)) {
            capabilities = new DesiredCapabilities();
        }
        return capabilities;
    }

    @Override
    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    private boolean hasQuit(@Nonnull WebDriver driver) {
        return Objects.isNull(((RemoteWebDriver) driver).getSessionId());
    }
}
