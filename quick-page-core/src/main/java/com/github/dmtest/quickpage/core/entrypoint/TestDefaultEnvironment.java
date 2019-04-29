package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;

public class TestDefaultEnvironment implements Environment<TestDefaultEnvironment> {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;

    @Override
    public TestDefaultEnvironment setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
        return this;
    }

    @Override
    public TestDefaultEnvironment setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
        return this;
    }

    @Override
    public TestDefaultEnvironment setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
        return this;
    }

    @Override
    public TestDefaultEnvironment setDefaults() {
        setDriverManager(new DefaultDriverManager());
        setPageManager(new DefaultPageManager());
        setSearchManager(new DefaultSearchManager());
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
