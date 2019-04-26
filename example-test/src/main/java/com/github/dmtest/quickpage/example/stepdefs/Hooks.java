package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.entrypoint.DefaultEnvironment;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;
//import com.github.dmtest.support.driver.DriverSupport;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private Environment environment;

    @Before
    public void preSet() {
//        Environment environment = new DefaultEnvironment(new DefaultDriverManager(), new DefaultPageManager(), new DefaultSearchManager());
        environment = new DefaultEnvironment();

    }

    public Environment getEnvironment() {
        return environment;
    }

    @After
    public void quitDriver() {

    }
}
