package com.github.dmtest.quickpage.core.element;

import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class DefaultSearchManager implements SearchManager {
    private static final String PATH_SEPARATOR = "->";
    private Environment environment;
    private HtmlElementDecorator decorator;

    public DefaultSearchManager(Environment environment) {
        this.environment = environment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(Object context, String path, boolean reInit) {
        return (T) getObjectByPath(context, path, reInit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(Object context, String path) {
        return (T) getObjectByPath(context, path, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(String path, boolean reInit) {
        return (T) getObjectByPath(environment.getCurrentPage(), path, reInit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> T searchElement(String path) {
        return (T) getObjectByPath(environment.getCurrentPage(), path, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(Object context, String path, boolean reInit) {
        return (List<T>) getObjectByPath(context, path, reInit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(Object context, String path) {
        return (List<T>) getObjectByPath(context, path, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(String path) {
        return (List<T>) getObjectByPath(environment.getCurrentPage(), path, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends WebElement> List<T> searchElementList(String path, boolean reInit) {
        return (List<T>) getObjectByPath(environment.getCurrentPage(), path, reInit);
    }

    private Object getObjectByPath(Object context, String path, boolean reInit) {
        String[] pathArray = path.split(PATH_SEPARATOR);
        try {
            for (String elementName : pathArray) {
                Field elementField = getFieldByName(context.getClass(), elementName);
                elementField.setAccessible(true);
                if (reInit) {
                    Object elementFieldValue = getDecorator().decorate(DefaultSearchManager.class.getClassLoader(), elementField);
                    elementField.set(context, elementFieldValue);
                    context = elementFieldValue;
                } else {
                    context = elementField.get(context);
                }
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

    private HtmlElementDecorator getDecorator() {
        if (Objects.isNull(decorator)) {
            decorator = new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(environment.getDriver()));
        }
        return decorator;
    }

}
