package com.github.dmtest.quickpage.core.element;

import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.List;

public class DefaultSearchManager implements SearchManager {
    private static final String PATH_SEPARATOR = "->";

    @Override
    public <T extends WebElement> T searchElement(Object context, String path) {
        return null;
    }

    @Override
    public <T extends WebElement> T searchElement(String path) {
        return null;
    }

    @Override
    public <E extends WebElement> List<E> searchElementList(Object context, String path) {
        return null;
    }

    @Override
    public <E extends WebElement> List<E> searchElementList(String path) {
        return null;
    }

    private Field getFieldByPath(Object context, String path)  {
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
                        String.format("The class '%s' does not have '@Name' with value '%s'", clazz.getSimpleName(), name)));
    }

}