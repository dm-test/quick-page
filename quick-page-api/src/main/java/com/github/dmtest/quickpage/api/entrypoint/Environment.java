package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.PageManager;
import org.openqa.selenium.WebDriver;

public interface Environment<T> {

    DriverManager getDriverManager();

    PageManager getPageManager();

    SearchManager getSearchManager();

    T setDriverManager(DriverManager driverManager);

    T setPageManager(PageManager pageManager);

    T setSearchManager(SearchManager searchManager);

    T setDefaults();

    default WebDriver getDriver() {
        return getDriverManager().getDriver();
    }
}
