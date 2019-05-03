package com.github.dmtest.quickpage.core.page;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.api.page.PageManager;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.google.common.reflect.ClassPath;

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultPageManager implements PageManager {
    private Environment environment;
    private Set<Class<? extends Page>> pageClasses;
    private Page currentPage;

    public DefaultPageManager(Environment environment) {
        this.environment = environment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Page> T getCurrentPage() {
        return (T) currentPage;
    }

    @Override
    public <T extends Page> T getNewPageByName(String name) {
        Class<? extends Page> clazz = getPageClassByName(name);
        try {
            Constructor constructor = clazz.getConstructor(Environment.class);
            constructor.setAccessible(true);
            currentPage = (Page) constructor.newInstance(environment);
            return getCurrentPage();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    private Class<? extends Page> getPageClassByName(String name) {
        return getPageClasses().stream()
                .filter(clazz -> CommonSupport.getAnnotationNameValue(clazz).equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No classes with '@Name' is '%s'", name)));
    }

    @SuppressWarnings("unchecked")
    private Set<Class<? extends Page>> getPageClasses() {
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
            for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClassesRecursive(environment.getConfig().pagePackage())) {
                allClasses.add(info.load());
            }
        } catch (IOException e) {
            throw new IOError(e);
        }
        return allClasses;
    }

}
