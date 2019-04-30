package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.entrypoint.DefaultEnvironment;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private Environment environment;

    public Hooks(DefaultEnvironment environment) {
        this.environment = environment;
    }

    @Before(order = 1000)
    public void setEnvironment() {
        DriverManager driverManager = new DefaultDriverManager();
        SearchManager searchManager = new DefaultSearchManager(driverManager);
        PageManager pageManager = new DefaultPageManager(driverManager, searchManager, "com.github.dmtest.quickpage.example.pages");
        environment.setEnvironment(driverManager, searchManager, pageManager);
    }

    @After
    public void quitDriver() {
        environment.getDriverManager().quitDriver();
    }

}
