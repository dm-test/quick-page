package com.github.dmtest.quickpage.api.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.PageManager;

public interface Environment {

    DriverManager getDriverManager();

    PageManager getPageManager();

    SearchManager getSearchManager();
}
