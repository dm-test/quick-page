package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class DefaultEnvironment implements Environment {
    private static ThreadLocal<Environment> environmentContainer = new ThreadLocal<>();
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;

    private DefaultEnvironment(DriverManager driverManager, PageManager pageManager, SearchManager searchManager) {
        this.driverManager = driverManager;
        this.pageManager = pageManager;
        this.searchManager = searchManager;
    }

    public static Environment getEnvironment() {
        Optional<Environment> environment = Optional.ofNullable(environmentContainer.get());
        return environment.orElseThrow(() -> new IllegalStateException("Set environment before use!"));
    }

    public static void setEnvironment() {
        Environment environment = new DefaultEnvironment(
                new DefaultDriverManager(), new DefaultPageManager(), new DefaultSearchManager());
        environmentContainer.set(environment);
    }

    public static void setEnvironment(DriverManager driverManager, PageManager pageManager, SearchManager searchManager) {
        Environment environment = new DefaultEnvironment(driverManager, pageManager, searchManager);
        environmentContainer.set(environment);
    }

    /**
     * Короткий способ получения объекта драйвера
     * @return
     */
    public static WebDriver getDriver() {
        return getEnvironment().getDriverManager().getDriver();
    }

    @Override
    public DriverManager getDriverManager() {
        return driverManager;
    }

    @Override
    public PageManager getPageManager() {
        return pageManager;
    }

    @Override
    public SearchManager getSearchManager() {
        return searchManager;
    }
}
