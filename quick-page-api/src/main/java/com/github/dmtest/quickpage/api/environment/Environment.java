package com.github.dmtest.quickpage.api.environment;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.page.PageManager;

public interface Environment {

    DriverManager getDriverManager();

    PageManager getPageManager();
}
