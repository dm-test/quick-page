package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import org.openqa.selenium.WebDriver;

public interface Environment {

    DriverManager getDriverManager();

    SearchManager getSearchManager();

    PageManager getPageManager();

    <T extends DefaultConfig> PropertyManager<T> getPropertyManager();

    Environment setEnvironment(PropertyManager propertyManager, DriverManager driverManager, SearchManager searchManager, PageManager pageManager);

    void setEnvironmentDefaults();

    // Shortcuts
    default <T extends DefaultConfig> T getConfig() {
        PropertyManager<T> propertyManager = getPropertyManager();
        return propertyManager.getConfig();
    }

    default WebDriver getDriver() {
        return getDriverManager().getDriver();
    }

    default <T extends Page> T getCurrentPage() {
        return getPageManager().getCurrentPage();
    }
}
