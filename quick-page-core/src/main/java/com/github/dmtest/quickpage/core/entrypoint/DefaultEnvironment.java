package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;

public class DefaultEnvironment implements Environment {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;

    public DefaultEnvironment(DriverManager driverManager, PageManager pageManager, SearchManager searchManager) {
        this.driverManager = driverManager;
        this.pageManager = pageManager;
        this.searchManager = searchManager;
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
