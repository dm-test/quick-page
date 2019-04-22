package com.github.dmtest.quickpage.core.entrypoint;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.driver.DefaultDriverManager;
import com.github.dmtest.quickpage.core.page.DefaultPageManager;

public class DefaultEnvironment implements Environment {
    private static volatile DefaultEnvironment instance;
    private DriverManager driverManager;
    private PageManager pageManager;

    public static DefaultEnvironment getInstance() {
        DefaultEnvironment localInstance = instance;
        if (localInstance == null) {
            synchronized (DefaultEnvironment.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultEnvironment(new DefaultDriverManager(), DefaultPageManager.getInstance());
                }
            }
        }
        return localInstance;
    }

    private DefaultEnvironment(DriverManager driverManager, PageManager pageManager) {
        this.driverManager = driverManager;
        this.pageManager = pageManager;
    }

    @Override
    public DriverManager getDriverManager() {
        return driverManager;
    }

    @Override
    public PageManager getPageManager() {
        return pageManager;
    }
}
