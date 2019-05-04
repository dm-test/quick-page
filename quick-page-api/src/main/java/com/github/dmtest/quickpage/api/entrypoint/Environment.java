package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import org.openqa.selenium.WebDriver;

/**
 * Интерфейс точка входа. Агрегатор для реализаций менеджеров и провайдер доступа к ним
 */
public interface Environment {

    /**
     * Метод отдает выбранный {@link DriverManager}
     *
     * @return выбранный {@link DriverManager}
     */
    DriverManager getDriverManager();

    /**
     * Метод отдает выбранный {@link SearchManager}
     *
     * @return выбранный {@link SearchManager}
     */
    SearchManager getSearchManager();

    /**
     * Метод отдает выбранный {@link PageManager}
     *
     * @return выбранный {@link PageManager}
     */
    PageManager getPageManager();

    /**
     * Метод отдает выбранный {@link PropertyManager}
     *
     * @param <T> конфигурация, которой типизирован выбранный {@link PropertyManager}
     * @return выбранный {@link PropertyManager}
     */
    <T extends DefaultConfig> PropertyManager<T> getPropertyManager();

    /**
     * Метод инициализирует {@link Environment} выбранными пользователем менеджерами
     *
     * @param propertyManager выбранный {@link PropertyManager}
     * @param driverManager   выбранный {@link DriverManager}
     * @param searchManager   выбранный {@link SearchManager}
     * @param pageManager     выбранный {@link PropertyManager}
     * @return инициализированный {@link Environment}
     */
    Environment setEnvironment(PropertyManager propertyManager, DriverManager driverManager, SearchManager searchManager, PageManager pageManager);

    /**
     * Метод инициализирует {@link Environment} дефолтными для данного {@link Environment} менеджерами
     */
    void setEnvironmentDefaults();

    //----------------------- Shortcuts -----------------------

    /**
     * Метод отдает текущую проперти конфигурацию
     *
     * @param <T> тип проперти конфигурации
     * @return текущуая проперти конфигурация
     */
    default <T extends DefaultConfig> T getConfig() {
        PropertyManager<T> propertyManager = getPropertyManager();
        return propertyManager.getConfig();
    }

    /**
     * Метод отдает текущий веб-драйвер
     *
     * @return текущий веб-драйвер
     */
    default WebDriver getDriver() {
        return getDriverManager().getDriver();
    }

    /**
     * Метод отдает текущий инициализированный page-object
     *
     * @param <T> тип текущего page-object
     * @return текущий инициализированный page-object
     */
    default <T extends Page> T getCurrentPage() {
        return getPageManager().getCurrentPage();
    }
}
