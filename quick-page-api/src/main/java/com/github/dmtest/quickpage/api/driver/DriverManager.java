package com.github.dmtest.quickpage.api.driver;

import org.openqa.selenium.WebDriver;

public interface DriverManager {

    WebDriver getDriver();

    void quitDriver();
}
