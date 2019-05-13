package com.github.dmtest.quickpage.api.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Интерфейс для управления веб-драйвером
 */
public interface DriverManager {

    /**
     * Метод отдает инстанс {@link WebDriver}
     *
     * @return инстанс {@link WebDriver}
     */
    WebDriver getDriver();

    /**
     * Метод останавливает драйвер и закрывает браузер
     */
    void quitDriver();

    /**
     * Метод отдает скриншот текущей страницы в виде {@link File}
     *
     * @return скриншот текущей страницы в виде {@link File}
     */
    default File takeScreenshotAsFile() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }

    DesiredCapabilities getCapabilities();

    void setCapabilities(DesiredCapabilities capabilities);
}
