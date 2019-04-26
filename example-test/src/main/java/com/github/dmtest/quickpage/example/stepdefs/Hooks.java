package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.core.entrypoint.DefaultEnvironment;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before(order = 1000)
    public void setEnvironment() {
        DefaultEnvironment.setEnvironment();
    }

    @After
    public void quitDriver() {
        DefaultEnvironment.getEnvironment().getDriverManager().quitDriver();
    }

}
