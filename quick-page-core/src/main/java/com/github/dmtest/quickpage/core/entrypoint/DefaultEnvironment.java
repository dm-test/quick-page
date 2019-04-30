package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;

public class DefaultEnvironment implements Environment {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;

    @Override
    public Environment setEnvironment(DriverManager driverManager, SearchManager searchManager, PageManager pageManager) {
        this.driverManager = driverManager;
        this.searchManager = searchManager;
        this.pageManager = pageManager;
        return this;
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
