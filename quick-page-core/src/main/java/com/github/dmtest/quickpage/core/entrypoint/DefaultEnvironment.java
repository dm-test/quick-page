package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;

public class DefaultEnvironment implements Environment<DefaultEnvironment> {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;

    @Override
    public DefaultEnvironment setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
        return this;
    }

    @Override
    public DefaultEnvironment setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
        return this;
    }

    @Override
    public DefaultEnvironment setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
        return this;
    }

    @Override
    public DefaultEnvironment setDefaults() {
        DriverManager driverManagerLocal = new DefaultDriverManager();
        SearchManager searchManagerLocal = new DefaultSearchManager(driverManagerLocal);
        PageManager pageManagerLocal = new DefaultPageManager(driverManagerLocal, searchManagerLocal);
        setDriverManager(driverManagerLocal);
        setSearchManager(searchManagerLocal);
        setPageManager(pageManagerLocal);
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
