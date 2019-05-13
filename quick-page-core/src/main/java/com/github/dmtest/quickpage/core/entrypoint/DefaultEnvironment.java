package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;
import com.github.dmtest.quickpage.core.property.DefaultPropertyManager;

public class DefaultEnvironment implements Environment {
    private DriverManager driverManager;
    private PageManager pageManager;
    private SearchManager searchManager;
    private PropertyManager propertyManager;

    @Override
    public Environment setEnvironment(PropertyManager propertyManager, DriverManager driverManager, SearchManager searchManager, PageManager pageManager) {
        this.propertyManager = propertyManager;
        this.driverManager = driverManager;
        this.searchManager = searchManager;
        this.pageManager = pageManager;
        return this;
    }

    @Override
    public void setEnvironmentDefaults() {
        PropertyManager<DefaultConfig> propertyManagerLocal = new DefaultPropertyManager<>(DefaultConfig.class);
        DriverManager driverManagerLocal = new DefaultDriverManager(this);
        SearchManager searchManagerLocal = new DefaultSearchManager(this);
        PageManager pageManagerLocal = new DefaultPageManager(this);
        setEnvironment(propertyManagerLocal, driverManagerLocal, searchManagerLocal, pageManagerLocal);
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

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DefaultConfig> PropertyManager<T> getPropertyManager() {
        return (PropertyManager<T>) propertyManager;
    }

}
