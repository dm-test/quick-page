package com.github.dmtest.quickpage.api.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public interface DriverManager {

    WebDriver getDriver();

    void quitDriver();

    default File takeScreenshotAsFile() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }
}
