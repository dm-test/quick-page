package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.element.DefaultSearchManager;
import com.github.dmtest.quickpage.core.entrypoint.DefaultEnvironment;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.core.property.DefaultPropertyManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private Environment environment;

    public Hooks(DefaultEnvironment environment) {
        this.environment = environment;
    }

    @Before(order = 1000)
    public void setEnvironment() {
        PropertyManager<DefaultConfig> propertyManager = new DefaultPropertyManager<>(DefaultConfig.class);
        DriverManager driverManager = new DefaultDriverManager();
        SearchManager searchManager = new DefaultSearchManager(driverManager);
        PageManager pageManager = new DefaultPageManager(driverManager, searchManager, propertyManager);
        environment.setEnvironment(driverManager, searchManager, pageManager, propertyManager);

    }

    @After
    public void quitDriver() {
        environment.getDriverManager().quitDriver();
    }

}
