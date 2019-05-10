package com.github.dmtest.quickpage.core.element;

import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;

public class DefaultSearchManager implements SearchManager {
    private static final String PATH_SEPARATOR = "->";
    private Environment environment;

    public DefaultSearchManager(Environment environment) {
        this.environment = environment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(Object context, String path) {
        return (T) getObjectByPath(context, path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(String path) {
        return (T) getObjectByPath(environment.getCurrentPage(), path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(Object context, String path) {
        return (List<T>) getObjectByPath(context, path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(String path) {
        return (List<T>) getObjectByPath(environment.getCurrentPage(), path);
    }

    private Object getObjectByPath(Object context, String path) {
        String[] pathArray = path.split(PATH_SEPARATOR);
        try {
            for (String elementName : pathArray) {
                Field elementField = getFieldByName(context.getClass(), elementName);
                elementField.setAccessible(true);
                context = elementField.get(context);
            }
            return context;
        } catch (IllegalAccessException e) {
            String msgError = String.format(
                    "Can't get object by path '%s' from context '%s'", path, context.getClass().getSimpleName());
            throw new IllegalAccessError(msgError);
        }
    }

    private Field getFieldByName(Class clazz, String name) {
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        return fields.stream()
                .filter(field -> CommonSupport.getAnnotationNameValue(field).equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("The class '%s' does not have field with '@Name' is '%s'", clazz.getSimpleName(), name)));
    }

}
