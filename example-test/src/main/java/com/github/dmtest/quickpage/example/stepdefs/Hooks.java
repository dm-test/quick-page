package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.core.entrypoint.TestDefaultEnvironment;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private Environment testDefaultEnvironment;

    public Hooks(TestDefaultEnvironment testDefaultEnvironment) {
        this.testDefaultEnvironment = testDefaultEnvironment;
    }

    @Before(order = 1000)
    public void setEnvironment() {
        testDefaultEnvironment.setDefaults();
    }

    @After
    public void quitDriver() {
        testDefaultEnvironment.getDriverManager().quitDriver();
    }

}
