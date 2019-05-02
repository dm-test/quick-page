package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import org.openqa.selenium.WebDriver;

public interface Environment {

    DriverManager getDriverManager();

    SearchManager getSearchManager();

    PageManager getPageManager();

    <T extends DefaultConfig> PropertyManager<T> getPropertyManager();

    Environment setEnvironment(DriverManager driverManager, SearchManager searchManager, PageManager pageManager, PropertyManager propertyManager);

    default WebDriver getDriver() {
        return getDriverManager().getDriver();
    }
}
