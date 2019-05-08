package com.github.dmtest.quickpage.core.element;

import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

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
        Field elementField = getFieldByPath(context, path);
        CustomHtmlElementDecorator decorator = new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(environment.getDriver()));
        return decorator.decorate(DefaultSearchManager.class.getClassLoader(), elementField);
    }

    private Field getFieldByPath(Object context, String path) {
        Class clazz = context.getClass();
        String[] pathArray = path.split(PATH_SEPARATOR);
        int lastElementIndex = pathArray.length - 1;
        for (int i = 0; i < lastElementIndex; i++) {
            String name = pathArray[i];
            clazz = getFieldByName(clazz, name).getType();
        }
        return getFieldByName(clazz, pathArray[lastElementIndex]);
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
