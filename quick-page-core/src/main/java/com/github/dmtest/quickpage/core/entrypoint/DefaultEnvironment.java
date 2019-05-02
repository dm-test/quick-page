package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;

public class DefaultEnvironment implements Environment {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;
    private PropertyManager propertyManager;

    public Environment setEnvironment(DriverManager driverManager, SearchManager searchManager, PageManager pageManager, PropertyManager propertyManager) {
        this.driverManager = driverManager;
        this.searchManager = searchManager;
        this.pageManager = pageManager;
        this.propertyManager = propertyManager;
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

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DefaultConfig> PropertyManager<T> getPropertyManager() {
        return (PropertyManager<T>) propertyManager;
    }

}
