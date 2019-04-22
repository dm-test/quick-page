package com.github.dmtest.quickpage.core.page;

import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOError;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultPageManager implements PageManager {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultPageManager.class);
    private static volatile DefaultPageManager instance;
    private Set<Class<? extends Page>> pageClasses;

    private DefaultPageManager() {
    }

    public static DefaultPageManager getInstance() {
        DefaultPageManager localInstance = instance;
        if (localInstance == null) {
            synchronized (DefaultPageManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultPageManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public <T extends Page> T getNewPageByName(String name) {
        Class<? extends Page> clazz = getPageClassByName(name);
        return CommonSupport.getInstance(clazz);
    }

    private Class<? extends Page> getPageClassByName(String name) {
        return getPageClasses().stream()
                .filter(clazz -> CommonSupport.getAnnotationNameValue(clazz).equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No classes with '@Name value()' is '%s'", name)));
    }

    @SuppressWarnings("unchecked")
    private synchronized Set<Class<? extends Page>> getPageClasses() {
        if (Objects.isNull(pageClasses)) {
            pageClasses = getAllClasses().stream()
                    .filter(Page.class::isAssignableFrom)
                    .map(clazz -> (Class<? extends Page>) clazz)
                    .collect(Collectors.toSet());
        }
        return pageClasses;
    }

    @SuppressWarnings("UnstableApiUsage")
    private Set<Class<?>> getAllClasses() {
        Set<Class<?>> allClasses = new HashSet<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClassesRecursive("com.github.dmtest.pages")) {
                allClasses.add(info.load());
            }
        } catch (IOException e) {
            LOG.error("Failed to read class path resources", e);
            throw new IOError(e);
        }
        return allClasses;
    }

}
