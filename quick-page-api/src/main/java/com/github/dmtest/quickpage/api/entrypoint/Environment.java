package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.PageManager;
import org.openqa.selenium.WebDriver;

public interface Environment {

    DriverManager getDriverManager();

    SearchManager getSearchManager();

    PageManager getPageManager();

    Environment setEnvironment(DriverManager driverManager, SearchManager searchManager, PageManager pageManager);

    default WebDriver getDriver() {
        return getDriverManager().getDriver();
    }
}
