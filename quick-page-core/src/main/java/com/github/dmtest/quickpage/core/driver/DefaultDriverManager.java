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
            driver.manage().window().maximize();
        }
        return driver;
    }

    private void startLocalDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void startRemoteDriver(String remoteWebdriverUrlStr) {
        addRemoteDriverCapabilities();
        try {
            URL remoteWebdriverUrl = new URL(remoteWebdriverUrlStr);
            driver = new RemoteWebDriver(remoteWebdriverUrl, getCapabilities());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Can't start remote webdriver!", e);
        }
    }

    private void addRemoteDriverCapabilities() {
        DefaultConfig config = environment.getConfig();
        DesiredCapabilities remoteDriverCapabilities = new DesiredCapabilities();
        remoteDriverCapabilities.setBrowserName(config.browserName());
        remoteDriverCapabilities.setVersion(config.remoteWebdriverBrowserVersion());
        remoteDriverCapabilities.setCapability("enableVNC", config.remoteWebDriverEnableVNC());
        remoteDriverCapabilities.setCapability("screenResolution", config.remoteWebDriverScreenResolution());
        remoteDriverCapabilities.setCapability("name", config.remoteWebdriverProjectName());
        getCapabilities().merge(remoteDriverCapabilities);
    }

    public DesiredCapabilities getCapabilities() {
        if (Objects.isNull(capabilities)) {
            capabilities = new DesiredCapabilities();
        }
        return capabilities;
    }


    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
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
